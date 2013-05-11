package model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.One;

public class SqlVar extends BaseModel<SqlVar> {

	@Column
	public String name;
	@Column
	public String nameZh;
	@Column
	public String remark;
	@Column
	public long contendId;
}
