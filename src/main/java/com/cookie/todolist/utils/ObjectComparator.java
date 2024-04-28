package com.cookie.todolist.utils;

import java.lang.reflect.Field;

public class ObjectComparator {

    public static boolean compareObjects(Object obj1, Object obj2) throws IllegalAccessException {
        if (obj1 == null || obj2 == null || obj1.getClass() != obj2.getClass()) {
            return false;
        }

        Field[] fields = obj1.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object value1 = field.get(obj1);
            Object value2 = field.get(obj2);

            if (value1 == null && value2 == null) {
                continue;
            }

            if (value1 == null || !value1.equals(value2)) {
                return false;
            }
        }

        return true;
    }
}
