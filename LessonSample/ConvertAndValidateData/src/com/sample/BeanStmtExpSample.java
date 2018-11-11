package com.sample;

import java.beans.Expression;
import java.beans.Statement;

public class BeanStmtExpSample {

	public static void main(String[] args) throws Exception {
		User user = new User();
		Statement statement = new Statement(user, "setAccount", new Object[] { "sa"});
		statement.execute();
		
		Expression expression = new Expression(user, "getAccount", null);
		expression.execute();
		System.out.println(expression.getValue());
		System.out.println(user);
	}

}
