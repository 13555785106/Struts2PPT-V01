package org.sample.helloworld.action;

import org.sample.helloworld.model.MessageStore;
import com.opensymphony.xwork2.ActionSupport;

public class HelloWorldAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private String userName;
	private MessageStore messageStore;

	@Override
	public String execute() throws Exception {
		messageStore = new MessageStore();
		messageStore.setUserName(userName);
		return SUCCESS;
	}
	
	public MessageStore getMessageStore() {
		return messageStore;
	}
	public void setMessageStore(MessageStore messageStore) {
		this.messageStore = messageStore;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
