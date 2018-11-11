package org.sample.helloworld.model;

public class MessageStore {
	private String userName="Struts User";


	public MessageStore() {
	}

	public String getMessage() {
		return "Hello world，"+userName+" ！";
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
 
}
