package com.easyweb.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import com.easyweb.EasyHttpServlet;
import com.easyweb.HttpReqResp;
import com.easyweb.model.User2Datas;

@WebServlet("/sample/DelUser2/*")
public class DelUser2 extends EasyHttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpReqResp hrr) throws ServletException, IOException {
		String id = hrr.getRequest().getParamSegments()[0];
		System.out.println(id);
		if (id != null) {
			User2Datas.getInstance().delUser(id);
		}
		hrr.getResponse().sendRedirect("../ListUser2");
	}

	@Override
	public void doPost(HttpReqResp hrr) throws ServletException, IOException {
		doGet(hrr);
	}
}
