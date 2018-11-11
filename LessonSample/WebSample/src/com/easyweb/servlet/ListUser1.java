package com.easyweb.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import com.easyweb.EasyHttpServlet;
import com.easyweb.HttpReqResp;
import com.easyweb.model.User1Datas;

@WebServlet("/sample/ListUser1")
public class ListUser1 extends EasyHttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpReqResp hrr) throws ServletException, IOException {
		hrr.setReqResult(User1Datas.getInstance().getDatas());
		hrr.forward("/WEB-INF/jsp/" + this.getClass().getSimpleName() + "-Result.jsp");
	}

	@Override
	public void doPost(HttpReqResp hrr) throws ServletException, IOException {
		doGet(hrr);
	}
}
