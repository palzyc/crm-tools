package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Sqls;
import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Many;
import org.nutz.dao.entity.annotation.ManyMany;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.lang.Strings;
import org.nutz.service.IdEntityService;

@Table("sql_content")
public class SqlContent extends IdEntityService<SqlContent> {
	@Id
	public long contendId;
	@Column
	private String name;
	@Column
	@ColDefine(width = 4000)
	private String content;

	@Many(target = SqlColumn.class, field = "contendId")
	private List<SqlColumn> columns;

	@ManyMany(target = SqlGroup.class, relation = "t_content_group", from = "contendId", to = "groupId")
	private List<SqlGroup> groups;

	// public Map<String, Object> params;
	// public Map<String, Object> vars;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<SqlColumn> getColumns() {
		return columns;
	}

	public void setColumns(List<SqlColumn> columns) {
		this.columns = columns;
	}

	public List<SqlGroup> getGroups() {
		return groups;
	}

	public void setGroups(List<SqlGroup> groups) {
		this.groups = groups;
	}

	public void exec(long id, Map<String, Object> params, Map<String, Object> vars) {
		final SqlContent con = dao().fetch(SqlContent.class, id);
		Sql sql = Sqls.create(con.getContent());
		sql.vars().putAll(vars);
		sql.params().putAll(params);
		sql.setCallback(new SqlCallback() {
			public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
				whenFirstExecSql(con, rs);
				return null;
			}
			
			private void whenFirstExecSql(final SqlContent cont, ResultSet rs) throws SQLException {
				// 第一次执行SQL时，生成列信息
				if (cont.getColumns() == null || cont.getColumns().size() == 0) {
					ResultSetMetaData rsMeta = rs.getMetaData();
					int columnCount = rsMeta.getColumnCount();
					List<SqlColumn> cols = new ArrayList<>();
					for (int i = 0; i < columnCount; i++) {
						String colName = Strings.isEmpty(rsMeta.getColumnName(i + 1)) ? rsMeta.getColumnLabel(i + 1) : rsMeta.getColumnName(i + 1);
						SqlColumn col = new SqlColumn(colName);
						cols.add(col);
					}
					cont.setColumns(cols);
					dao().insertLinks(cont, "columns");
				}
			}
		});
		dao().execute(sql);
	}

	public void save(String name, String content) {
		SqlContent c = new SqlContent();
		c.setColumns(columns);
		c.setContent(content);
		dao().insertWith(c, null);
	}
}
