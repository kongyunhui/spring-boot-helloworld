package com.kyh.statemachine;

import com.kyh.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by kongyunhui on 2017/5/22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class StateMachineTest {
    @Autowired
    private StateMachine<States, Events> stateMachine;

    @Test
    public void test(){
        // 启动状态机（会加锁，进行初始化等操作）
        stateMachine.start();
        // 必须先PAY，再RECEIVE，无法跳过的
        stateMachine.sendEvent(Events.PAY);
//        stateMachine.sendEvent(Events.RECEIVE);
        Message<Events> message = MessageBuilder
                .withPayload(Events.RECEIVE)
                .setHeader("foo", "bar")
                .build();
        stateMachine.sendEvent(message);
        stateMachine.stop();
    }
}
