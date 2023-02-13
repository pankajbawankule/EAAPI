package com.ea.util;
import java.util.*;
import java.io.*;


public class Props {

    public String getProperty(String key)throws Exception{
        InputStream input = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/"+getEnvironmentUnderTest()+".properties");
        Properties properties = new Properties();
        properties.load(input);
        if(key == null || key.isEmpty()){
            throw new Exception("No key found");
        }else {
            return properties.getProperty(key);
        }

    }

    private String getEnvironmentUnderTest() throws Exception{

        InputStream input = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/environment.properties");
        Properties properties = new Properties();
        properties.load(input);
            return properties.getProperty("env");
    }

    /*public static void main(String[] args) throws Exception{

        Props props = new Props();
        System.out.println("Test:"+props.getProperty("baseurl"));
    }*/

    }
