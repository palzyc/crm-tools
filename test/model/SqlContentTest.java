package model;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;

import utils.TestUtils;
import vo.SqlResult;

public class SqlContentTest {

	static Dao dao;
	@BeforeClass
	public static void beforeClass(){
		dao = new NutDao(TestUtils.getDataSource());
	}
	
	SqlContent cont;
	@Before
	public void before(){
		cont = new SqlContent();
	}
	
	@Test
	public void test() {
		SqlContent bean = new SqlContent();
		bean.content =("select t.userinfoid 主键, t.truename 姓名, addDate 添加时间, addIp from userinfo t");
		bean.name =("测试");
		SqlContent res = dao.insertWith(bean, null);
		bean.exec(res.contendId, null, null);
	}

	public static void main(String[] args) {
		dao = new NutDao(TestUtils.getDataSource());
		SqlContent bean = new SqlContent();
//		bean.content =("select t.userinfoid 主键, t.truename 姓名, addDate 添加时间, addIp from userinfo t");
//		bean.name =("测试");
//		SqlContent res = dao.insertWith(bean, null);
		SqlResult res = bean.exec(4, null, null);
		System.out.println(res);
	}
}
