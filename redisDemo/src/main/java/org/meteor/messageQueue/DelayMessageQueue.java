package org.meteor.messageQueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.meteor.model.message;
import redis.clients.jedis.Jedis;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

public class DelayMessageQueue {
    private Jedis jedis;
    private String queue;

    public DelayMessageQueue(Jedis jedis, String queue) {
        this.jedis = jedis;
        this.queue = queue;
    }

    /**
     * 消息入队
     * @param obj 要发送的消息
     */
    public void queue(Object obj){
        //构造消息
        message m = new message();
        m.setId(UUID.randomUUID().toString());
        m.setData(obj);
        try {
            //序列化
            String s = new ObjectMapper().writeValueAsString(m);
            //消息发送，延迟5秒
            System.out.println("msg send:" + new Date());
            jedis.zadd(queue,System.currentTimeMillis()+5000,s);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 消息消费
     */
    public void loop(){
        //只要线程未被打断
        while(!Thread.interrupted()){
            //从0到当前时间之间的，偏移量0，第一个
            Set<String> strings = jedis.zrangeByScore(queue, 0, System.currentTimeMillis(), 0, 1);
            if(strings.isEmpty()){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
                continue;
            }else{
                //读取到了消息
                String next = strings.iterator().next();
                if(jedis.zrem(queue,next) > 0){
                    //抢到消息，处理业务
                    try {
                        message message = new ObjectMapper().readValue(next, message.class);
                        System.out.println(message);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
