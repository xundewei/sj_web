package com.sj.core.utils.web.easyui;

import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.Map;

/**
 * EasyUI Grid填充数据需要的包装功能类
 * @author tody
 * 2017年5月3日9:16:16
 */
public class EzPageResult {
    /**
     * 帮助方法，用于返回EasyUi Grid需要的数据格式
     *
     * @param totalCount
     * @param data
     * @return
     */
    public static Map<String, Object> build(long totalCount, Object data) {
        Map<String, Object> jsonMap = new HashMap<String, Object>();

        //total/rows为easyui grid定义的字段，不能改
        jsonMap.put("total", totalCount);
        jsonMap.put("rows", data);

        return jsonMap;
    }
    
    /**
     * 帮助方法，用于返回EasyUi Grid需要的数据格式
     * @param 
     * 		totalCount 总条数
     * @param 
     * 		data 查询的数据
     * @return
     */
    public static Map<String, Object> build(int totalCount, Object data) {
        Map<String, Object> jsonMap = new HashMap<String, Object>();

        //total/rows为easyui grid定义的字段，不能改
        jsonMap.put("total", totalCount);
        jsonMap.put("rows", data);

        return jsonMap;
    }

    
    /**
     * 帮助方法，用于返回EasyUi Grid需要的数据格式
     *
     * @param pageResult
     * @return
     */
    public static <T> Map<String, Object> build(Page<T> pageResult) {
        return build(pageResult.getTotalElements(), pageResult.getContent());
    }
}
