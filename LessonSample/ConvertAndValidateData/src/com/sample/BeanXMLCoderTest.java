package com.sample;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;

public class BeanXMLCoderTest {

	public static void main(String[] args)
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		User user = new User();
		user.setAccount("sa");
		user.setPasswd("123");
		user.setSex("男");
		user.setSalary(9000.0);
		user.setBirthday(new Date());
		user.setHobbies(new String[] {"足球","篮球","排球"});
		//将user信息写到xml文件中
		XMLEncoder xmlEncoder = null;
		try {
			xmlEncoder = new XMLEncoder(new FileOutputStream("user.xml"));
			xmlEncoder.writeObject(user);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally {
			if(xmlEncoder != null)
				xmlEncoder.close();
		}
		//从xml文件读取user信息形成新的实例。
		XMLDecoder xmlDecoder = null;
		try {
			xmlDecoder = new XMLDecoder(new FileInputStream("user.xml"));
			System.out.println(xmlDecoder.readObject());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally {
			if(xmlDecoder != null)
				xmlDecoder.close();
		}		
	}
}
