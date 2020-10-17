package com.fda.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import com.fasterxml.jackson.databind.ObjectMapper;

public class FdaUtils {

	public static <T> T mergeObjects(T first, T second) {
		Class<?> clazz = first.getClass();
		Field[] fields = clazz.getDeclaredFields();
		Object returnValue;
		try {
			returnValue = clazz.newInstance();
			for (Field field : fields) {
				if (Modifier.isFinal(field.getModifiers()))
					continue;
				field.setAccessible(true);
				Object value1 = field.get(first);
				Object value2 = field.get(second);
				Object value = (value1 != null) ? value1 : value2;
				field.set(returnValue, value);
			}
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			returnValue = second;
		}
		return (T) returnValue;
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
