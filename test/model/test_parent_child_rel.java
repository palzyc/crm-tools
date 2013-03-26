package model;

import java.util.ArrayList;
import java.util.List;

import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Many;
import org.nutz.dao.entity.annotation.One;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.dao.impl.NutDao;

import utils.TestUtils;

// 测试自关联
@Table("test_parent_child_rel")
public class test_parent_child_rel {
	@Id
	public long columnId;
	public String name;
	public String nameZh;
	public String remark;
	
	@One(target=test_parent_child_rel.class, field="parentId")
	public test_parent_child_rel parent;
	public long parentId;
	
	@Many(target=test_parent_child_rel.class, field="parentId")
	public List<test_parent_child_rel> childs;
	

	public test_parent_child_rel() {
		super();
	}

	public test_parent_child_rel(String name, String nameZh, String remark) {
		this.name = name;
		this.nameZh = nameZh;
		this.remark = remark;
	}
	
	public static void main(String[] args) {
		NutDao dao = new NutDao(TestUtils.getDataSource());
		dao.create(test_parent_child_rel.class, true);

		test_parent_child_rel par = new test_parent_child_rel("name", "nameZh", "remark");
		List<test_parent_child_rel> childs = new ArrayList<>();
		test_parent_child_rel c1 = new test_parent_child_rel("1", "1", "1");
		test_parent_child_rel c2 = new test_parent_child_rel("2", "2", "2");
		childs.add(c1);
		childs.add(c2);
		par.childs = childs;
		dao.insertWith(par, "childs");
	}

}
