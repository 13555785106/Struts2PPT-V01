package com.easyweb.servlet;

import java.io.IOException;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import org.json.JSONObject;

import com.easyweb.EasyHttpServlet;
import com.easyweb.HttpReqResp;
import com.easyweb.model.User2;
import com.easyweb.model.User2Datas;

@WebServlet("/sample/AjaxAndJsonAddOrChgUser")
public class AjaxAndJsonAddOrChgUser extends EasyHttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpReqResp hrr) throws ServletException, IOException {
		doPost(hrr);
	}

	@Override
	public void doPost(HttpReqResp hrr) throws ServletException, IOException {
		hrr.getResponse().setContentType("application/json; charset=UTF-8");
		User2 user = hrr.convertAndValidate(User2.class);
		JSONObject result = new JSONObject();
		if (user != null) {
			if (!user.getPasswd().equals(user.getConfirmPasswd())) {
				hrr.addParamError("confirmPasswd", "确认密码与密码不同");
			}
			if ((user.getId().isEmpty() && User2Datas.getInstance().accountExist(user.getAccount())) || (!user.getId().isEmpty() && User2Datas.getInstance().accountExist(user.getId(), user.getAccount())))
				hrr.addParamError("account", "账号已经存在");

			if (!hrr.hasErrors()) {
				if (user.getId().isEmpty()) {
					user.setId(UUID.randomUUID().toString());
					if (!User2Datas.getInstance().addUser(user))
						hrr.addExeError("添加用户失败");
				} else {
					if (!User2Datas.getInstance().chgUser(user))
						hrr.addExeError("修改用户失败");
				}
			}
		}
		if (!hrr.hasErrors()) {
			result.put("status", "success");
		} else {
			result.put("status", "failed");
			result.put("paramErrors", hrr.getParamErrors());
			result.put("exeErrors", hrr.getExeErrors());
		}
		System.out.println(result.toString(4));
		hrr.getResponse().getWriter().append(result.toString(4));
	}
}
