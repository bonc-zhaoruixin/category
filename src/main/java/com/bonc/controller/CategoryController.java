package com.bonc.controller;


import com.bonc.entity.Category;
import com.bonc.service.CategoryService;
import com.bonc.util.RandomCodeUtil;
import com.bonc.vo.MenuNumber;
import com.bonc.vo.PageResult;
import com.bonc.vo.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Zhao RuiXin
 * @since 2021-01-06
 */
@RestController
@RequestMapping("/category")
@CrossOrigin
public class CategoryController {
    @Autowired(required = true)
    CategoryService categoryService;

    /**
     * 添加类目信息
     * @param categoryName
     * @param runStatus
     * @param categoryParentId
     * @return
     */
    @RequestMapping("/insertCategory")
    public Result insertCategory(
            @NotBlank(message = "类目名称不能为空")
            @RequestParam String categoryName,
            @Pattern(regexp = "^\\d+$",message = "runStatus只能是非负数")
            @NotBlank(message = "类目状态不能为空")
            @RequestParam String runStatus,
            @Pattern(regexp = "^\\d+$",message = "categoryParentId只能是非负数")
            @NotBlank(message = "类目父ID不能为空")
            @RequestParam String categoryParentId
    ){
        //判读类目是否存在
        Category category = new Category();
        HashMap<Object, Object> map = new HashMap<>(16);
        //设置用户部门id，设置为静态值01
        String departmentId = "01";
        map.put("categoryName",categoryName);
        map.put("categoryParentId",categoryParentId);
        map.put("departmentId",departmentId);
        //获取数据库中相同名
        String name = categoryService.checkCategoryStatusByCategoryName(map);
        if (StringUtils.isNotEmpty(name)){
            return Result.error("类目已存在！");
        }
        //运行状态
        int status = Integer.parseInt(runStatus);
        //父类目ID
        int parentId = Integer.parseInt(categoryParentId);

        Category categoryById = categoryService.selectCategoryById(Integer.parseInt(categoryParentId));
        Integer level = categoryById.getTreeLevel();

        String parentCode = categoryService.selectCategoryParentCode(parentId);
        //随机生成类目code
        String categoryCode = RandomCodeUtil.makeCode(parentCode);

        //set类目
        category.setCategoryName(categoryName);
        category.setCategoryCode(categoryCode);
        category.setCategoryParentId(parentId);
        category.setRunStatus(status);
        category.setCreator("0117262");
        category.setCreateTime(LocalDateTime.now());
        category.setDeleteFlag(0);
        category.setRemark(categoryName);
        category.setCreatorName("admin");
        category.setDepartmentId(departmentId);
        category.setTreeLevel(level+1);
        Result result = categoryService.insertCategory(category);
        return result;
    }

    /**
     * 获取类目信息
     * @param categoryId
     * @return
     */
    @RequestMapping("selectCategoryById")
    public Result selectCategoryById(
            @Pattern(regexp = "^\\d+$",message = "类目id只能是非负数")
            @NotBlank(message = "类目ID不能为空")
            @RequestParam String categoryId){
        Category category = categoryService.selectCategoryById(Integer.parseInt(categoryId));
        return Result.success(category);
    }

    /**
     * 分页查询
     * @param pageSize
     * @param pageNumber
     * @param categoryName
     * @param categoryId
     * @return
     * @throws BindException
     */
    @RequestMapping("/selectByPageCategory")
    public Result selectByPageCategory(
            @RequestParam("pageSize") String pageSize,
            @RequestParam("pageNumber") String pageNumber,
            @RequestParam String categoryName,
            @Pattern(regexp = "^\\d+$",message = "类目id只能是非负数")
            @RequestParam String categoryId
    ) throws BindException {
        //一级菜单
        HashMap<Object, Object> hashMap = new HashMap<>(16);
        //获取用户部门id
        String departmentId = "01";

        if (StringUtils.isEmpty(categoryId)){
            categoryId = "1";
        }
        hashMap.put("pageSize",pageSize);
        hashMap.put("pageNumber",pageNumber);
        hashMap.put("categoryName",categoryName);
        hashMap.put("categoryId",categoryId);
        hashMap.put("departmentId",departmentId);
        List<MenuNumber> menuNumbers = categoryService.selectParentCategoryTotal(hashMap);
        PageResult pageResult = categoryService.selectByPageCategory(hashMap);

        ArrayList<Object> list = new ArrayList<>();
        list.add(menuNumbers);
        list.add(pageResult);
        Result Result = com.bonc.vo.Result.success(list);
        return Result;
    }

    /**
     * 删除类目
     * @param categoryId
     * @return
     */
    @RequestMapping("deleteCategoryById")
    public Result deleteCategoryById(@Pattern(regexp = "^\\d+$",message = "类目id只能是非负数")
                                     @NotBlank(message = "id不能为空")
                                     @RequestParam String categoryId) {
        String categoryId1 = categoryService.checkCategoryUseStatusByCategoryId(categoryId);
        if (StringUtils.isNotEmpty(categoryId1)) {
            return Result.error("该类目下有API使用");
        }
        HashMap<Object, Object> param = new HashMap<>(16);
        param.put("categoryId", categoryId);
        param.put("deleteFlag", 1);
        Result result = categoryService.deleteCategoryById(param);
        return result;
    }


    /**
     * 修改类目信息
     * @param categoryId
     * @param categoryName
     * @param categoryParentId
     * @param runStatus
     * @return
     */
    @RequestMapping("/updateCategory")
    public Result updateCategory(
            @Pattern(regexp = "^\\d+$",message = "类目id只能是非负数")
            @NotBlank(message = "类目ID不能为空") @RequestParam String categoryId,
            @NotBlank(message = "类目名称不能为空")
            @RequestParam String categoryName,
            @NotBlank(message = "父类目id不能为空")
            @RequestParam String categoryParentId,
            @Pattern(regexp = "^\\d+$",message = "runStatus只能是非负数")
            @NotBlank(message = "类目状态不能为空") @RequestParam String runStatus){

        Integer value = null;

        if (StringUtils.isNotEmpty(runStatus)){
            value = Integer.valueOf(runStatus);
        }
        Map<String, Object> map = new HashMap<>(16);
        map.put("categoryId",categoryId);
        map.put("categoryName",categoryName);
        map.put("runStatus",value);
        //根据id  查看名字
        Category category = categoryService.selectCategoryById(Integer.parseInt(categoryId));
        String paramName = category.getCategoryName();
        if (paramName.equals(categoryName)){
            categoryService.updateCategoryById(map);
            return Result.success();
        }

        //获取用户部门id
        String departmentId = "01";
        //查看数据库是否存在该类目名称
        Map<String, Object> param = new HashMap<>(16);
        param.put("categoryName",categoryName);
        param.put("categoryParentId",categoryParentId);
        param.put("departmentId",departmentId);
        String name = categoryService.checkCategoryStatusByCategoryName(param);
        if (StringUtils.isNotEmpty(name)){
            return Result.error("类目名称已经存在");
        }


        Result result = categoryService.updateCategoryById(map);
        return result;
    }

    /**
     * 修改激活状态
     * @param categoryId
     * @param runStatus
     * @return
     */
    @RequestMapping("/categoryStatus")
    public Result categoryStatus(
            @Pattern(regexp = "^\\d+$",message = "类目id只能是非负数")
            @NotBlank(message = "类目ID不能为空")
            @RequestParam String categoryId,
            @NotBlank(message = "激活状态不能为空")
            @RequestParam String runStatus){

        Map<String, Object> map = new HashMap<>(16);
        map.put("categoryId",categoryId);
        map.put("runStatus",runStatus);
        return categoryService.updateRunStatus(map);
    }
}

