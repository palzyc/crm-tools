package model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.One;

public class SqlColumn extends BaseModel<SqlColumn> {

	@Column
	public String name;
	@Column
	public String nameZh;
	@Column
	public String remark;
	@Column
	public long contendId;

	public SqlColumn() {
		super();
	}

	public SqlColumn(String name, String nameZh) {
		this.name = name;
		this.nameZh = nameZh;
	}

	public String toString() {
		return "SqlColumn [columnId=" + id + ", name=" + name + ", nameZh=" + nameZh + ", remark=" + remark + ", contendId=" + contendId + "]";
	}
}
