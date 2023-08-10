package cn.deng.index12306.framework.starter.convention.exception;

import cn.deng.index12306.framework.starter.convention.errorcode.BaseErrorCode;
import cn.deng.index12306.framework.starter.convention.errorcode.IErrorCode;

import java.util.Optional;

/**
 * @author Deng
 * @date 2023/8/9
 * @description
 */
public class ServiceException extends AbstractException {

    public ServiceException(String message) {
        this(message, null, BaseErrorCode.SERVICE_ERROR);
    }

    public ServiceException(IErrorCode errorCode) {
        this(null, errorCode);
    }

    public ServiceException(String message, IErrorCode errorCode) {
        this(message, null, errorCode);
    }

    public ServiceException(String message, Throwable throwable, IErrorCode errorCode) {
        super(Optional.ofNullable(message).orElse(errorCode.message()), throwable, errorCode);
    }

    @Override
    public String toString() {
        return "ServiceException{" +
                "code='" + errorCode + "'," +
                "message='" + errorMessage + "'" +
                '}';
    }
}