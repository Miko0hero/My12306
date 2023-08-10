package cn.deng.index12306.framework.starter.distributedid.core.serviceid;

import cn.deng.index12306.framework.starter.distributedid.core.IdGenerator;
import cn.deng.index12306.framework.starter.distributedid.core.snowflake.SnowflakeIdInfo;

/**
 * @author Deng
 * @date 2023/8/9
 * @description 业务Id生成器
 */
public interface ServiceIdGenerator extends IdGenerator {

    /**
     * 根据 {@param serviceId} 生成雪花算法 ID
     */
    default long nextId(long serviceId) {
        return 0L;
    }

    /**
     * 根据 {@param serviceId} 生成雪花算法 ID
     */
    default long nextId(String serviceId) {
        return 0L;
    }

    /**
     * 根据 {@param serviceId} 生成字符串类型雪花算法 ID
     */
    default String nextIdStr(long serviceId) {
        return null;
    }

    /**
     * 根据 {@param serviceId} 生成字符串类型雪花算法 ID
     */
    default String nextIdStr(String serviceId) {
        return null;
    }

    /**
     * 解析雪花算法
     */
    SnowflakeIdInfo parseSnowflakeId(long snowflakeId);
}
