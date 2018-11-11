package com.sample;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Set;

import javax.validation.Configuration;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableValidator;
import org.apache.log4j.Logger;

public class UserValidatorSample {
	static Logger log = Logger.getLogger(UserValidatorSample.class);

	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		System.out.println("\r\n---通过注解进行数据验证---");
		dataValidationByAnnotations();
		System.out.println("\r\n---通过配置文件进行数据验证---");
		dataValidationByXmlConfig();
		System.out.println("\r\n---校验函数的输入参数---");
		validateFuncInputParams();
		System.out.println("\r\n---校验函数的返回值---");
		validateFuncRetVal();
		System.out.println("\r\n---校验函数的构造函数的输入参数---");
		validateConstructorInputParams();
	}

	// 通过注解进行数据验证
	public static void dataValidationByAnnotations() {
		User user = new User();
		user.setAccount("sa");
		user.setPasswd("123");
		user.setSex("男女");
		user.setSalary(90000.0);
		user.setBirthday(new Date());

		ValidatorFactory validatorFactory = Validation.byDefaultProvider().configure()
				.buildValidatorFactory();
		Validator validator = validatorFactory.getValidator();
		Set<ConstraintViolation<User>> constraintViolationSet = validator.validate(user);
		printValidationInfo(constraintViolationSet);
	}

	// 通过配置文件进行数据验证
	public static void dataValidationByXmlConfig() {
		User user = new User();
		// user.setAccount("sa");
		user.setPasswd("123");
		user.setSex("女0");
		user.setSalary(90000.0);
		user.setBirthday(new Date());
		user.setHobbies(new String[] { "aaa" });
		
		Configuration<?> configuration = Validation.byDefaultProvider().configure();
		
		configuration.addMapping(UserValidatorSample.class.getResourceAsStream("User-Validator.xml"));

		ValidatorFactory validatorFactory = configuration.buildValidatorFactory();
		Validator validator = validatorFactory.getValidator();
		Set<ConstraintViolation<User>> constraintViolationSet = validator.validate(user);
		if (constraintViolationSet.size() > 0) {
			printValidationInfo(constraintViolationSet);

		}
	}

	// 校验函数的输入参数
	public static void validateFuncInputParams() throws NoSuchMethodException, SecurityException {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		// 1.获取校验器
		Validator validator = factory.getValidator();
		// 2.获取校验方法参数的校验器
		ExecutableValidator validatorParam = validator.forExecutables();
		// 3 创建一个要校验参数的实例
		User user = new User(null, null);
		// 4 获取要校验的方法
		Method method = User.class.getMethod("getCommission", Float.class);
		// 传递校验参数的输入的参数
		Object[] paramObjects = new Object[] { 2f };
		// 开始校验
		Set<ConstraintViolation<User>> constraintViolationSet = validatorParam.validateParameters(user, method,
				paramObjects);
		printValidationInfo(constraintViolationSet);
	}

	// 对函数返回值进行校验
	public static void validateFuncRetVal() throws NoSuchMethodException, SecurityException {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		// 1.获取校验器
		Validator validator = factory.getValidator();

		// 2.获取校验方法参数的校验器
		ExecutableValidator validatorParam = validator.forExecutables();
		// 3 创建一个要校验参数的实例
		User user = new User(null, null);
		user.setSalary(30.0);
		// 4 获取要校验的方法
		Method method = User.class.getMethod("getCommission", Float.class);
		// 传递校验参数的输入的参数
		Object[] paramObjects = new Object[] { 0.5f };
		Object returnValue = null;
		try {
			returnValue = method.invoke(user, paramObjects);// 调用方法获取返回值
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		// 5.校验返回值
		Set<ConstraintViolation<User>> constraintViolationSet = validatorParam.validateReturnValue(user, method,
				returnValue);
		printValidationInfo(constraintViolationSet);
	}

	public static void validateConstructorInputParams() throws NoSuchMethodException {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		// 1.获取校验器
		Validator validator = factory.getValidator();
		// 2.获取校验方法参数的校验器
		ExecutableValidator validatorParam = validator.forExecutables();
		// 3.获取构造函数
		Constructor<User> constructor = User.class.getConstructor(String.class, String.class);
		Object[] constructorsParams = new Object[] { null, null };
		Set<ConstraintViolation<User>> constraintViolationSet = validatorParam
				.validateConstructorParameters(constructor, constructorsParams);
		printValidationInfo(constraintViolationSet);
	}
	private static<T> void printValidationInfo(Set<ConstraintViolation<T>> constraintViolationSet) {
		for (ConstraintViolation<T> cv : constraintViolationSet) {
			System.out.println("字段：" + cv.getPropertyPath().toString());
			System.out.println("无效值：" + cv.getInvalidValue());
			System.out.println("错误：" + cv.getMessage());
		}		
	}
}
