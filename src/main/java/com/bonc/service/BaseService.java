package com.bonc.service;

import com.bonc.constant.PageParamsConstant;
import com.bonc.util.RegexUtil;
import com.bonc.util.RequestUtil;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * @Author: ZhaoRuiXin
 * @Date: Create in 14:13 2021/1/7
 * @Version: 1.0
 * @File: category com.bonc.service
 * @Description:
 */
public class BaseService {
    /**
     * 分页参数校验
     *
     * @return
     * @throws BindException
     */
    public HashMap<String, Object> getPageParams() throws BindException {
        HttpServletRequest request = getRequest();
        //每页记录数
        String pageSize = request.getParameter(PageParamsConstant.PAGE_SIZE);
        //当前页数码
        String pageNumber = request.getParameter(PageParamsConstant.PAGE_NUMBER);
        // 排序字段
        String orderBy = request.getParameter(PageParamsConstant.ORDER_BY);
        //排序方式，降序（desc） 或者升序（asc）
        String sort = request.getParameter(PageParamsConstant.SORT);
        //是否全部查询
        String findAll = request.getParameter(PageParamsConstant.FIND_ALL);

        if (RegexUtil.isStrictBlank(findAll) || PageParamsConstant.FALSE.equalsIgnoreCase(findAll)) {
            if (pageSize == null || !RegexUtil.isPositiveInteger(pageSize)) {
                throw getBindException("pageSize只能为正整数");
            }
            if (pageNumber == null || !RegexUtil.isPositiveInteger(pageNumber)) {
                throw getBindException("pageNumber只能为正整数");
            }
            if (!RegexUtil.isStrictBlank(sort)) {
                if (RegexUtil.isGeneralizedBlank(orderBy)) {
                    throw getBindException("排序方式非空，排序字段也不能为空");
                } else if (!RegexUtil.isGeneralizedBlank(orderBy)
                        && !sort.toLowerCase().equals(PageParamsConstant.ASC)
                        && !sort.toLowerCase().equals(PageParamsConstant.DESC)) {
                    throw getBindException("sort的值应该为asc或desc，不区分大小写");
                }
            }
        } else if (!PageParamsConstant.TRUE.equalsIgnoreCase(findAll)) {
            throw getBindException("findAll只能为空对象、空字符串或布尔值");
        }

        HashMap<String, Object> map = new HashMap<String, Object>(16);
        map.put("pageSize", pageSize);
        map.put("currentPage", pageNumber);
        map.put("orderBy", orderBy);
        map.put("sort", sort);
        map.put("findAll", Boolean.parseBoolean(findAll));
        return map;
    }

    protected String getColumnName(String filed) {
        StringBuffer sb = new StringBuffer();
        for (char c : filed.toCharArray()) {
            if (c >= 'A' && c < 'Z') {
                sb.append(("_" + c).toLowerCase());
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 获取 HttpServletRequest
     */
    public static HttpServletResponse getResponse() {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        return response;
    }

    /**
     * 获取 包装防Xss Sql注入的 HttpServletRequest
     *
     * @return request
     */
    public static HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return new RequestUtil(request);
    }


    /**
     * 获取BindException对象
     *
     * @param message
     * @return
     */
    private static BindException getBindException(String message) {
        BindException bindException = new BindException("", "");
        FieldError fieldError = new FieldError("", "", message);
        bindException.addError(fieldError);
        return bindException;
    }
}
