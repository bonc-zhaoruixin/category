package com.bonc.category.mapperTest;

import com.bonc.CategoryApplication;
import com.bonc.dao.CategoryMapper;
import com.bonc.entity.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ZhaoRuiXin
 * @Date: Create in 13:02 2021/1/7
 * @Version: 1.0
 * @File: category com.bonc.category.mapperTest
 * @Description:
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CategoryApplication.class)
public class Update {

    @Autowired(required = true)
    CategoryMapper categoryMapper;

    @Test
    public void updateCategoryById() {
        Map<String, Object> map = new HashMap<>(16);
        map.put("categoryId", 6);
        map.put("categoryName", "城市智能");
        map.put("runStatus", 0);
        //根据id  查看名字
        Category category = categoryMapper.selectCategoryById(Integer.parseInt("6"));
        String paramName = category.getCategoryName();

        if (paramName.equals("智能城市")) {
            categoryMapper.updateCategoryById(map);
        }
    }

    @Test
    public void updateRunStatus () {
        HashMap<Object, Object> map = new HashMap<>(16);
        map.put("categoryId",6);
        map.put("runStatus",1);
        categoryMapper.updateRunStatus(map);
    }
}
