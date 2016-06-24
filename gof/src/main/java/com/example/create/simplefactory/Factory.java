package com.example.create.simplefactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 简单工厂
 * Created by AckywOw on 2016/6/12.
 */
public class Factory {

    public static Api createApi() {
        Properties properties = new Properties();
        InputStream in = null;
        System.out.println(new File("gof").getAbsoluteFile());
        System.out.println(Factory.class.getClassLoader().getResource("").getFile());
        String path = "/D:/AndroidProjects/DaggerSession/gof/";
        try {
            in = Factory.class.getResourceAsStream(path + "simplefactory.properties");
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Api api = null;
        try {
            api = (Api) Class.forName(properties.getProperty("ImplClass")).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return api;
    }
}
