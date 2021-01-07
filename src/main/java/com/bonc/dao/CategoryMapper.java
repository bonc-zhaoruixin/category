package com.bonc.dao;

import com.bonc.entity.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bonc.vo.MenuNumber;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Zhao RuiXin
 * @since 2021-01-06
 */
@Component(value = "categoryMapper")
public interface CategoryMapper extends BaseMapper<Category> {


    /**
     * 添加新类目
     * @param category
     */
    void insertCategory(Category category);

    /**
     * 查询父类目编号
     * @param id
     * @return
     */
    String selectParentCategoryCode(int id);

    /**
     * 查询父类目名称和数量
     * @param map
     * @return
     */
    List<MenuNumber> selectParentCategoryTotal(Map map);

    /**
     * 通过id查询类目信息
     * @param categoryId
     * @return category
     */
    Category selectCategoryById(int categoryId);

    /**
     * 分页查询
     * @param map
     * @return
     */
    List<Object> selectByPageCategory(Map map);

    /**
     * 通过id修改类目信息
     * @param map
     */
    void updateCategoryById(Map map);

    /**
     * 修改类目激活状态
     * @param map
     */
    void updateRunStatus(Map map);

    /**
     * 通过id删除类目
     * @param map
     */
    void deleteCategoryById(Map map);

    /**
     * 通过类目名称查询，用来判断类目是否存在
     * @param map
     * @return
     */
    String checkCategoryStatusByCategoryName(Map map);

    /**
     * 检查类目是否正在被使用
     * @param categoryId
     * @return
     */
    String checkCategoryUseStatusByCategoryId(String categoryId);
}
