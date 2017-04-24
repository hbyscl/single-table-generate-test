package org.cheng.devtool.util;


import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author li.cheng
 * @version 1.0.0 2017年04月24日
 */
public class BeanEntityHelper {

    public static void copyNotNull(Object source,Object target){
        if(null == source || target == null){
            throw new RuntimeException("不能复制空对象:"+source+","+target);
        }
        Class<?> targetClz = target.getClass();
        Class<?> sourceClz = source.getClass();

        try {
            Field[] fields = sourceClz.getDeclaredFields();
            for (Field field : fields) {
                Method sGet = geter(field,sourceClz);
                Object obj = sGet.invoke(source);
                if(null != obj){
                    Method tSet = seter(field,targetClz);
                    tSet.invoke(target,obj);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("复制对象出错："+e.getMessage());
        }
    }

    private static Method geter(Field filed,Class<?> clz) throws NoSuchMethodException {
        String filedName = filed.getName();
        String geter = "get"+filedName.substring(0,1).toUpperCase()+filedName.substring(1);
        return clz.getMethod(geter);
    }

    private static Method seter(Field filed,Class<?> clz) throws NoSuchMethodException {
        String filedName = filed.getName();
        String geter = "set"+filedName.substring(0,1).toUpperCase()+filedName.substring(1);
        return clz.getMethod(geter,filed.getType());
    }


}
