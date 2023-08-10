package cn.deng.index12306.framework.starter.bases.safa;

import org.springframework.beans.factory.InitializingBean;

/**
 * @author Deng
 * @date 2023/8/8
 * @description FastJson安全模式，开启后关闭类型隐式传递
 */
public class FastJsonSafeMode implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        System.setProperty("fastjson2.parser.safeMode", "true");
    }
}
