package com.github.yvkm.errorhandlder;

import java.util.List;

/**
 * 自定义的响应实体类，用于封装响应结果
 * @author xie jian xun
 * @since
 */
public class ErrorResponse {

    private String message;
    private List<String> detail;

    public ErrorResponse(String message, List<String> detail) {
        this.message = message;
        this.detail = detail;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getDetail() {
        return detail;
    }

    public void setDetail(List<String> detail) {
        this.detail = detail;
    }
}
