package org.cheng.devtool.controller;

import java.util.HashMap;

/**
 * @author li.cheng
 * @version 1.0.0 2017年04月24日
 * @since soter 1.0.0
 */
public class DataVo {
    private int code = 200;
    private String msg = "";
    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static DataVo set(Object data){
        DataVo dataVo = new DataVo();
        dataVo.setData(data);
        return dataVo;
    }

    public static DataVo err(int code,String msg){
        DataVo dataVo = new DataVo();
        dataVo.setCode(code);
        dataVo.setMsg(msg);
        dataVo.setData(new HashMap<>());
        return dataVo;
    }
}
