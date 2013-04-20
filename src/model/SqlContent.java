package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Many;
import org.nutz.dao.entity.annotation.ManyMany;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.service.IdEntityService;

import vo.SqlResult;

import common.Const;

@Table("sql_content")
@IocBean(fields = {"dao"})
public class SqlContent extends IdEntityService<SqlContent>{
	
	@Id
	public long contendId;
	@Column
	public String name;
	@Column
	@ColDefine(width = 4000)
	public String content;

	@Many(target = SqlColumn.class, field = "contendId")
	public List<SqlColumn> columns;

	@ManyMany(target = SqlGroup.class, relation = "t_content_group", from = "contendId", to = "groupId")
	public List<SqlGroup> groups;
	
//	@ManyMany(target = User.class, relation = "t_sqls_user", from = "contendId", to = "userId")
//	public List<User> users;


	public SqlContent() {
		super();
	}

	public SqlContent(Dao dao) {
		super(dao);
	}

	public SqlResult exec(long id, Map<String, Object> params, Map<String, Object> vars) {
		final SqlContent cont = dao().fetchLinks(dao().fetch(SqlContent.class, id), "columns");
		Sql sql = Sqls.create(cont.content);
		sql.vars().putAll(vars);
		sql.params().putAll(params);
		sql.setCallback(new SqlCallback() {
			public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
				whenFirstExecSql(cont, rs);  // 第一次执行SQL时，生成列信息
				int i = 0;
				SqlResult result = new SqlResult(cont.columns); 
				while (rs.next()) {
					if (i > Const.PAGE_SIZE)
						break; // TODO 暂时没有考虑分页问题
					
					List line = new ArrayList<>();
					for (int j = 0; j < cont.columns.size(); j++)
						line.add(rs.getObject(j + 1));
					result.addLine(line);
					i++;
				}
				return result;
			}

			private void whenFirstExecSql(final SqlContent cont, ResultSet rs) throws SQLException {
				if (cont.columns == null || cont.columns.size() == 0) {
					ResultSetMetaData rsMeta = rs.getMetaData();
					int columnCount = rsMeta.getColumnCount();
					List<SqlColumn> cols = new ArrayList<>();
					for (int i = 0; i < columnCount; i++) {
						SqlColumn col = new SqlColumn(rsMeta.getColumnName(i + 1), rsMeta.getColumnLabel(i + 1));
						cols.add(col);
					}
					cont.columns = cols;
					dao().insertLinks(cont, "columns");
				}
			}
		});
		dao().execute(sql);
		return sql.getObject(SqlResult.class);
	}

	public void save(String name, String content) {
		SqlContent c = new SqlContent();
		c.columns = columns;
		c.content = content;
		dao().insertWith(c, null);
	}
}
