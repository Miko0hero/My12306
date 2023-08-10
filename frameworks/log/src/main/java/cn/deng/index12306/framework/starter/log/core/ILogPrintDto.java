package cn.deng.index12306.framework.starter.log.core;

import lombok.Data;

/**
 * @author Deng
 * @date 2023/8/8
 * @description ILog打印实体类
 */
@Data
public class ILogPrintDto {

    /**
     * 开始时间
     */
    private String beginTime;

    /**
     * 请求入参
     */
    private Object[] inputParams;

    /**
     * 返回参数
     */
    private Object outputParams;
}
