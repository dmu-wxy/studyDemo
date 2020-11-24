package org.meteor.Bloom;

import io.rebloom.client.Client;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.JedisPool;

public class BloomFilter {
    public static void main(String[] args) {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxIdle(300);
        config.setMaxTotal(1000);
        config.setMaxWaitMillis(30000);
        config.setTestOnBorrow(true);
        JedisPool jedisPool = new JedisPool(config, "smartdog.top", 6379, 30000, "meteor");
        Client client = new Client(jedisPool);
        long start = System.currentTimeMillis();
        int temp = 0;
        for (int i = 0;i < 2000;i++){
            client.add("name","meteor-" + i);
            long end = System.currentTimeMillis();
            if(end - start > 1000){
                start = end;
                System.out.println("--" + (i - temp) + "--");
                temp = i;
            }
        }
        boolean exists = client.exists("name", "meteor-9");
        System.out.println(exists);
    }
}
