package com.sj.web.model.system;

import java.io.Serializable;

/**
 * 
 * @Description: 用于转换treegird 特殊VO
 * @author tody
 * @date 2017年5月4日下午10:00:13
 */
public class OrgTreeGridVO extends SysOrg implements Serializable{
	 //用于easyui treegrid进行树处理
    public String _parentId;

    //上级名称
    public String parentname;
}
