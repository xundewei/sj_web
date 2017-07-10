package com.sj.web.common.mybatis.plugin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.slf4j.LoggerFactory;

import com.sj.web.common.mybatis.plugin.MybatisSpringPageInterceptor.PageNotSupportException;
import com.sj.web.common.mybatis.plugin.Util.ReflectUtil;


/**
 *  设计：对原生态的参数不做任何调整，只是需要添加一个翻页对象支持翻页
 * 后续需要优化：对反向工程需要做一定的改造，所有参数将不固定死，动态的添加和变化，目前手工去完成
 * @Description: mybaits 插件主要是用于翻页
 * @author tody
 * @date 2017年5月4日上午10:26:20
 */
@Intercepts({ @Signature(method = "prepare", type = StatementHandler.class, args = { Connection.class, Integer.class })})
public class PageHelper implements Interceptor {
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(PageHelper.class);

	public static final String ORACLE = "oracle";
	public static final String MYSQL = "mysql";

	protected String dialect; // 目前不需要配置此参数，会自动解析相关参数

	public PageHelper() {
		super();
		logger.info("mybaits 插件主要是用于翻页处理");
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public String getDialect() {
		return dialect;
	}

	public void setDialect(String dialect) {
		if (!dialect.equalsIgnoreCase(MYSQL) && !dialect.equalsIgnoreCase(ORACLE)) {
			throw new PageNotSupportException(
					"Page not support for the type of database, database type [" + dialect + "]");
		}
		this.dialect = dialect;
	}

	@Override
	public void setProperties(Properties props) {
		String dialect = props.getProperty("dialect");
		if (dialect != null) {
			setDialect(dialect);
		}
	}

	/**
	 * 每次查询都会执行相关此过程，此插件的实际业务逻辑
	 */
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		if (invocation.getTarget() instanceof StatementHandler) {
			RoutingStatementHandler handler = (RoutingStatementHandler) invocation.getTarget();
			StatementHandler delegate = (StatementHandler) ReflectUtil.getFieldValue(handler, "delegate");
			BoundSql boundSql = delegate.getBoundSql();
			Page page = findPageObject(boundSql.getParameterObject());
			if (page == null) {
				if(logger.isDebugEnabled()){
					logger.debug("没有Page对象作为参数, 不是分页查询.");
				}
				
				return invocation.proceed();
			} else {
				logger.debug("检测到分页Page对象, 使用分页查询.");
				Connection connection = (Connection) invocation.getArgs()[0];
				prepareAndCheckDatabaseType(connection); // 檢測數據庫類型
				String sql = boundSql.getSql();
				String pageSql = buildPageSql(page, sql);
				if(logger.isDebugEnabled()){
					logger.debug("分页时, 生成分页pageSql: " + pageSql);
				}
				ReflectUtil.setFieldValue(boundSql, "sql", pageSql);
				return invocation.proceed();
			}
		}
		return invocation.proceed(); 
	}

	/**
	 * 查找翻页工具类，判断是否翻页
	 * @param parameterObj
	 * @return Page 工具类
	 */
	private Page<?> findPageObject(Object parameterObj) {
		logger.debug("开始解析 传入的参数");
		if (parameterObj instanceof Page<?>) {
			return (Page<?>) parameterObj;
		} else if (parameterObj instanceof Map) {
			for (Object val : ((Map<?, ?>) parameterObj).values()) {
				if (val instanceof Page<?>) {
					return (Page<?>) val;
				}
			}
		}
		return null;
	}

	
	/**
	 * 检测数据库类型
	 * @param connection
	 * @throws SQLException
	 */
	private void prepareAndCheckDatabaseType(Connection connection) throws SQLException {
		String productName11 = connection.getMetaData().getDatabaseProductName();
		if (dialect == null) {
			String productName = connection.getMetaData().getDatabaseProductName();
			logger.debug("Database productName: " + productName);
			productName = productName.toLowerCase();
			if (productName.indexOf(MYSQL) != -1) {
				dialect = MYSQL;
			} else if (productName.indexOf(ORACLE) != -1) {
				dialect = ORACLE;
			} else {
				throw new PageNotSupportException(
						"Page not support for the type of database, database product name [" + productName + "]");
			}
			if(logger.isDebugEnabled()){
				logger.debug("自动检测到的数据库类型为: " + dialect);
			}
			
		}
	}
	
	/**
	 * <pre>
	 * 
	 * 生成分页SQL
	 * </pre>
	 * 
	 * @param page
	 * @param sql
	 * @return
	 */
	private String buildPageSql(Page<?> page, String sql) {
		if (MYSQL.equalsIgnoreCase(dialect)) {
			return buildMysqlPageSql(page, sql);
		} else if (ORACLE.equalsIgnoreCase(dialect)) {
			return buildOraclePageSql(page, sql);
		}
		return sql;
	}
	
	/**
	 * <pre>
	 * 
	 * 生成Mysql分页查询SQL
	 * </pre>
	 * 
	 * @param page
	 * @param sql
	 * @return
	 */
	private String buildMysqlPageSql(Page<?> page, String sql) {
		// 计算第一条记录的位置，Mysql中记录的位置是从0开始的。
		int offset = (page.getPageNo() - 1) * page.getPageSize();
		return new StringBuilder(sql).append(" limit ").append(offset).append(",").append(page.getPageSize())
				.toString();
	}

	
	/**
	 * <pre>
	 * 
	 * 生成Oracle分页查询SQL
	 * </pre>
	 * 
	 * @param page
	 * @param sql
	 * @return
	 */
	private String buildOraclePageSql(Page<?> page, String sql) {
		// 计算第一条记录的位置，Oracle分页是通过rownum进行的，而rownum是从1开始的
		int offset = (page.getPageNo() - 1) * page.getPageSize() + 1;
		StringBuilder sb = new StringBuilder(sql);
		sb.insert(0, "select u.*, rownum r from (").append(") u where rownum < ").append(offset + page.getPageSize());
		sb.insert(0, "select * from (").append(") where r >= ").append(offset);
		return sb.toString();
	}

}