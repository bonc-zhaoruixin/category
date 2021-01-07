package com.bonc.category.mapperTest;

import com.bonc.CategoryApplication;
import com.bonc.dao.CategoryMapper;
import com.bonc.entity.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @Author: ZhaoRuiXin
 * @Date: Create in 11:50 2021/1/7
 * @Version: 1.0
 * @File: category com.bonc.category.mapperTest
 * @Description:
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CategoryApplication.class)
public class Insert {
    @Autowired(required = true)
    CategoryMapper categoryMapper;

    @Test
    public void insertCategory(){
        Category category = new Category();
        category.setCategoryName("城市智能");
        category.setCategoryCode("02-01");
        category.setCategoryParentId(02);
        category.setCreator("0117262");
        category.setCreatorName("admin");
        category.setRunStatus(0);
        category.setDeleteFlag(0);
        category.setCreateTime(LocalDateTime.now());
        category.setRemark("城市智能");
        category.setTreeLevel(2);
        category.setDepartmentId("02");
        categoryMapper.insertCategory(category);
    }
}
