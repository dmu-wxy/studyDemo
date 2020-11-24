package org.meteor.limit;

import org.meteor.jedis.CallWithJedis;
import org.meteor.jedis.Redis;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

public class RateLimiter {
    private Jedis jedis;

    public RateLimiter(Jedis jedis) {
        this.jedis = jedis;
    }

    /**
     * 限流方法  适合数据量小
     * @param user 操作用户  限流的对象
     * @param action 具体的操作
     * @param period 时间窗  限流的周期
     * @param maxCount 次数  某一个周期内允许的操作
     * @return
     */
    public boolean isAllowed(String user,String action,int period,int maxCount,int dif){
        //1.数据用zset存，首先生成一个key
        String key = user + "-" + action;
        //2.获取当前时间
        long nowtime = System.currentTimeMillis();
        //3.建立管道
        Pipeline pipelined = jedis.pipelined();
        pipelined.multi();
        //4.将当前的操作保存下来
        pipelined.zadd(key,nowtime,String.valueOf(dif));//使用时间做value会有一些问题
        //5.移除时间窗之外的数据
        pipelined.zremrangeByScore(key,0,nowtime - period * 1000);
        //6.统计剩下的key
        Response<Long> zcard = pipelined.zcard(key);
        //7.将当前key设置一个过期时间，过期时间就是时间窗
        pipelined.expire(key,period + 1);
        //关闭管道
        pipelined.exec();
        pipelined.close();
        //8.返回比较时间窗内的操作数
        System.out.println(zcard.get() + "--" + maxCount);
        return zcard.get() <= maxCount;
    }

    public static void main(String[] args) {
        Redis redis = new Redis();
        redis.excute(new CallWithJedis() {
            @Override
            public void call(Jedis jedis) {
                RateLimiter rateLimiter = new RateLimiter(jedis);
                for(int i = 0;i < 10;i++){
                    System.out.println(rateLimiter.isAllowed("meteor","publish",5,3,i));
                }
            }
        });
    }
}
