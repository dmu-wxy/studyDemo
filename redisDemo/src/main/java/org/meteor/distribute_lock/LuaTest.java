package org.meteor.distribute_lock;

import org.meteor.jedis.Redis;
import redis.clients.jedis.params.SetParams;

import java.util.Arrays;
import java.util.UUID;

public class LuaTest {
    public static void main(String[] args) {
        Redis redis = new Redis();
        redis.excute(jedis -> {
            //1.获取一个随机字符串
            String value = UUID.randomUUID().toString();
            //2.获取锁
            String k1 = jedis.set("k1", value, new SetParams().nx().ex(5));
            //3.判断是否成功拿到锁
            if(k1 != null && "OK".equals(k1)){
                //拿到了
                //4.业务
                jedis.set("name","meteor");
                System.out.println(jedis.get("name"));
                //5.释放锁
                jedis.evalsha("79372d264938d5a0ab65d2547d95fbe55a97ab31", Arrays.asList("k1"),Arrays.asList(value));
            }else{
                //没拿到
                System.out.println("没拿到锁");
            }
        });
    }
}
