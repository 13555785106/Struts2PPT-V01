package com.easyweb.servlet;

import java.io.IOException;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import com.easyweb.EasyHttpServlet;
import com.easyweb.HttpReqResp;
import com.easyweb.model.User2;
import com.easyweb.model.User2Datas;

@WebServlet("/sample/AddUser2")
public class AddUser2 extends EasyHttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpReqResp hrr) throws ServletException, IOException {
		hrr.forwardByViewName("Input.jsp");
	}

	@Override
	public void doPost(HttpReqResp hrr) throws ServletException, IOException {
		User2 user = hrr.convertAndValidate(User2.class);
		if (user != null) {
			if (!user.getPasswd().equals(user.getConfirmPasswd())) {
				hrr.addParamError("confirmPasswd", "确认密码与密码不同");
			}
			if (User2Datas.getInstance().accountExist(user.getAccount())) {
				hrr.addParamError("account", "账号已经存在");
			}
			if (hrr.hasErrors()) {
				hrr.forwardByViewName("Input.jsp");
				return;
			} else {
				user.setId(UUID.randomUUID().toString());
				if (User2Datas.getInstance().addUser(user)) {
					hrr.setReqResult(user);
					hrr.forwardByViewName("Success.jsp");
				} else {
					hrr.addExeError("添加用户失败");
					hrr.forwardByViewName("Input.jsp");
				}
				return;
			}
		}
		hrr.forward("/WEB-INF/jsp/error.jsp");
	}
}
