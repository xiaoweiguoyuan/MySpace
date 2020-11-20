package com.wuba.car.myspace.base;


public class CommonCodeEvent<T> {

    private int code;
    private T data;

    public CommonCodeEvent(int code) {
        this.code = code;
    }

    public CommonCodeEvent(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public int getCode() {
        return code;
    }
}
