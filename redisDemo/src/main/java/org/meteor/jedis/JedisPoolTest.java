package org.meteor.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisPoolTest {
    public static void main(String[] args) {
//        //1.Jedis连接池
//        JedisPool pool = new JedisPool("localhost", 6379);
//        //2.从连接池中获得一个jedis连接
//        try(Jedis jedis = pool.getResource()){
//            //3.jedis 操作
//            jedis.auth("meteor");
//            System.out.println(jedis.ping());
//        }
        aGoodMethod();
    }

    public static void aGoodMethod(){
        Redis redis = new Redis();
        redis.excute(new CallWithJedis() {
            @Override
            public void call(Jedis jedis) {
                System.out.println(jedis.ping());
            }
        });
    }
}
