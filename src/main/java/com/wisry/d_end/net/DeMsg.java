package com.wisry.d_end.net;

import lombok.Data;

@Data
public class DeMsg {
    private int code;
   // private String clazzName;
    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

   /* public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }*/

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public DeMsg(int code, Object data) {
        this.code = code;
        this.data = data;
    }

    public DeMsg() {
    }

}
