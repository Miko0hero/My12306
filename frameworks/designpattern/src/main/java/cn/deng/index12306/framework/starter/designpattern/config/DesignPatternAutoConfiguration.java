package cn.deng.index12306.framework.starter.designpattern.config;

import cn.deng.index12306.framework.starter.bases.config.ApplicationBaseAutoConfiguration;
import cn.deng.index12306.framework.starter.designpattern.chain.AbstractChainContext;
import cn.deng.index12306.framework.starter.designpattern.strategy.AbstractStrategyChoose;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @author Deng
 * @date 2023/8/9
 * @description
 */
@ImportAutoConfiguration(ApplicationBaseAutoConfiguration.class)
public class DesignPatternAutoConfiguration {

    /**
     * 策略模式选择器
     */
    @Bean
    public AbstractStrategyChoose abstractStrategyChoose() {
        return new AbstractStrategyChoose();
    }

    /**
     * 责任链上下文
     */
    @Bean
    public AbstractChainContext abstractChainContext() {
        return new AbstractChainContext();
    }
}
