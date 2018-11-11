package com.sample.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class UploadAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	// 封装文件标题请求参数的属性
	private String title;
	// 封装上传文件域的属性
	private File upload;
	// 封装上传文件类型的属性
	private String uploadContentType;
	// 封装上传文件名的属性
	private String uploadFileName;
	// 直接在struts.xml文件中配置的属性
	private String savePath;
	private String allowTypes;

	public String getAllowTypes() {
		return allowTypes;
	}

	public void setAllowTypes(String allowTypes) {
		this.allowTypes = allowTypes;
	}

	// 接受struts.xml文件配置值的方法
	public void setSavePath(String value) {
		this.savePath = value;
	}

	// 获取上传文件的保存位置
	private String getSavePath() throws Exception {
		return ServletActionContext.getServletContext().getRealPath(savePath);
	}

	// title的setter和getter方法
	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return (this.title);
	}

	// upload的setter和getter方法
	public void setUpload(File upload) {
		this.upload = upload;
	}

	public File getUpload() {
		return (this.upload);
	}

	// uploadContentType的setter和getter方法
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadContentType() {
		return (this.uploadContentType);
	}

	// uploadFileName的setter和getter方法
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getUploadFileName() {
		return (this.uploadFileName);
	}

	@Override
	public String execute() throws Exception {
		// 以服务器的文件保存地址和原文件名建立上传文件输出流
		FileOutputStream fos = new FileOutputStream(getSavePath() + File.separator + getUploadFileName());
		System.out.println(getSavePath() + File.separator + getUploadFileName());
		System.out.println(getUploadContentType());
		FileInputStream fis = new FileInputStream(getUpload());
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = fis.read(buffer)) > 0) {
			fos.write(buffer, 0, len);
		}
		fis.close();
		fos.close();
		return SUCCESS;
	}
/*
	public boolean filterTypes(String[] types) {
		// 获取允许上传的所有文件类型
		String fileType = getUploadContentType();
		for (String type : types) {
			if (type.equals(fileType)) {
				return true;
			}
		}
		return false;
	}

	// 执行输入校验
	public void validate() {
		// 将允许上传文件类型的字符串以英文逗号（,）
		// 分解成字符串数组从而判断当前文件类型是否允许上传
		if (!filterTypes(getAllowTypes().split(","))) {
			// 添加FieldError
			addFieldError("upload", "您要上传的文件类型不正确！");
		}
	}
*/
}
