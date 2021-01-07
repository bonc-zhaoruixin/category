package com.bonc.category.mapperTest;

import com.bonc.CategoryApplication;
import com.bonc.dao.CategoryMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

/**
 * @Author: ZhaoRuiXin
 * @Date: Create in 13:45 2021/1/7
 * @Version: 1.0
 * @File: category com.bonc.category.mapperTest
 * @Description:
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CategoryApplication.class)
public class Check {
    @Autowired(required = true)
    CategoryMapper categoryMapper;

    @Test
    public void checkCategoryStatusByCategoryName(){
        HashMap<Object, Object> map = new HashMap<>(16);
        map.put("categoryName","智能城市");
        map.put("categoryParentId","2");
        map.put("departmentId","02");
        categoryMapper.checkCategoryStatusByCategoryName(map);
    }

    @Test
    public void checkCategoryUseStatusByCategoryId(){
        categoryMapper.checkCategoryUseStatusByCategoryId("6");
    }
}
