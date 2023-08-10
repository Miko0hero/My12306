package cn.deng.index12306.framework.starter.convention.errorcode;

/**
 * @author Deng
 * @date 2023/8/9
 * @description
 */
public interface IErrorCode {

    /**
     * 错误码
     */
    String code();

    /**
     * 错误信息
     */
    String message();
}
