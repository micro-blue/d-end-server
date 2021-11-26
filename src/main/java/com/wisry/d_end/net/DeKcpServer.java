package com.wisry.d_end.net;


import com.wisry.d_end.core.Leader;
import com.wisry.d_end.net.dispatcher.ReqDispatcher;
import com.wisry.d_end.util.GsonUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.beykery.jkcp.KcpOnUdp;
import org.beykery.jkcp.KcpServer;

import java.util.*;

public class DeKcpServer extends KcpServer {
    public Map<KcpOnUdp, List<ByteBuf>> sessionMap = new HashMap<>();

    public DeKcpServer(int port, int workerSize, Runnable logicTick) {
        super(port,workerSize,logicTick);
    }

    /*public static NsKcpServer initServer() {
        NsKcpServer s = new NsKcpServer(2222, 1);
        s.noDelay(1, 10, 2, 1);
        s.setMinRto(10);
        s.wndSize(64, 64);
        s.setTimeout(10 * 1000);
        s.setMtu(512);
        s.start();
        ReqDispatcher.getInstance().getReqController().setLeader(Leader.getInstance());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("server start");
        return s;
    }*/
    public static void main(String[] args) {
        Leader leader = Leader.getInstance();
        DeKcpServer s = new DeKcpServer(2222, 1,leader);
        leader.setKcpServer(s);
        s.noDelay(1, 50, 2, 1);
        s.setMinRto(10);
        s.wndSize(64, 64);
        s.setTimeout(1000 * 1000);
        s.setMtu(512);
        s.start();
        ReqDispatcher.getInstance().getReqController().setLeader(Leader.getInstance());
        leader.init();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //System.out.println("server start");
    }
    @Override
    public void handleReceive(ByteBuf byteBuf, KcpOnUdp session) {
        if (!sessionMap.containsKey(session)) {
            sessionMap.put(session, new ArrayList<>());
        }

        DeMsg deMsg =MsgUtil.parseReqFromByteBuf(byteBuf);
        byteBuf.release();
        Class dataClazz = MsgUtil.getClazzByCode(deMsg.getCode());

        Object data = deMsg.getData();
        /*String dataJson = GsonUtil.GsonString(data);
        InitColliderTransformReq initColliderTransformReq = GsonUtil.GsonToBean(dataJson, InitColliderTransformReq.class);*/
        ReqDispatcher.getInstance().handleRequest(dataClazz, data);
    }

    @Override
    public void handleException(Throwable throwable, KcpOnUdp kcpOnUdp) {
        System.out.println(throwable + "--------" + kcpOnUdp.getLocal().toString());
    }

    @Override
    public void handleClose(KcpOnUdp kcp) {
        System.out.println(new Date());
        System.out.println("客户端离开:" + kcp);
    }

    public void broadCast(DeMsg msg) {
        String s = GsonUtil.GsonString(msg);
        byte[] bytes = s.getBytes();
        ByteBuf buf = Unpooled.directBuffer();// 非池化堆内存
        buf.writeBytes(bytes);


        if (sessionMap.keySet().size() > 1) {
            buf.retain(sessionMap.keySet().size() - 1);
        }

        for (KcpOnUdp kcpOnUdp : sessionMap.keySet()) {
            kcpOnUdp.send(buf);
        }
    }

    /*public NsKcpServer(int port, int workerSize) {
        super(port, workerSize);
    }*/
}
