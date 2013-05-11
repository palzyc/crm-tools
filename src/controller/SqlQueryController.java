package controller;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;

@IocBean
@Fail("json")
public class SqlQueryController {
	
	
	
	@At("/sqls/query")
	public String queryAllSql() {
		return null;
	}
}
