package com.sample;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;
import java.util.Date;

import com.sample.propeditor.DateEditor;

public class PropertyEditorSample {
	static {
		PropertyEditorManager.registerEditor(java.util.Date.class, DateEditor.class);
	}

	public static void main(String[] args) throws Exception {
		PropertyEditor pe = PropertyEditorManager.findEditor(Double.class);
		//字符串转成double
		pe.setAsText("1000");
		Double val = (Double)pe.getValue();
		System.out.println(val);
		//double转成字符串
		pe.setValue(900.9);
		String str = pe.getAsText();
		System.out.println(str);
		
		pe = PropertyEditorManager.findEditor(Date.class);
		//字符串转成double
		pe.setAsText("2018-1-1 22:10:10");
		Date date = (Date)pe.getValue();
		System.out.println(date);
		//double转成字符串
		pe.setValue(date);
		str = pe.getAsText();
		System.out.println(str);
		

	}
}
