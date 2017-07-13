package com.sj.core.utils.web;

import java.io.Serializable;
import java.util.Map;

import org.slf4j.LoggerFactory;

import com.sj.web.common.StateIdentifier;

/**
 * 服务端返回给客户端的数据封装对象
 */
public class JsonResult implements Serializable {

    private static final long serialVersionUID = 2652608027682835212L;
    
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(JsonResult.class);
    
    
    /**
     * 服务端业务逻辑是否执行成功
     */
    private boolean success;

    /**
     * 错误编号
     */
    private String errorCode = "";

    /**
     * 信息(如果发生错误，那么代表错误信息)
     */
    private String message = "";

    /**
     * 返回给客户端的数据对象
     */
    private Object data;

    public JsonResult() {
    }

    public JsonResult(boolean success, String errorCode, String message, Object data) {
        this.success = success;
        this.errorCode = errorCode;
        this.message = message;
        this.data = data;
    }

    /**
     * 返回一个代表成功的JsonResult，无返回对象，无信息
     *
     * @return 成功的JsonResult
     */
    public static JsonResult success() {
        return new JsonResult(true, "", "", null);
    }

    /**
     * 返回一个代表成功的JsonResult，包括返回的数据
     *
     * @param data 需要返回的数据
     * @return 成功的JsonResult
     */
    public static JsonResult success(Object data) {
        return new JsonResult(true, "", "", data);
    }

    /**
     * 返回一个代表成功的JsonResult，有返回对象，有提示信息
     *
     * @return 成功的JsonResult
     */
    public static JsonResult success(String message, Object data) {
        return new JsonResult(true, "", message, data);
    }

    /**
     * 返回一个代表失败的JsonResult，包含错误信息
     *
     * @param message 需要返回的错误信息
     * @return 失败的JsonResult
     */
    public static JsonResult error(String message) {
        return new JsonResult(false, "", message, null);
    }

    /**
     * 返回一个代表失败的JsonResult，包含错误码和错误信息
     *
     * @param errorCode 错误码
     * @param message   需要返回的错误信息
     * @return
     */
    public static JsonResult error(String errorCode, String message) {
        return new JsonResult(false, errorCode, message, null);
    }
    
    /**
     * 
    * @Title: analyzeMaptoJsonResult
    * @Description: 用于controller层解析service返回消息
    * @param map
    * @return
    * @throws
     */
    public static JsonResult analyzeMaptoJsonResult(Map<String,Object> map) {
    	try {
    		Integer status_value = (Integer) map.get(StateIdentifier.JsonResult_Status); //1 表示成功  2标示失败
    		JsonResult jr=null;
    		switch (status_value) {
    		case 1:
    			jr=new JsonResult(true, "", "", null);
    			break;
    		case 2:
    			String msg_value = (String) map.get(StateIdentifier.JsonResult_Message);
    			jr= new JsonResult(false, "", msg_value, null);
    			break;
    		}
    		return jr;
		} catch (Exception e){
			logger.debug("给的状态值不对,只能给1,2两个状态值,必须是整数类型");
		}
		return null;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
