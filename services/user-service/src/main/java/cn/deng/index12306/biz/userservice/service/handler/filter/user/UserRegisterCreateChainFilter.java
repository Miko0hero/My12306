package cn.deng.index12306.biz.userservice.service.handler.filter.user;

import cn.deng.index12306.biz.userservice.common.enums.UserChainMarkEnum;
import cn.deng.index12306.biz.userservice.dto.req.UserRegisterReqDTO;
import cn.deng.index12306.framework.starter.designpattern.chain.AbstractChainHandler;

/**
 * @author Deng
 * @date 2023/8/15
 * @description
 */
public interface UserRegisterCreateChainFilter<T extends UserRegisterReqDTO> extends AbstractChainHandler<UserRegisterReqDTO> {
    @Override
   default String mark(){
     return UserChainMarkEnum.USER_REGISTER_FILTER.name();
    }
}
