package cn.deng.index12306.framework.starter.distributedid.core;

/**
 * @author Deng
 * @date 2023/8/9
 * @description ID生成器
 */
public interface IdGenerator {

    /**
     * 下一个 ID
     */
    default long nextId() {
        return 0L;
    }

    /**
     * 下一个 ID 字符串
     */
    default String nextIdStr() {
        return "";
    }
}
