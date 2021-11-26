package com.wisry.d_end.net.dispatcher;

import com.wisry.d_end.core.Leader;
import lombok.Data;

@Data
public class ReqController {
    private Leader leader;

    /*public void initColliderTransformReq(String requestJson){
        InitColliderTransformReq initColliderTransformReq = GsonUtil.GsonToBean(requestJson, InitColliderTransformReq.class);
        leader.handle(initColliderTransformReq);
       // System.out.println(initColliderTransformReq);
    }
    public void dirInputReq(String requestJson){
        DirInputReq dirInputReq = GsonUtil.GsonToBean(requestJson, DirInputReq.class);
    //    leader.handle(dirInputReq);
        //System.out.println(initColliderTransformReq);
    }
    //测试匀速直线运动
    public void testUniform(String requestJson){
        UniformMotionReq uniformMotionReq = GsonUtil.GsonToBean(requestJson, UniformMotionReq.class);
        leader.handle(uniformMotionReq);
        // System.out.println(initColliderTransformReq);
    }

    public void syncPos(String requestJson) {
        SyncPosReq syncPosReq = GsonUtil.GsonToBean(requestJson, SyncPosReq.class);
        leader.handle(syncPosReq);
    }

    public void handleSkillRelease(String requestJson) {
        BaseSkillReleaseReq skillReleaseReq=GsonUtil.GsonToBean(requestJson,BaseSkillReleaseReq.class);
        leader.handle(skillReleaseReq);
    }*/

/*
    public static Object handle(Object o) {
    }*/
}
