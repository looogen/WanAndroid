package com.loogen.wanandroid.model;

/**
 * A generic class that holds a result success w/ data or an error exception.
 */
public class DataResult {
    // hide the private constructor to limit subclass types (Success, Error)
    private DataResult() {
    }

    @Override
    public String toString() {
        if (this instanceof DataResult.Success) {
            DataResult.Success success = (DataResult.Success) this;
            return "Success[data=" + success.getData().toString() + "]";
        } else if (this instanceof DataResult.Error) {
            DataResult.Error error = (DataResult.Error) this;
            return "Error[exception=" + error.getError() + "]";
        }
        return "";
    }

    // Success sub-class
    public final static class Success<T> extends DataResult {
        private final T data;

        public Success(T data) {
            this.data = data;
        }

        public T getData() {
            return this.data;
        }
    }

    // Error sub-class
    public final static class Error extends DataResult {
        private final String error;

        public Error(String error) {
            this.error = error;
        }

        public String getError() {
            return error;
        }
    }

    public interface IResult<T>{
        void onSuccess(Success<T> data);
        void onError(Error error);
    }
}