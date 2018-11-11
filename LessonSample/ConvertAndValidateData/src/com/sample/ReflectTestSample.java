package com.sample;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectTestSample {

	public static void main(String[] args)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		Class<?> userClass = Class.forName("com.sample.User");
		//查看类里面声明的变量
		System.out.println(userClass.getName() + " 类里面生命的变量");
		for(Field field : userClass.getDeclaredFields()) {
			System.out.println(field);
		}
		System.out.println(userClass.getName() + " 类里面生命的方法");
		//查看类里面声明的方法
		for(Method method : userClass.getDeclaredMethods()) {
			System.out.println(method);
		}
		
		//调用默认构造函数
		System.out.println("调用无参构造函数创建实例");
		Object userObj1 = userClass.newInstance();
		Method methodSetAccount = userClass.getDeclaredMethod("setAccount", String.class);
		methodSetAccount.invoke(userObj1, "sa");
		System.out.println(userObj1);
		//调用带参构造函数
		System.out.println("调用有参构造函数创建实例");
		Object userObj2= userClass.getConstructor(String.class,String.class).newInstance("admin","123");
		System.out.println(userObj2);
	}
	
}
