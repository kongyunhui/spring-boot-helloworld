package com.kyh.rabbitmq.demo;

import java.io.Serializable;

import com.rabbitmq.client.MessageProperties;
import org.springframework.util.SerializationUtils;


/**
 * The producer endpoint that writes to the queue.
 */
public class Producer extends EndPoint {
    private String bindingKey;
    public void setBindingKey(String bindingKey){
        this.bindingKey = bindingKey;
    }

    public Producer(String exchangeName) throws Exception {
        super(exchangeName);
    }

    public void sendMessage(Serializable object) throws Exception {
        /**
         * @param arg1: exchangeName （""表示默认交换机）
         * @param arg2: queueName | "" | bindingKey ==> exchange对应：default | fanout | direct
         * @param arg3: MessageProperties 消息属性 （消息持久性：写入磁盘/缓存。简单持久性，如需强大的保证use publisher confirms）
         * @param byte[]: 消息字节数组
         */
        channel.basicPublish(exchangeName, bindingKey, MessageProperties.PERSISTENT_TEXT_PLAIN, SerializationUtils.serialize(object));
    }
}