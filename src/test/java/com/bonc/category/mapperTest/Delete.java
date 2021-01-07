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
 * @Date: Create in 13:41 2021/1/7
 * @Version: 1.0
 * @File: category com.bonc.category.mapperTest
 * @Description:
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CategoryApplication.class)
public class Delete {
    @Autowired(required = true)
    CategoryMapper categoryMapper;

    @Test
    public void deleteCategoryById(){
        HashMap<Object, Object> map = new HashMap<>(16);
        map.put("categoryId",6);
        map.put("deleteFlag",1);
        categoryMapper.deleteCategoryById(map);
    }
}
