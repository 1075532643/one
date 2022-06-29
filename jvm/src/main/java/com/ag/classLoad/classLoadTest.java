package com.ag.classLoad;

import java.net.URL;
import java.net.URLClassLoader;

public class classLoadTest {

    public static void main(String[] args) {
        URLClassLoader extensionLoader = (URLClassLoader)ClassLoader.getSystemClassLoader().getParent();
        URL[] urLs = extensionLoader.getURLs();
        for (URL urL : urLs) {
            System.out.println(urL);

        }


    }
}
