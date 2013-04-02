package model;

import java.util.ArrayList;
import java.util.List;

import org.nutz.dao.impl.NutDao;

import utils.TestUtils;

public class test_insert {

	public static void main(String[] args) {
		NutDao dao = new NutDao(TestUtils.getDataSource());
		dao.create(SqlColumn.class, true);
		dao.create(SqlContent.class, true);
		dao.create(SqlGroup.class, true);
		// testContent(dao);

		testGroup(dao);
	}

	private static void testContent(NutDao dao) {
		List<SqlColumn> cols = new ArrayList<>();
		cols.add(new SqlColumn("AA", "测试1"));
		cols.add(new SqlColumn("BB", "测试2"));
		cols.add(new SqlColumn("CC", "测试3"));
		cols.add(new SqlColumn("DD", "测试4"));
		SqlContent bean = new SqlContent();
		bean.columns =(cols);
		bean.content =("select * from person");
		dao.insertWith(bean, "columns");

		SqlContent b = dao.fetchLinks(dao.fetch(SqlContent.class, 1), "columns");
		System.out.println(b);

		test_parent_child_rel col = dao.fetch(test_parent_child_rel.class, 1);
		System.out.println(col);
		test_parent_child_rel col2 = dao.fetchLinks(col, "sql");
		System.out.println(col2);
	}

	private static void testGroup(NutDao dao) {
		List<SqlColumn> cols = new ArrayList<>();
		cols.add(new SqlColumn("AA", "测试1"));
		cols.add(new SqlColumn("BB", "测试2"));
		List<SqlGroup> groups = new ArrayList<>();
		groups.add(new SqlGroup("张宇骢"));
		groups.add(new SqlGroup("张宇骢2"));

		SqlContent bean = new SqlContent();
		bean.columns =(cols);
		bean.name =("测试查询");
		bean.content =("select * from person");
		bean.groups =(groups);

		dao.insertWith(bean, null);
	}
}
