package cn.deng.index12306.framework.starter.log.core;

import cn.deng.index12306.framework.starter.log.annotation.ILog;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.SystemClock;
import com.alibaba.fastjson2.JSON;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Method;
import java.util.Optional;

/**
 * @author Deng
 * @date 2023/8/8
 * @description
 */
@Aspect
@Slf4j
public class ILogPrintAspect {
    @Around("@within(cn.deng.index12306.framework.starter.log.annotation.ILog) || @annotation(cn.deng.index12306.framework.starter.log.annotation.ILog)")
    public Object printLog(ProceedingJoinPoint joinPoint) throws Throwable {
        //记录方法开始执行的时间
        long startTime = SystemClock.now();
        //通过反射获取方法签名和类的类型
        MethodSignature methodSignature=(MethodSignature) joinPoint.getSignature();

        //获取方法开始执行的时间
        String beginTime= DateUtil.now();
        Object result=null;
        try{
            //执行切点方法，获取返回结果
            result=joinPoint.proceed();
        }finally{
            //获取目标方法对象
            Method targetMethod=joinPoint.getTarget().getClass().getDeclaredMethod(methodSignature.getName(),methodSignature.getParameterTypes());
            //获得方法上的@ILog注解，如果没有获取到，尝试获取目标对象上的注解
            ILog logAnnotation= Optional.ofNullable(targetMethod.getAnnotation(ILog.class)).orElse(joinPoint.getTarget().getClass().getAnnotation(ILog.class));

            if(logAnnotation !=null){
                //构建日志信息
                ILogPrintDto logPrintDto=new ILogPrintDto();
                logPrintDto.setBeginTime(beginTime);
                //如果@ILog注解要求记录输入参数，则构建输入参数
                if(logAnnotation.input()){
                    logPrintDto.setInputParams(buildInput((joinPoint)));
                }

                // 如果 @ILog 注解要求记录输出参数，则设置输出参数
                if (logAnnotation.output()) {
                    logPrintDto.setOutputParams(result);
                }
                String methodType = "", requestUri = "";
                try {
                    // 获取请求的方法类型和URI
                    ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                    assert servletRequestAttributes != null;
                    methodType = servletRequestAttributes.getRequest().getMethod();
                    requestUri = servletRequestAttributes.getRequest().getRequestURI();
                } catch (Exception ignored) {
                }
                log.info("[{}] {}, executeTime: {}ms, info: {}", methodType, requestUri, SystemClock.now() - startTime, JSON.toJSONString(logPrintDto));
            }
        }
        return result;
    }
    // 构建输入参数数组，用于日志记录
    private Object[] buildInput(ProceedingJoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Object[] printArgs = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            // 如果参数是 HttpServletRequest 或 HttpServletResponse，不记录
            if ((args[i] instanceof HttpServletRequest) || args[i] instanceof HttpServletResponse) {
                continue;
            }
            // 如果参数是 byte[]，记录为 "byte array"
            if (args[i] instanceof byte[]) {
                printArgs[i] = "byte array";
                // 如果参数是 MultipartFile，记录为 "file"
            } else if (args[i] instanceof MultipartFile) {
                printArgs[i] = "file";
            } else {
                // 其他情况，记录参数值
                printArgs[i] = args[i];
            }
        }
        return printArgs;
    }
}
