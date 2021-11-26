package com.wisry.d_end.net;


import com.wisry.d_end.util.GsonUtil;
import io.netty.buffer.ByteBuf;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class MsgUtil {
    private static MsgUtil r = new MsgUtil();

    // 私有的构造方法
    private MsgUtil(){
        clazzToCodeMap=new HashMap<>();
        codeToClazzMap=new HashMap<>();
      /*  clazzToCodeMap.put(InitColliderTransformReq.class,10000);
        codeToClazzMap.put(10000,InitColliderTransformReq.class);
        clazzToCodeMap.put(PositionSyncRsp.class,10001);
        codeToClazzMap.put(10001, DirInputReq.class);

        clazzToCodeMap.put(UniformMotionRsp.class,20003);
        codeToClazzMap.put(10003, UniformMotionReq.class);

        codeToClazzMap.put(10004,SyncPosReq.class);
        codeToClazzMap.put(11000, BaseSkillReleaseReq.class);

        clazzToCodeMap.put(VerticalJumpRsp.class,20004);*/
    }

    private static Map<Class,Integer>clazzToCodeMap;
    private static Map<Integer,Class>codeToClazzMap;


    public static MsgUtil getR(){
        return r;
    }
    public Map<Object,String>map=new HashMap();
    public static void main(String[] args) {
        /*A a = new A();
        B b = new B();
        a.a="a";
        b.b="b";
        R<Object> objectR = new R<>();
        objectR.map.put(a,a.a);
        objectR.map.put(b,b.b);
        Object objB=b;
        System.out.println(objectR.map.get(objB));

        NsMsg ok = R.getR().ok(B.class, b);
        System.out.println(ok);*/
    }
    public static DeMsg rsp(Object data){
        Integer code = clazzToCodeMap.get(data.getClass());
        return new DeMsg(code,data);
    }
    public static DeMsg rsp(Class clazz, Object data){
        Integer code = clazzToCodeMap.get(clazz);
        return new DeMsg(code,data);
    }
    public static DeMsg parseReqFromByteBuf(ByteBuf byteBuf){
        String json = byteBuf.toString(Charset.forName("utf-8"));
        DeMsg nsMsg = GsonUtil.GsonToBean(json, DeMsg.class);
       /* Integer code=nsMsg.getCode();
        Class dataClazz = codeToClazzMap.get(code);*/
        return nsMsg;
    }
    public static Class getClazzByCode(int code){
        Class aClass = codeToClazzMap.get(code);
        return aClass;
    }

    public static int getCodeByClass(Class c){
        Integer code = clazzToCodeMap.get(c);
        return code;
    }
}

