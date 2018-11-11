package com.telecom.geometry;

public class Array<T> {
	private Object[] objs = new Object[4];
	private int size = 0;

	public void add(T obj) {
		if (size == objs.length) {
			Object[] temp = new Object[objs.length * 2];
			for (int i = 0; i < objs.length; i++)
				temp[i] = objs[i];
			objs = temp;
		}
		objs[size] = obj;
		size++;
	}

	public int size() {
		return size;
	}

	public T get(int index) {
		if (index >= 0 && index < size)
			return (T)objs[index];
		return null;
	}
}
