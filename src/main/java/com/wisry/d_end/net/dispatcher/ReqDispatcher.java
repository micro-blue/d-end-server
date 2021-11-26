package com.wisry.d_end.net.dispatcher;


import com.wisry.d_end.util.GsonUtil;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@Data
public class ReqDispatcher {
    private static ReqDispatcher dispatcher = new ReqDispatcher();
    private ReqController reqController;
    private ReqDispatcher() {
        reqController=new ReqController();
        this.initReqMap();
    }

    private Map<Class, Consumer<String>>reqMap;

    public static ReqDispatcher getInstance(){
        return dispatcher;
    }
    public void initReqMap(){
        reqMap=new HashMap<>();
       /* reqMap.put(InitColliderTransformReq.class,reqController::initColliderTransformReq);
        reqMap.put(DirInputReq.class,reqController::dirInputReq);
        reqMap.put(UniformMotionReq.class,reqController::testUniform);
        reqMap.put(SyncPosReq.class,reqController::syncPos);

        reqMap.put(BaseSkillReleaseReq.class,reqController::handleSkillRelease);*/
    }
    public  void handleRequest(Class c,Object request){
     //   System.out.println("handleRequest Class"+c);
        Consumer consumer = reqMap.get(c);
        String reqDataJson = GsonUtil.GsonString(request);
        consumer.accept(reqDataJson);
    }
}
