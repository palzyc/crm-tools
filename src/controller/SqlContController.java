package controller;

import model.SqlContent;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

@IocBean
@Fail("json")
public class SqlContController {
	
	@Inject("refer:sqlContent")
	private SqlContent cont_sv;
	
	@At("/sqls/query")
	public String queryAllSql() {
		return null;
	}
	
	@At("/sqlcont/index")
	@Ok("jsp:jsp/sql_cont/index") 
	public void addSqlCont(){
	}
	
	@At("/sqlcont/save")
	@Ok("json")
	public String saveSqlCont(@Param("sqlName")String name, @Param("sqlCont")String content){
		try {
			cont_sv.save(name, content);
		} catch (Exception e) {
			e.printStackTrace();
			return "添加失败";
		}
		return "添加成功";
	}
}
