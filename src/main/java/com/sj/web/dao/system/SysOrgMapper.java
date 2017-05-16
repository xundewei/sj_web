package com.sj.web.dao.system;

import com.sj.web.model.system.SysOrg;
import com.sj.web.model.system.SysOrgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysOrgMapper {
    int countByExample(SysOrgExample example);

    int deleteByExample(SysOrgExample example);

    int deleteByPrimaryKey(String pkSysOrg);

    int insert(SysOrg record);

    int insertSelective(SysOrg record);

    List<SysOrg> selectByExample(SysOrgExample example);
    
    List<SysOrg> selectAll();
    
    List<SysOrg> selectByOrgcdoe(@Param("orgcode") String orgcode);
    
    List<SysOrg> selectByLastOrg(@Param("orgcode") String orgcode);

    SysOrg selectByPrimaryKey(String pkSysOrg);

    int updateByExampleSelective(@Param("record") SysOrg record, @Param("example") SysOrgExample example);

    int updateByExample(@Param("record") SysOrg record, @Param("example") SysOrgExample example);

    int updateByPrimaryKeySelective(SysOrg record);

    int updateByPrimaryKey(SysOrg record);
}