package controller;

import model.SqlContent;

import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;

import vo.SqlResult;

@IocBean
@Fail("json")
public class TestController {
	
	@Inject
	private SqlContent sqlContent;

	
	@At("/test")
	public void test(){
		SqlResult res = sqlContent.exec(4, null, null);
		System.out.println(res);
	}
}
