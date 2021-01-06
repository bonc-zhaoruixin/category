package com.bonc.service.impl;

import com.bonc.entity.Category;
import com.bonc.dao.CategoryMapper;
import com.bonc.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Zhao RuiXin
 * @since 2021-01-06
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}
