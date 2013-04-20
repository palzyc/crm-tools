package model;

import java.util.List;

import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.ManyMany;

public class SqlGroup {
	@Id
	public long groupId;
	public String name;
	
	@ManyMany(target = SqlContent.class, relation = "t_content_group", from = "groupId", to = "contendId")
	public List<SqlContent> contents;

//	@ManyMany(target = User.class, relation = "t_group_user", from = "groupId", to = "userId")
//	public List<User> users;

	public SqlGroup() {
	}

	public SqlGroup(String name) {
		this.name = name;
	}
}
