package model;

import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.One;

public class SqlColumn {
	@Id
	public long columnId;
	public String name;
	public String nameZh;
	public String remark;

	@One(target = SqlContent.class, field = "contendId")
	public SqlContent sql;
	public long contendId;

	public SqlColumn() {
		super();
	}

	public SqlColumn(String name) {
		this.name = name;
	}

	public SqlColumn(String name, String nameZh, String remark) {
		this.name = name;
		this.nameZh = nameZh;
		this.remark = remark;
	}

}
