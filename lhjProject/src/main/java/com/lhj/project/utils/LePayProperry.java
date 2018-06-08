package com.lhj.project.utils;

import java.io.InputStream;
import java.util.Properties;

import static jdk.nashorn.internal.codegen.OptimisticTypesPersistence.load;

/**
 * describe
 * author liuhj18
 * Date 2018/6/8
 * version 1.00
 */
public class LePayProperry extends Properties {

    /**
     *
     */
    private static final long serialVersionUID = -7738763932265633L;
    private static String CONFIG_FILE = "lePay.properties";
    private static LePayProperry p;
    private LePayProperry(){
        try {
            ClassLoader classLoader = Thread.currentThread()
                    .getContextClassLoader();
            InputStream is = classLoader.getResourceAsStream(CONFIG_FILE);
            load(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static LePayProperry getInstance(){
        if(p == null){
            synchronized(LePayProperry.class){
                if(p == null){
                    p = new LePayProperry();
                }
            }
        }
        return p;
    }


}
