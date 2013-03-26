package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Many;
import org.nutz.dao.entity.annotation.ManyMany;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.dao.impl.NutDao;

import utils.TestUtils;

@Table("sql_content")
public class SqlContent {
	@Id
	public long contendId;
	@Column
	public String name;
	@Column
	@ColDefine(width = 4000)
	public String content;

	@Many(target = test_parent_child_rel.class, field = "contendId")
	public List<test_parent_child_rel> columns;

	@ManyMany(target = SqlGroup.class, relation = "t_content_group", from = "contendId", to = "groupId")
	public List<SqlGroup> groups;

	public Map<String, Object> params;
	public Map<String, Object> vars;
	
	public static void main(String[] args) {
		NutDao dao = new NutDao(TestUtils.getDataSource());
		dao.create(test_parent_child_rel.class, true);
		dao.create(SqlContent.class, true);
		dao.create(SqlGroup.class, true);
//		 testContent(dao);

		testGroup(dao);
	}

	private static void testContent(NutDao dao) {
		List<test_parent_child_rel> cols = new ArrayList<>();
		 cols.add(new test_parent_child_rel("AA", "测试1", ""));
		 cols.add(new test_parent_child_rel("BB", "测试2", ""));
		 cols.add(new test_parent_child_rel("CC", "测试3", ""));
		 cols.add(new test_parent_child_rel("DD", "测试4", ""));
		 SqlContent bean = new SqlContent();
		 bean.columns = cols;
		 bean.content = ("select * from person");
		 dao.insertWith(bean, "columns");
		
		 SqlContent b = dao.fetchLinks(dao.fetch(SqlContent.class, 1), "columns");
		 System.out.println(b);
		
		 test_parent_child_rel col = dao.fetch(test_parent_child_rel.class, 1);
		 System.out.println(col);
		 test_parent_child_rel col2 = dao.fetchLinks(col, "sql");
		 System.out.println(col2);
	}

	private static void testGroup(NutDao dao) {
		List<test_parent_child_rel> cols = new ArrayList<>();
		cols.add(new test_parent_child_rel("AA", "测试1", ""));
		cols.add(new test_parent_child_rel("BB", "测试2", ""));
		List<SqlGroup> groups = new ArrayList<>();
		groups.add(new SqlGroup("张宇骢"));
		groups.add(new SqlGroup("张宇骢2"));
		
		SqlContent bean = new SqlContent();
		bean.columns = cols;
		bean.name = "测试查询";
		bean.content = ("select * from person");
		bean.groups = groups;
		
		dao.insertWith(bean, null);
	}
}
