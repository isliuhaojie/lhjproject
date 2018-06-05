package com.lhj.test.controller;

import java.io.File;

public class Test {

    @org.junit.Test
    public void getResourcesUrl(){

        String genCfg = "src/main/resources/GeneratorConfiguration.xml";
        File configFile = new File(Test.class.getResource(genCfg).getFile());

        System.out.println(System.getProperty(configFile.getName()));
    }





}
