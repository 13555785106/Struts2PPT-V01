package com.easyweb;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * 对HttpServletRequest接口进行封装，提供一些额外的功能。
 *
 * @author 肖俊峰
 * @since 1.0
 * @version 1.0
 */
public class EasyHttpServletRequest extends HttpServletRequestWrapper {
	public final static String REQ_PARAMS = "reqParams";// 请求参数的属性名称
	public final static String PARAM_ERRORS = "paramErrors";// 字段转换或校验错误信息的属性名称
	public final static String EXE_ERRORS = "exeErrors";// 程序执行时遇到的非字段校验错误信息的属性名称
	public final static String REQ_RESULT = "reqResult";// forward请求结果的属性名称

	public final static String FIELD_NOT_NEED_TRIM = "fieldNotNeedTrim";// 不需要trim的表单字段名称
	public final static String ARRAY_FIELDS = "arrayFields";// 数组类型的字段使用"[字段名][字段名]..."格式
	public final static String ARRAY_SEPARATOR = "arraySeparator";// 在字符串数组参数合并成字符串时使用的分隔符号
	public final static String[] IGNORED_FIELDS = { FIELD_NOT_NEED_TRIM, ARRAY_FIELDS, ARRAY_SEPARATOR };

	// 构造函数
	public EasyHttpServletRequest(HttpServletRequest request, String charset) {
		super(request);
		try {
			setCharacterEncoding(charset);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		setAttribute(REQ_PARAMS, convertParamsToString());
		setAttribute(PARAM_ERRORS, new HashMap<String, List<String>>());
		setAttribute(EXE_ERRORS, new ArrayList<String>());
	}

	// 将请求参数同一放到一个Map里，便于后面向Bean转化，此处字符串数组被转换成了使用分隔符(默认是逗号)隔开的字符串
	private Map<String, String> convertParamsToString() {
		String fieldNotNeedTrim = getParameter(FIELD_NOT_NEED_TRIM);
		if (fieldNotNeedTrim == null)
			fieldNotNeedTrim = "";
		String arrayFields = getParameter(ARRAY_FIELDS);
		if (arrayFields == null)
			arrayFields = "";
		String arraySeparator = getParameter(ARRAY_SEPARATOR);
		if (arraySeparator == null)
			arraySeparator = ",";

		Map<String, String> parameters = new HashMap<String, String>();
		for (Map.Entry<String, String[]> entry : getParameterMap().entrySet()) {
			if (!Arrays.asList(IGNORED_FIELDS).contains(entry.getKey())) {
				String[] values = entry.getValue();
				if (arrayFields.contains("[" + entry.getKey() + "]")) {
					StringBuilder sb = new StringBuilder();
					for (int i = 0; i < values.length; i++) {
						sb.append(values[i]);
						if (i != values.length - 1)
							sb.append(arraySeparator);
					}
					parameters.put(entry.getKey(), sb.toString());
				} else {
					if (fieldNotNeedTrim.contains("[" + entry.getKey() + "]"))
						parameters.put(entry.getKey(), values[0]);
					else
						parameters.put(entry.getKey(), values[0].trim());
				}
			}
		}
		return parameters;
	}

	public String[] getParamSegments() {
		String basePath = getContextPath() + getServletPath();
		if (getRequestURI().length() > basePath.length()) {
			return getRequestURI().substring(basePath.length() + 1).split("/");
		}
		return null;
	}
}
