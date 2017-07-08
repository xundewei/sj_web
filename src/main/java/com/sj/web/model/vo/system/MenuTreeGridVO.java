package com.sj.web.model.vo.system;

import java.io.Serializable;

import com.sj.web.model.bean.system.SysMenu;

/**
 * 
* @ClassName: MenuTreeGridVO
* @Description: 用于转换treegird easyui 需要显示的时候需要特殊两个字段
* @author TODY happyming886@126.com
* @date 2017年7月8日 上午11:27:23
*
 */
public class MenuTreeGridVO extends SysMenu implements Serializable{
	 //用于easyui treegrid进行树处理
    public String _parentId;

    //上级名称
    public String parentname;
}
