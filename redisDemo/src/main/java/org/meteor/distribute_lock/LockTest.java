package org.meteor.distribute_lock;

import org.meteor.jedis.Redis;
import redis.clients.jedis.params.SetParams;

public class LockTest {
    public static void main(String[] args) {
        Redis redis = new Redis();
        redis.excute(jedis -> {
            String set = jedis.set("k1", "v1", new SetParams().nx().ex(5));
            if(set != null && "OK".equals(set)){
                //没人站位
                jedis.set("name","meteor");
                System.out.println(jedis.get("name"));
                jedis.del("k1");
            }else{
                //有人站位
            }
        });
    }
}
