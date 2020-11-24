package org.meteor.jedis;

import redis.clients.jedis.Jedis;

public class ScanTest {
    public static void main(String[] args) {
        Redis redis = new Redis();
        redis.excute(new CallWithJedis() {
            @Override
            public void call(Jedis jedis) {
                for(int i = 0;i < 1000;i++){
                    jedis.set("k" + i,"v" + i);
                }
            }
        });
    }
}
