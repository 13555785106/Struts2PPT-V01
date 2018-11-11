package com.sample;

import org.apache.struts2.util.SubsetIteratorFilter.Decider;

public class DotDecider implements Decider {

	@Override
	public boolean decide(Object element) throws Exception {
		if (element instanceof String) {
			String str = (String) element;
			if (str.indexOf(".") > 0)
				return true;
		}
		return false;
	}

}
