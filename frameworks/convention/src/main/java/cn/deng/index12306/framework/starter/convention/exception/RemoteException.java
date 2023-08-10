package cn.deng.index12306.framework.starter.convention.exception;

import cn.deng.index12306.framework.starter.convention.errorcode.BaseErrorCode;
import cn.deng.index12306.framework.starter.convention.errorcode.IErrorCode;

/**
 * @author Deng
 * @date 2023/8/9
 * @description
 */
public class RemoteException extends AbstractException{

    public RemoteException(String message) {
        this(message, null, BaseErrorCode.REMOTE_ERROR);
    }

    public RemoteException(String message, IErrorCode errorCode) {
        this(message, null, errorCode);
    }

    public RemoteException(String message, Throwable throwable, IErrorCode errorCode) {
        super(message, throwable, errorCode);
    }

    @Override
    public String toString() {
        return "RemoteException{" +
                "code='" + errorCode + "'," +
                "message='" + errorMessage + "'" +
                '}';
    }
}
