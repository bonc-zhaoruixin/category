package com.bonc.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;

/**
 * @Author: ZhaoRuiXin
 * @Date: Create in 14:00 2021/1/7
 * @Version: 1.0
 * @File: category com.bonc.vo
 * @Description:
 */
@Configuration
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult {
    //总行数
    private Integer total;
    //数据行
    @SuppressWarnings("rawtypes")
    private List rows;

    public PageResult(HashMap<String, Object> map, @SuppressWarnings("rawtypes") List rows) {
        this.total = Integer.parseInt((String)map.get("pageSize"))*Integer.parseInt((String)map.get("pageNumber"));
        this.rows = rows;
    }
}
