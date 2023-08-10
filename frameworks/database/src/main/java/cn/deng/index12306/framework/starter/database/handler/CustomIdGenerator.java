package cn.deng.index12306.framework.starter.database.handler;

import cn.deng.index12306.framework.starter.distributedid.toolkit.SnowflakeIdUtil;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;

/**
 * @author Deng
 * @date 2023/8/10
 * @description 自定义雪花生成器
 */
public class CustomIdGenerator implements IdentifierGenerator {

    @Override
    public Number nextId(Object entity) {
        return SnowflakeIdUtil.nextId();
    }
}
