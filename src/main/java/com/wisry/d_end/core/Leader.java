package com.wisry.d_end.core;

import com.wisry.d_end.core.service.BattleService;
import com.wisry.d_end.net.DeKcpServer;

import lombok.Data;

/**
 * 游戏逻辑总控制，root
 */
@Data
public class Leader implements Runnable {
    private static Leader leader;
    private BattleService battleService;
    private boolean isInited=false;
    private long curTickTime;
    static {
        leader = new Leader();
    }
    private DeKcpServer kcpServer;
    /*public void init() {
        leader.kcpServer=NsKcpServer.initServer();
    }*/

   /* public void handle(InitColliderTransformReq initColliderTransformReq) {
        System.out.println("initColliderTransformReq:"+initColliderTransformReq);
        battleService.handleInit(initColliderTransformReq);
        isInited=true;
    }*/


    //玩家技能请求处理逻辑：从
    /*public void handle(UniformMotionReq uniformMotionReq) {
        //System.out.println(uniformMotionReq);
        UniformMotionRsp uniformMotionRsp = new UniformMotionRsp();
        battleService.handleUniformMotion(uniformMotionReq,uniformMotionRsp);


        DeMsg rsp = MsgUtil.rsp(uniformMotionRsp.getClass(), uniformMotionRsp);
        kcpServer.broadCast(rsp);
    }*/

    public static Leader getInstance() {
        return leader;
    }

    private Leader() {
    }
    //逻辑帧，类似unity update或fixedUpdate，每50ms调用一次
    @Override
    public void run() {

        //1.轮询场景中的所有unit，从buffService中的BuffStateContainer获取其对应的buff
        //2.适用buff，比如buff是否生效校验，计算延时，进行位移，cd同步，buff销毁，必要响应客户端等。

        curTickTime=System.currentTimeMillis();
        if (isInited) {
           // battleService.logicTick(curTickTime);
        }
    }

  /*  public void handle(SyncPosReq syncPosReq) {
        battleService.syncPos(syncPosReq);
    }*/

    public void init() {
        battleService= BattleService.getInstance();
        battleService.init(kcpServer);
    }

    /*public void handle(BaseSkillReleaseReq skillReleaseReq) {
        battleService.handleSkillRelease(skillReleaseReq);
    }*/
}
