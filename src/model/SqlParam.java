package model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.One;

public class SqlParam extends BaseModel<SqlParam>{

	@Column
	public String name;
	@Column
	public String nameZh;
	@Column
	public String remark;
	@Column
	public long contendId;
}
