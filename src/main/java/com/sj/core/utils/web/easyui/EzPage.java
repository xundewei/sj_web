package com.sj.core.utils.web.easyui;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;

/**
 * EasyUI Grid 查询参数包装对象
 */
public class EzPage implements Serializable {

    //指定页（返回第几页的数据）
    private int page;

    //指定每页几行数据
    private int rows;

    //指定排序字段
    private String sort;

    //指定排序方向
    private String order;

    public EzPage() {
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    /**
     * 获取一个JPA的分页请求对象
     * @return
     */
    public Pageable buildJPAPageRequest(){
        Sort.Direction direction = Sort.Direction.ASC;
        if(StringUtils.equals(order, "desc"))
            direction = Sort.Direction.DESC;

        Pageable pageable = new PageRequest(page - 1, rows, direction, sort);

        return pageable;
    }
}
