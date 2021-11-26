package com.wisry.d_end.core.service;

import com.wisry.d_end.net.DeKcpServer;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class BattleService {
    private static BattleService battleService = new BattleService();

    private BattleService() {
    }

    public static BattleService getInstance() {
        return battleService;
    }

    public void init(DeKcpServer kcpServer) {

    }
}
