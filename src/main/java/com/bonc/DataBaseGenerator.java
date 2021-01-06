package com.bonc;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: ZhaoRuiXin
 * @Date: Create in 15:50 2021/1/6
 * @Version: 1.0
 * @File: category com.bonc.category.util
 * @Description:
 */
public class DataBaseGenerator {
    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();

        /*--配置数据源--*/
        DataSourceConfig dsc = new DataSourceConfig();
        // 设置数据库类型为mysql
        dsc.setDbType(DbType.MYSQL);
        // 设置引擎名
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        // 设置数据库url
        dsc.setUrl("jdbc:mysql://localhost:3306/api_server?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=Asia/Shanghai");
        // 设置用户名
        dsc.setUsername("root");
        // 设置用户密码
        dsc.setPassword("root");
        //数据源配置
        mpg.setDataSource(dsc);

        /*全局配置*/
        GlobalConfig gc = new GlobalConfig();
        // 设置输出目录
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setFileOverride(true);
        gc.setActiveRecord(true);
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(true);// XML columList
        // 设置作者
        gc.setAuthor("Zhao RuiXin");
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setControllerName("%sController");
        //全局配置
        mpg.setGlobalConfig(gc);

        /*--生成策略配置--*/
        StrategyConfig strategy = new StrategyConfig();
        //strategy.setTablePrefix(new String[] { "SYS_" });
        // 此处可以修改为您的表前缀
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 表名生成策略
        strategy.setInclude(new String[] {"category"});
        // 需要生成的表
        //生成策略配置
        mpg.setStrategy(strategy);


        /*--生成文件所在包配置--*/
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.bonc");
        pc.setController("controller");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setEntity("entity");
        pc.setMapper("dao");
        //包配置
        mpg.setPackageInfo(pc);

        /*--xml文件配置*/
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-rb");
                this.setMap(map);
            }
        };
        //xml生成路径
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return "src/main/resources/"+ "/mybatis/mapper/" +tableInfo.getEntityName()+"Mapper.xml";
            }
        });
        cfg.setFileOutConfigList(focList);
        //xml配置
        mpg.setCfg(cfg);

        // 关闭默认 xml 生成，调整生成 至 根目录
        TemplateConfig tc = new TemplateConfig();
        tc.setXml(null);
        mpg.setTemplate(tc);
        /*--自动生成==*/

        // 执行生成
        mpg.execute();


    }
}
