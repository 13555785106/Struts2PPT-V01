package org.sample;

import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;

public class Test {

	public static void main(String[] args) throws OgnlException {
		testOgnl1();
		testOgnl2();
	}
	public static void testOgnl1() throws OgnlException {
		System.out.println("========================================");
        OgnlContext context = new OgnlContext();
        context.put("cn","China");
        String value = (String)context.get("cn");
        System.out.println(value);
        System.out.println("========================================");
        
        User user01 = new User();
        user01.setId(100);
        user01.setName("Jack");
        context.put("user",user01);
        Object s = context.get("user");
        System.out.println(s);
        Object ognl01 = Ognl.parseExpression("#user.id");
        Object value01 = Ognl.getValue(ognl01, context, context.getRoot());
        
        System.out.println(value01);
        System.out.println("========================================");

        User user02 = new User();
        user02.setId(200);
        user02.setName("Tom");
        context.setRoot(user02);
        System.out.println(context.getRoot());
        Object ognl02 = Ognl.parseExpression("id");
        Object value02 = Ognl.getValue(ognl02, context, context.getRoot());
        System.out.println(value02);
        System.out.println("========================================");
        

    }
	public static void testOgnl2() throws OgnlException{
        OgnlContext context = new OgnlContext();
        Object ognl = Ognl.parseExpression("@java.lang.Math@floor(10.9)");
        Object value = Ognl.getValue(ognl, context, context.getRoot());
        System.out.println(value);
    }
}
