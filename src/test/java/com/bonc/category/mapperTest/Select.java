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
 * @Date: Create in 11:29 2021/1/7
 * @Version: 1.0
 * @File: category com.bonc.category.mapperTest
 * @Description: mapper查询测试
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CategoryApplication.class)
public class Select {

    @Autowired(required = true)
    CategoryMapper categoryMapper;

    @Test
    public void selectParentCategoryCode(){
         categoryMapper.selectParentCategoryCode(2);
    }

    @Test
    public void selectParentCategoryTotal(){
        HashMap<Object, Object> map = new HashMap<>();
        categoryMapper.selectParentCategoryTotal(map);
    }

    @Test
    public void selectCategoryById(){
        categoryMapper.selectCategoryById(2);
    }

    @Test
    public void selectByPageCategory(){
        HashMap<Object, Object> map = new HashMap<>();
        categoryMapper.selectByPageCategory(map);
    }
}
