package com.learn.websocket.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;

public class ex {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException {

        Class<?> ex = Class.forName("com.learn.websocket.reflection.beanHandler");
        TypeVariable<? extends Class<?>>[] typeParameters = ex.getTypeParameters();
      Method method =   ex.getMethod("process");
         Method[] methods =  ex.getDeclaredMethods();
         for (Method me : methods){
             System.out.println(me);
             try {
                 me.invoke(ex, (Object[]) null); // Invoke on the instance
             } catch (IllegalAccessException e) {
                 throw new RuntimeException(e);
             } catch (InvocationTargetException e) {
                 throw new RuntimeException(e);
             }
         }
    }
}
