package org.meteor.RedisCommand;

import io.lettuce.core.dynamic.Commands;
import io.lettuce.core.dynamic.annotation.Command;

import java.util.List;

public interface RedisCommandInterface extends Commands {
    @Command("cl.throttle ?0 ?1 ?2 ?3 ?4")
    List<Object> throttle(String key,long init,long count,long period,long quota);
}
