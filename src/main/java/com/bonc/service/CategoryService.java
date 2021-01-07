package com.bonc.service;

import com.bonc.entity.Category;
import com.bonc.dao.CategoryMapper;
import com.bonc.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bonc.vo.MenuNumber;
import com.bonc.vo.PageResult;
import com.bonc.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Zhao RuiXin
 * @since 2021-01-06
 */
@Service
public class CategoryService extends BaseService{

    @Qualifier("categoryMapper")
    @Autowired
    private CategoryMapper mapper;


    /**
     * 添加类目
     * @param category
     * @return
     */
    public Result insertCategory(Category category) {
        mapper.insertCategory(category);
        return Result.success();
    }

    /**
     * 获取父类目编号
     * @param id
     * @return
     */
    public String selectCategoryParentCode(int id) {
        return mapper.selectParentCategoryCode(id);
    }

    /**
     * 获取父类目名称和数量
     * @param map
     * @return
     */
    public List<MenuNumber> selectParentCategoryTotal(Map map) {
        return mapper.selectParentCategoryTotal(map);
    }

    /**
     * 分页查询，返回数据和总条数
     * @param map
     * @return
     * @throws BindException
     */
    public PageResult selectByPageCategory(HashMap map) throws BindException {
        HashMap<String, Object> pageParams = this.getPageParams();
        map.putAll(pageParams);
        List<Object> pageInfos = mapper.selectByPageCategory(map);
        return new PageResult(map,pageInfos);
    }

    /**
     * 查询类目信息
     * @param categoryId
     * @return
     */
    public Category selectCategoryById(int categoryId) {
        return mapper.selectCategoryById(categoryId);
    }

    /**
     * 修改类目激活状态
     * @param map
     * @return
     */
    public Result updateRunStatus(Map map) {
        mapper.updateRunStatus(map);
        return Result.success();
    }

    /**
     * 修改类目信息
     * @param map
     * @return
     */
    public Result updateCategoryById(Map map) {
        mapper.updateCategoryById(map);
        return Result.success();
    }

    /**
     * 删除类目
     * @param map
     * @return
     */
    public Result deleteCategoryById(Map map) {
        mapper.deleteCategoryById(map);
        return Result.success();
    }

    /**
     * 根据id判断能否被删除
     * @param categoryId
     * @return
     */
    public String checkCategoryUseStatusByCategoryId(String categoryId) {
        return mapper.checkCategoryUseStatusByCategoryId(categoryId);
    }

    /**
     * 根据类目名，判断是否存在该类目
     * @param map
     * @return
     */
    public String checkCategoryStatusByCategoryName(Map map) {
        return mapper.checkCategoryStatusByCategoryName(map);
    }
}
