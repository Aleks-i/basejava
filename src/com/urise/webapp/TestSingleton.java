package com.urise.webapp;

import com.urise.webapp.model.SectionType;

public class TestSingleton {
    private static TestSingleton instance;

    private TestSingleton() {
    }

    public static TestSingleton getInstance() {
        if (instance == null) {
            instance = new TestSingleton();
        }
        return instance;
    }

    public static void main(String[] args) {
        TestSingleton.getInstance().toString();
        Singleton instance = Singleton.valueOf("INSTANCE");
        System.out.println(instance);
        System.out.println(instance.ordinal());
        for (SectionType type :  SectionType.values()) {
            System.out.println();
            System.out.println(type);
            System.out.println(type.getTitle());
            System.out.println(type.name());
            System.out.println(type.ordinal());
        }
    }

    public enum Singleton {
        INSTANCE
    }
}
