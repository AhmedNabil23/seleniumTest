package com.qacart.todo.utils;

import java.util.Properties;

public class ConfigUtils {

    private Properties propertis ;
    public static ConfigUtils configUtils;

    // عشان اول منده الConfigUtils يقرأ من ال PropertiesFile
    private ConfigUtils(){

        String env = System.getProperty("env","PRODUCTION");
        switch (env) {
            case "PRODUCTION":
                propertis = PropertiesUtils.loadProperties("src/test/java/com/qacart/todo/config/Production.properties");
                break;
            case "LOCAL":
                propertis = PropertiesUtils.loadProperties("src/test/java/com/qacart/todo/config/Local.properties");
                break;
            default:
                throw new RuntimeException("The environment is not supported");
        }
}

// عشان اخد instance من الكلاس, ويقرأ من ال file  مرة واحدة
    public static ConfigUtils getInstance(){
        if(configUtils==null){
            configUtils =new ConfigUtils();}

        return configUtils;
}

//بيرجع ال Url
  public String getBrodUrl(){
        String prop = propertis.getProperty("prdURL");
        if(prop!=null) return prop;
        throw new RuntimeException("Could not find the property in PropertiesFile");
  }

    //بيرجع ال Email
    public String getBrodEmail(){
        String prop = propertis.getProperty("email");
        if(prop !=null) return prop;
        throw new RuntimeException("Could not find the Email in PropertiesFile");
    }

    //بيرجع ال Password
    public String getBrodPassword(){
        String prop = propertis.getProperty("password");
        if(prop !=null) return prop;
        throw new RuntimeException("Could not find the Password in PropertiesFile");
    }

}

