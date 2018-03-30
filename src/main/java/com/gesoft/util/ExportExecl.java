package com.gesoft.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
/**
 * Created by lz on 2018-3-12.
 */
public class ExportExecl {
    private static Configuration configuration =null;
    private static Map<String, Template> allTemplates =null;
    private static String realPath = getWEBINFOPath();
    
    /**
     * 创建excel
     * @param dataMap
     * @param type
     * @return
     */
    public static File createExcel(Map<?, ?> dataMap, String type,String valueName){
        try {
            configuration = new Configuration();
            configuration.setDefaultEncoding("UTF-8");
            configuration.setDirectoryForTemplateLoading(new File(realPath));
            allTemplates = new HashMap<String, Template>();
            allTemplates.put(type, configuration.getTemplate(valueName));
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        String name = "temp" + (int) (Math.random() * 100000) + ".xls";
        File file = new File(name);
        Template template = allTemplates.get(type);
        try {
            Writer w = new OutputStreamWriter(new FileOutputStream(file), "utf-8");
            template.process(dataMap, w);
            w.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return file;
    }
    
    /**
     * 模板读取路径
     * @return
     */
    public static String getWEBINFOPath(){
        String configpath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        configpath = configpath.replace("classes/", "template/");
        return configpath;
    }
}
