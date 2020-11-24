package org.meteor.lettuce;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.dynamic.RedisCommandFactory;
import org.meteor.RedisCommand.RedisCommandInterface;

import java.util.List;

public class ThrottleTest {
    public static void main(String[] args) {
        RedisClient redisClient = RedisClient.create("redis://meteor@smartdog.top");
        StatefulRedisConnection<String, String> connect = redisClient.connect();
        RedisCommandFactory factory = new RedisCommandFactory(connect);
        RedisCommandInterface commands =
                factory.getCommands(RedisCommandInterface.class);
        List<Object> list = commands.throttle("meteor-publish", 10L, 10L, 60L,
                1L);
        System.out.println(list);
    }
}
