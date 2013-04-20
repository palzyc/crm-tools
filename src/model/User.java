package model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.service.IdEntityService;

@Table("user")
@IocBean(fields = { "dao" })
public class User extends IdEntityService<User> {
	@Id
	@Column
	public long userId;
	@Column
	public String name;
	@Column
	public String password;
	@Column
	@Name
	public String loginName;
}
