package com.gxey.remotemedicalplatform.model;

/**
 * Created by simple on 16/12/16.
 */

public class ApiModel <T>{
    private boolean Code;
    private String Message;
    private T ResultJson;

    public boolean isCode() {
        return Code;
    }

    public void setCode(boolean Code) {
        this.Code = Code;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public T getResultJson() {
        return ResultJson;
    }

    public void setResultJson(T resultJson) {
        ResultJson = resultJson;
    }
}
