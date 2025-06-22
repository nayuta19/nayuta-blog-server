package org.nayutablogserver.pojo;


import lombok.Data;

@Data
public class Result {
    private Integer code;
    private String message;
    private Object data;

    public static Result success() {
        Result result = new Result();
        result.code = 200;
        result.message = "success";
        return result;
    }

    public static Result success(Object data) {
        Result result = success();
        result.data = data;
        return result;
    }

    public static Result error() {
        Result result = new Result();
        result.code = 500;
        result.message = "error";
        return result;
    }

    public static Result error(Object data) {
        Result result = error();
        result.data = data;
        return result;
    }
}
