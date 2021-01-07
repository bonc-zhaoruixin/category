package com.bonc.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: ZhaoRuiXin
 * @Date: Create in 10:05 2021/1/7
 * @Version: 1.0
 * @File: category com.bonc.entity
 * @Description: 菜单数量实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuNumber {

    //名称
    private String name;

    //数量
    private Integer number;
}
