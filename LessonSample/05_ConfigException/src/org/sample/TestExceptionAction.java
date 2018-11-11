package org.sample;

import java.io.IOException;
import java.sql.SQLException;

import com.opensymphony.xwork2.ActionSupport;

public class TestExceptionAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String execute() throws Exception {
		if (type != null) {
			if (type.equalsIgnoreCase("sql")) {
				throw new SQLException();
			} else if (type.equalsIgnoreCase("io")) {
				throw new IOException();
			} else if (type.equalsIgnoreCase("runtime")) {
				throw new RuntimeException();
			} 
		}
		return SUCCESS;
	}

}
