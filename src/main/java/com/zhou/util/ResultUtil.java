package com.zhou.util;

public class ResultUtil {

    public static final CommonResponse SUCCESS = successResult();

    public static CommonResponse successResult(){
        CommonResponse result = new CommonResponse();
        result.setSuccess(true);
        result.setErrorCode(0);
        result.setErrorMsg("success");
        return result;
    }

    public static CommonResponse success(Object data){
        CommonResponse response = new CommonResponse();
        response.setErrorCode(0);
        response.setData(data);
        response.setSuccess(true);
        return response;
    }

    public static CommonResponse failResult(Integer code, String message){
        CommonResponse result = new CommonResponse();
        result.setErrorCode(0);
        result.setSuccess(false);
        result.setErrorMsg(message);
        return result;
    }

}
