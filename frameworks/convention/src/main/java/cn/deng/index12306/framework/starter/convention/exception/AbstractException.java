package cn.deng.index12306.framework.starter.convention.exception;

import cn.deng.index12306.framework.starter.convention.errorcode.IErrorCode;
import lombok.Getter;

import java.util.Optional;

/**
 * @author Deng
 * @date 2023/8/9
 * @description 抽象项目中三类异常体系，客户端异常、服务端异常以及远程服务调用异常
 */
@Getter
public class AbstractException extends RuntimeException{
    public final String errorCode;

    public final String errorMessage;

    public AbstractException(String message, Throwable throwable, IErrorCode errorCode){
        super(message,throwable);
        this.errorCode=errorCode.code();
        this.errorMessage= Optional.ofNullable(message).orElse(errorCode.code());
    }
}
