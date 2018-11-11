package com.sample;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import com.sample.propeditor.DateEditor;
import com.sample.propeditor.SqlDateEditor;
import com.sample.propeditor.SqlTimeEditor;
import com.sample.propeditor.SqlTimestampEditor;

public final class BeanUtils {
	static {
		// 除了基本数据类型，其它类型必须有对应的PropertyEditor。
		// 并在转换之前，进行相应的注册。
		PropertyEditorManager.registerEditor(java.util.Date.class, DateEditor.class);
		PropertyEditorManager.registerEditor(java.sql.Date.class, SqlDateEditor.class);
		PropertyEditorManager.registerEditor(java.sql.Time.class, SqlTimeEditor.class);
		PropertyEditorManager.registerEditor(java.sql.Timestamp.class, SqlTimestampEditor.class);
	}

	private BeanUtils() {
	}

	public static Map<String, String> bean2MapStr(Object bean) {
		return bean2MapStr(bean, "");
	}

	public static Map<String, Object> bean2Map(Object bean) {
		return bean2Map(bean, "");
	}

	public static Map<String, String> bean2MapStr(Object bean, String namePrefix) {
		return bean2MapStr(bean, namePrefix,",");
	}
	public static Map<String, String> bean2MapStr(Object bean, String namePrefix,String arraySeparator) {
		Map<String, Object> mapObj = bean2Map(bean);
		Map<String, String> mapStr = new HashMap<String, String>();
		for (Map.Entry<String, Object> entry : mapObj.entrySet()) {
			String key = namePrefix + entry.getKey();
			String val = "";
			PropertyEditor pe = null;
			if (entry.getValue().getClass().isArray()) {
				pe = PropertyEditorManager.findEditor(entry.getValue().getClass().getComponentType());
				if (pe != null) {
					int arrayLength = Array.getLength(entry.getValue());
					for (int i = 0; i < arrayLength; i++) {
						pe.setValue(Array.get(entry.getValue(), i));
						val += pe.getAsText();
						if (i < arrayLength - 1)
							val += arraySeparator;
					}
				}
			} else {
				pe = PropertyEditorManager.findEditor(entry.getValue().getClass());
				if (pe != null) {
					pe.setValue(entry.getValue());
					val = pe.getAsText();
				}
			}
			mapStr.put(key, val);
		}
		return mapStr;
	}

	public static Map<String, Object> bean2Map(Object bean, String namePrefix) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
			for (PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors()) {
				String name = propertyDescriptor.getName();
				if (!name.equals("class")) {
					try {
						map.put(namePrefix + propertyDescriptor.getName(),
								propertyDescriptor.getReadMethod().invoke(bean));
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return map;
	}

	public static <T> T mapStr2Bean(Map<String, String> map, Class<T> clazz) {
		return mapStr2Bean(map, clazz, "");
	}
	public static <T> T mapStr2Bean(Map<String, String> map, Class<T> clazz, String namePrefix) {
		return mapStr2Bean(map, clazz, namePrefix,",");
	}
	public static <T> T mapStr2Bean(Map<String, String> map, Class<T> clazz, String namePrefix,String arraySeparator ) {
		T t = null;
		BeanInfo beanInfo = null;
		try {
			beanInfo = Introspector.getBeanInfo(clazz);
			t = clazz.newInstance();
			for (PropertyDescriptor pd : beanInfo.getPropertyDescriptors()) {
				try {
					String propName = pd.getName();
					if (!propName.equals("class")) {
						String value = map.get(namePrefix + propName);
						if (pd.getPropertyType() == String.class) {
							pd.getWriteMethod().invoke(t, value);
						} else if (pd.getPropertyType().isArray()) {
							Class<?> componentType = pd.getPropertyType().getComponentType();
							String[] strs = value.split(arraySeparator);
							PropertyEditor pe = PropertyEditorManager.findEditor(componentType);
							if (strs != null) {
								Object array = Array.newInstance(componentType, strs.length);
								for (int i = 0; i < strs.length; i++) {
									pe.setAsText(strs[i]);
									Array.set(array, i, pe.getValue());
								}
								pd.getWriteMethod().invoke(t, array);
							}
						} else {
							PropertyEditor pe = PropertyEditorManager.findEditor(pd.getPropertyType());
							if (pe != null) {
								pe.setAsText(value);
								pd.getWriteMethod().invoke(t, pe.getValue());
							}
						}
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}

		return t;
	}

	public static <T> T map2Bean(Map<String, Object> map, Class<T> clazz) {
		return map2Bean(map, clazz, "");
	}

	public static <T> T map2Bean(Map<String, Object> map, Class<T> clazz, String namePrefix) {
		T t = null;
		BeanInfo beanInfo = null;
		try {
			beanInfo = Introspector.getBeanInfo(clazz);
			t = clazz.newInstance();
			for (PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors()) {
				try {
					if (!propertyDescriptor.getName().equals("class"))
						propertyDescriptor.getWriteMethod().invoke(t,
								map.get(namePrefix + propertyDescriptor.getName()));
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return t;
	}
}
