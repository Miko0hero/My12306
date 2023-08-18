/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.deng.index12306.biz.gatewayservice.filter;

import com.alibaba.nacos.client.naming.utils.CollectionUtils;
import cn.deng.index12306.biz.gatewayservice.config.Config;
import cn.deng.index12306.biz.gatewayservice.toolkit.JWTUtil;
import cn.deng.index12306.biz.gatewayservice.toolkit.UserInfoDTO;
import cn.deng.index12306.framework.starter.bases.constant.UserConstant;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

/**
 * SpringCloud Gateway Token 拦截器
 *
 * @author 邓进伟
 */
@Component
public class TokenValidateGatewayFilterFactory extends AbstractGatewayFilterFactory<Config> {

    public TokenValidateGatewayFilterFactory() {
        super(Config.class);
    }

    /**
     * 注销用户时需要传递 Token 的路径
     */
    public static final String DELETION_PATH = "/api/user-service/deletion";

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            String requestPath = request.getPath().toString();

            // 检查请求路径是否在黑名单列表中
            if (isPathInBlackPreList(requestPath, config.getBlackPathPre())) {
                // 获取请求头中的 Authorization（Token）
                String token = request.getHeaders().getFirst("Authorization");

                // 解析 Token，获取用户信息
                UserInfoDTO userInfo = JWTUtil.parseJwtToken(token);

                // 验证 Token 是否有效
                if (!validateToken(userInfo)) {
                    // 如果 Token 无效，返回 UNAUTHORIZED 响应
                    ServerHttpResponse response = exchange.getResponse();
                    response.setStatusCode(HttpStatus.UNAUTHORIZED);
                    return response.setComplete();
                }

                // 根据请求路径设置请求头信息
                ServerHttpRequest.Builder builder = exchange.getRequest().mutate().headers(httpHeaders -> {
                    httpHeaders.set(UserConstant.USER_ID_KEY, userInfo.getUserId());
                    httpHeaders.set(UserConstant.USER_NAME_KEY, userInfo.getUsername());
                    httpHeaders.set(UserConstant.REAL_NAME_KEY, URLEncoder.encode(userInfo.getRealName(), StandardCharsets.UTF_8));

                    // 如果是注销路径，则设置 USER_TOKEN_KEY
                    if (Objects.equals(requestPath, DELETION_PATH)) {
                        httpHeaders.set(UserConstant.USER_TOKEN_KEY, token);
                    }
                });

                // 继续传递请求给下一个过滤器或路由处理器
                return chain.filter(exchange.mutate().request(builder.build()).build());
            }

            // 如果请求路径不在黑名单中，直接继续传递请求
            return chain.filter(exchange);
        };
    }

    // 检查请求路径是否在黑名单列表中
    private boolean isPathInBlackPreList(String requestPath, List<String> blackPathPre) {
        if (CollectionUtils.isEmpty(blackPathPre)) {
            return false;
        }
        return blackPathPre.stream().anyMatch(requestPath::startsWith);
    }

    // 对 Token 进行验证，只要 userInfo 不为 null，就认为 Token 有效
    private boolean validateToken(UserInfoDTO userInfo) {
        return userInfo != null;
    }
}