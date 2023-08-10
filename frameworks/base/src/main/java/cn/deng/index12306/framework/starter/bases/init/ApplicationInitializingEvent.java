package cn.deng.index12306.framework.starter.bases.init;

import org.springframework.context.ApplicationEvent;

/**
 * @author Deng
 * @date 2023/8/8
 * @description 规约事件类
 */
public class ApplicationInitializingEvent extends ApplicationEvent {

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public ApplicationInitializingEvent(Object source) {
        super(source);
    }
}
