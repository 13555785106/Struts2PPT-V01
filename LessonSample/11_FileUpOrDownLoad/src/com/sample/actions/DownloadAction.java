package com.sample.actions;

import java.io.InputStream;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class DownloadAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private String fileName;
	private String inputPath;

	public void setInputPath(String value) {
		inputPath = value;
	}

	public String getFileName() {
		return fileName;
	}

	public InputStream getTargetFile() throws Exception {
		fileName = new String("哈哈.jpg".getBytes(),"iso8859-1");
		return ServletActionContext.getServletContext().getResourceAsStream(inputPath);
	}
}
