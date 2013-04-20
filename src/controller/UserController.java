package controller;

import javax.servlet.http.HttpSession;

import model.User;

import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import common.Const;


@IocBean
@Fail("json")
public class UserController {                                                                                                                 

	@Inject("refer:user")
	private User user_sv;
	
	@At("/login")
	@Ok("jsp:jsp/sqls/sql_query")
	public void login(@Param("name") String name, @Param("pwd") String password, HttpSession session){
		User u = user_sv.fetch(Cnd.where("loginName", "=", name).and("password", "=", password));
		if (null == u)
			throw new RuntimeException("Error username or password");
		session.setAttribute(Const.SESSIONKEY_LOGIN_USER, u);
	}
}