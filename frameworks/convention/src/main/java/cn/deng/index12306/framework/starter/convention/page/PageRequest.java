package cn.deng.index12306.framework.starter.convention.page;

import lombok.Data;

/**
 * @author Deng
 * @date 2023/8/9
 * @description 分页请求对象
 */
@Data
public class PageRequest {

    /**
     * 当前页
     */
    private Long current = 1L;

    /**
     * 每页显示条数
     */
    private Long size = 10L;
}
