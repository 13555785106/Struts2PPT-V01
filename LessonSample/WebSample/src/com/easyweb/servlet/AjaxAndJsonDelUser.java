package com.easyweb.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import org.json.JSONObject;

import com.easyweb.EasyHttpServlet;
import com.easyweb.HttpReqResp;
import com.easyweb.model.User2Datas;

@WebServlet("/sample/AjaxAndJsonDelUser")
public class AjaxAndJsonDelUser extends EasyHttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpReqResp hrr) throws ServletException, IOException {
		hrr.getResponse().setContentType("application/json; charset=UTF-8");
		boolean success = false;
		String id = hrr.getRequest().getParameter("id");
		if (id != null && User2Datas.getInstance().delUser(id)) {
			success = true;
		}
		JSONObject result = new JSONObject();
		if (success) {
			result.put("status", "success");
		} else {
			result.put("status", "failed");
		}
		System.out.println(result.toString(4));
		hrr.getResponse().getWriter().append(result.toString(4));
	}

	@Override
	public void doPost(HttpReqResp hrr) throws ServletException, IOException {
		doGet(hrr);
	}
}
