package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;

import utils.TestUtils;

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
		bean.setContent("select * from userinfo");
		bean.setName("测试");
		dao.insertWith(bean, null);
		
		bean.exec(2, null, null);
	}

}
