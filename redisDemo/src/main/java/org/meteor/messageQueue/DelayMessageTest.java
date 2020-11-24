package org.meteor.messageQueue;

import org.meteor.jedis.Redis;

public class DelayMessageTest {
    public static void main(String[] args) {
        Redis redis = new Redis();
        redis.excute(jedis -> {
            //构建消息队列
            DelayMessageQueue delayMessageQueue = new DelayMessageQueue(jedis, "meteor-delay-queue");
            //消息生产者
            Thread thread = new Thread(){
                @Override
                public void run() {
                    for(int i = 0;i < 5;i++){
                        delayMessageQueue.queue("meteor send : " + i);
                    }
                }
            };
            //消息消费者
            Thread thread2 = new Thread(){
                @Override
                public void run() {
                    delayMessageQueue.loop();
                }
            };
            //启动
            thread.start();
            thread2.start();
            //休息7秒，停止程序
            try {
                Thread.sleep(7000);
                thread2.interrupt();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
