package com.easyweb.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import com.easyweb.EasyHttpServlet;
import com.easyweb.HttpReqResp;
import com.easyweb.bean.BeanUtils;
import com.easyweb.model.User2;
import com.easyweb.model.User2Datas;

@WebServlet("/sample/ChgUser2")
public class ChgUser2 extends EasyHttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpReqResp hrr) throws ServletException, IOException {
		User2 user = User2Datas.getInstance().getUser(hrr.getRequest().getParameter("id"));
		if (user != null) {
			hrr.setReqParams(BeanUtils.bean2MapStr(user));
			System.out.println(BeanUtils.mapStr2Bean(BeanUtils.bean2MapStr(user), User2.class));
		}
		hrr.forwardByViewName("Input.jsp");
	}

	@Override
	public void doPost(HttpReqResp hrr) throws ServletException, IOException {
		User2 user = hrr.convertAndValidate(User2.class);
		if (user != null) {
			if (!user.getPasswd().equals(user.getConfirmPasswd())) {
				hrr.addParamError("confirmPasswd", "确认密码与密码不同");
			}
			if (User2Datas.getInstance().accountExist(user.getId(), user.getAccount())) {
				hrr.addParamError("account", "账号已经存在");
			}

			if (hrr.hasErrors()) {
				hrr.forwardByViewName("Input.jsp");
				return;
			} else {
				if (User2Datas.getInstance().chgUser(user)) {
					hrr.getResponse().sendRedirect("ListUser2");
					return;
				}
			}
		}
		hrr.forward("/WEB-INF/jsp/error.jsp");
	}
}
