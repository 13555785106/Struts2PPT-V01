package com.easyweb.servlet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import com.easyweb.EasyHttpServlet;
import com.easyweb.HttpReqResp;
@WebServlet("/ParamSegments/*")
public class ParamSegments extends EasyHttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doGet(HttpReqResp hrr) throws ServletException, IOException{
		doPost(hrr);
	}

	@Override
	protected void doPost(HttpReqResp hrr) throws ServletException, IOException{
		String[] paramSegments = hrr.getRequest().getParamSegments();
		if(paramSegments != null) {
			for(String ps : paramSegments) {
				System.out.println("["+ps+"]");
			}
		}
	}

}
