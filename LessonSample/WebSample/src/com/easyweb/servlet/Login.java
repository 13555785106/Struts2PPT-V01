package com.easyweb.servlet;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import com.easyweb.EasyHttpServlet;
import com.easyweb.HttpReqResp;
import com.easyweb.model.User2;
import com.easyweb.model.User2Datas;

@WebServlet("/Login")
public class Login extends EasyHttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpReqResp hrr) throws ServletException, IOException {
		hrr.forwardByViewName("Input.jsp");
	}

	@Override
	public void doPost(HttpReqResp hrr) throws ServletException, IOException {
		Map<String, String> msgs = getMessagesMap();
		Map<String, String> params = hrr.getReqParams();
		for (Map.Entry<String, String> entry : params.entrySet()) {
			if (entry.getValue().isEmpty()) {
				hrr.addParamError(entry.getKey(), msgs.get("error." + entry.getKey()));
			}
		}
		if (!hrr.hasErrors()) {
			User2 user = User2Datas.getInstance().validateLogin(params.get("account"), params.get("passwd"));
			if (user != null) {
				hrr.getRequest().getSession().setAttribute("user_id", user.getId());
				hrr.getResponse().sendRedirect("sample/ListUser2");
				return;
			} else {
				hrr.addExeError("登录失败");
			}
		}
		if (hrr.hasErrors()) {
			hrr.forwardByViewName("Input.jsp");
			return;
		}
		hrr.forward("/WEB-INF/jsp/error.jsp");
	}
}
