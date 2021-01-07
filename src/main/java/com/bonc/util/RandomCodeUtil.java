package com.bonc.util;

import java.util.Random;

/**
 * @Author: ZhaoRuiXin
 * @Date: Create in 14:57 2021/1/7
 * @Version: 1.0
 * @File: category com.bonc.util
 * @Description: 产生随机码
 */
public class RandomCodeUtil {
    /**
     *
     * @param str 父类 category_code
     * @return
     */
    public static String makeCode(String str){
        String number  = String.valueOf(new Random().nextInt(900)+100);
        String time = String.valueOf(System.currentTimeMillis());
        String code = str+"-"+time+"-"+number;
        return code;
    }
}
