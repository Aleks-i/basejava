package com.urise.webapp;

import com.urise.webapp.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {

    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Resume resume = new Resume("Makarov");
        Field field = resume.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(field.get(resume));
        field.set(resume, "new_uuid");

        Method[] methods = resume.getClass().getMethods();
        for (Method method : methods) {
            if ("toString".equals(method.getName())) {
                System.out.println(method.invoke(resume));
            }
        }

        Method method = resume.getClass().getMethod("toString");
        System.out.println(method.invoke(resume));
        System.out.println(resume);
    }
}