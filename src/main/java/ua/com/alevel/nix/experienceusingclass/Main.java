package ua.com.alevel.nix.experienceusingclass;

import ua.com.alevel.nix.experienceusingclass.builder.DataBuilder;
import ua.com.alevel.nix.experienceusingclass.config.ApplicationEnvironment;

import java.sql.Date;

public class Main {

    public static void main(String[] args) {
//        classLoaderTest(args);
        runDataBuilder(args);
    }

    private static void classLoaderTest(String[] args) {

        ClassLoader applicationClassLoader = Main.class.getClassLoader();
        printClassLoaderDetails(applicationClassLoader);

        java.sql.Date now = new Date(System.currentTimeMillis());
        ClassLoader platformClassLoder = now.getClass().getClassLoader();
        printClassLoaderDetails(platformClassLoder);

        ClassLoader bootstrapClassLoder = args.getClass().getClassLoader();
        printClassLoaderDetails(bootstrapClassLoder);
    }

    private static void printClassLoaderDetails(ClassLoader classLoader) {
        if (classLoader != null) {
            System.out.println("ClassLoader name : " + classLoader.getName());
            System.out.println("ClassLoader class : " + classLoader.getClass().getName());
        } else {
            System.out.println("Bootstrap classloader");
        }
    }

    private static  void runDataBuilder(String[] args) {
        DataBuilder dataBuilder = new DataBuilder();
        if (args.length != 0) {
            ApplicationEnvironment.setPropertyLang(args[0]);
        } else {
            ApplicationEnvironment.setPropertyLang("en");
        }
        dataBuilder.buildDataList();
    }
}
