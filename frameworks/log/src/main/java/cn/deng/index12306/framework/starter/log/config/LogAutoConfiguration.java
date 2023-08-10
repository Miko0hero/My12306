package cn.deng.index12306.framework.starter.log.config;

import cn.deng.index12306.framework.starter.log.annotation.ILog;
import cn.deng.index12306.framework.starter.log.core.ILogPrintAspect;
import org.springframework.context.annotation.Bean;

/**
 * @author Deng
 * @date 2023/8/9
 * @description 日志自动装配
 */
public class LogAutoConfiguration {
    /**
     * {@link ILog} 日志打印 AOP 切面
     */
    @Bean
    public ILogPrintAspect iLogPrintAspect() {
        return new ILogPrintAspect();
    }
}
