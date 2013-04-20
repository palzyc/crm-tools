package common;

import java.util.Arrays;

import model.User;

import org.nutz.dao.Dao;
import org.nutz.ioc.Ioc;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;

public class MainSetup implements Setup {

	public void init(NutConfig config) {
		Ioc ioc = config.getIoc();
		System.out.println(Arrays.toString(ioc.getNames()));
		Dao dao = ioc.get(Dao.class, "dao");
		boolean exists = dao.exists("user");
		if(!exists)
			dao.create(User.class, false);
		
		User u = dao.fetch(User.class, "zhangyc3");
		if (u == null) {
			u = new User();
			u.name = ("张宇骢");
			u.loginName = ("zhangyc3");
			u.password = ("1");
			dao.insert(u);
		}
		System.out.println("setup init...");
	}

	public void destroy(NutConfig config) {
		System.out.println("setup destory...");
	}

}
