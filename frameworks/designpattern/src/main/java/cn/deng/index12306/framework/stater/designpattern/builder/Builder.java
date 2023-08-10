package cn.deng.index12306.framework.stater.designpattern.builder;

import java.io.Serializable;

/**
 * @author Deng
 * @date 2023/8/9
 * @description Builder 模式抽象接口
 */
public interface Builder<T> extends Serializable {

    /**
     * 构建方法
     *
     * @return 构建后的对象
     */
    T build();
}
