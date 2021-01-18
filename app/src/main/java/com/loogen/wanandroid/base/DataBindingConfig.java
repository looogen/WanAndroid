package com.loogen.wanandroid.base;

import android.util.SparseArray;

/**
 * 配置各种绑定的参数
 */
public class DataBindingConfig {

    private final int layout;
    private final SparseArray<Object> bindingParams = new SparseArray<>();
    private int vmVariableId;

    public DataBindingConfig(int layout, int vmVariableId) {
        this.layout = layout;
        this.vmVariableId = vmVariableId;
    }

    public DataBindingConfig(int layout) {
        this.layout = layout;
    }

    public int getLayout() {
        return layout;
    }

    public int getVmVariableId() {
        return vmVariableId;
    }

    public SparseArray<Object> getBindingParams() {
        return bindingParams;
    }

    public DataBindingConfig addBindingParam(int variableId, Object object) {
        if (bindingParams.get(variableId) == null) {
            bindingParams.put(variableId, object);
        }
        return this;
    }
}