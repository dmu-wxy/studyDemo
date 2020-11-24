package org.meteor.jedis;

import redis.clients.jedis.Jedis;

public class MyJedis {
    public static void main(String[] args) {
        //1.构造jedis对象，默认端口6379，可以不用写
        Jedis jedis = new Jedis("localhost",6379);
        //2.密码认证
        jedis.auth("meteor");
        //3.测试是否连接成功  pong
        System.out.println(jedis.ping());

    }
}
