package com.loogen.wanandroid.model;

import androidx.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class Request<T> {
    public final T data;
    public final int code;
    public final String msg;

    public static final String TAG = "Request";

    public Request(@Status int code,T data,String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public Request(@Status int code) {
        this(code,null,"");
    }

    @IntDef({Status.SUCCESS,Status.LOADING, Status.ERROR})
    @Retention(RetentionPolicy.SOURCE)
    @Target({ElementType.FIELD,ElementType.PARAMETER})
    public @interface Status{
        int SUCCESS = 1;
        int LOADING = 0;
        int ERROR = -1;
    }


    public T getData() {
        return data;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static <T>Request<T> error(String msg) {
        return new Request<>(Status.ERROR,null,msg);
    }

    public static <T> Request<T> success(T data) {
        return new Request<>(Status.SUCCESS,data,"");
    }


    public interface IResult<T> {
        void onSuccess(Request<T> data);
        void onError(Request<T> error);
    }

}


