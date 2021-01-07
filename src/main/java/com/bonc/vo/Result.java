package com.bonc.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: ZhaoRuiXin
 * @Date: Create in 9:16 2021/1/7
 * @Version: 1.0
 * @File: category com.bonc.entity
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    Integer state;
    String msg;
    Object data;

    public Result(Integer state, String msg) {
        this.state = state;
        this.msg = msg;
    }

    public static Result success(){
        return new Result(1,"请求成功");
    }

    public static Result success(String msg){
        return new Result(1,msg);
    }

    public static Result success(Object o){
        return new Result(1,"请求成功",o);
    }

    public static Result error(){
        return new Result(0,"请求失败");
    }

    public static Result error(String msg){
        return new Result(0,msg);
    }

    public static Result error(Object o){
        return new Result(0,"请求失败",o);
    }

    public static Result login(){
        return new Result(2,"请您登录账号");
    }

    public static Result relogin(){
        return new Result(3,"您的账号已超时，请重新登录！");
    }

    public static Result relogin1(){
        return new Result(3,"您的账号已在别处登录，请重新登录！");
    }
}
