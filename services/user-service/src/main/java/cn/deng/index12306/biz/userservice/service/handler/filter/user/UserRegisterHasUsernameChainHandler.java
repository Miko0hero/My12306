package cn.deng.index12306.biz.userservice.service.handler.filter.user;

import cn.deng.index12306.biz.userservice.common.enums.UserRegisterErrorCodeEnum;
import cn.deng.index12306.biz.userservice.dto.req.UserRegisterReqDTO;
import cn.deng.index12306.biz.userservice.service.UserLoginService;
import cn.deng.index12306.framework.starter.convention.exception.ClientException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Deng
 * @date 2023/8/15
 * @description  判断用户名是否已经被注册过
 */
@Component
@RequiredArgsConstructor
public class UserRegisterHasUsernameChainHandler implements UserRegisterCreateChainFilter<UserRegisterReqDTO> {

    private final UserLoginService userLoginService;

    @Override
    public void handler(UserRegisterReqDTO requestParam) {

        if (userLoginService.hasUsername(requestParam.getUsername())){
            throw new ClientException(UserRegisterErrorCodeEnum.HAS_USERNAME_NOTNULL);
        }
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
