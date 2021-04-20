package com.coder.labsystem.util;

import org.springframework.data.mongodb.core.query.Update;

import java.lang.reflect.Field;

/**
 * @author : JQK
 * @date : 2021-04-20 20:43
 * @description : mongo操作时的工具
 */
public class MongoOperatorUtil {

    /**
     * 将class类对象中所有的属性放入update，以方便执行更新操作
     * @param bean
     * @param <T>
     * @return
     */
    public static <T> Update beanToUpdate(T bean) {
        Class<?> clazz = bean.getClass();
        Update update = new Update();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                update.set(field.getName(), field.get(bean));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return update;
    }

}
