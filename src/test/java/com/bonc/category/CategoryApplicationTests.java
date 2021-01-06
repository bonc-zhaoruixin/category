package com.bonc.category;

import com.bonc.CategoryApplication;
import com.bonc.dao.CategoryMapper;
import com.bonc.entity.Category;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CategoryApplication.class)
class CategoryApplicationTests {

    @Autowired(required = true)
    CategoryMapper mapper;

    @Test
    void contextLoads() {
        // 1、测试列表，成功
        List<Category> list = mapper.selectList(null);
        list.forEach(System.out::println);
    }
}
