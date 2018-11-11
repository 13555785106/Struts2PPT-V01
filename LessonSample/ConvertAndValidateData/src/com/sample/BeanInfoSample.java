package com.sample;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

public class BeanInfoSample {

	public static void main(String[] args) {
		//查看bean的属性信息
		BeanInfo userBeanInfo = null;
		try {
			userBeanInfo = Introspector.getBeanInfo(User.class);
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		if (userBeanInfo != null) {
			for (PropertyDescriptor pd : userBeanInfo.getPropertyDescriptors()) {
				String propName = pd.getName();
				Class<?> propType = pd.getPropertyType();
				System.out.println(propName + "=" + propType);
			}
		}

	}

}
