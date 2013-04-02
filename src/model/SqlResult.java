package model;

import java.util.ArrayList;
import java.util.List;

public class SqlResult {

	public final List<SqlColumn> columns;
	public List<List> lines = new ArrayList<>();

	public SqlResult(List<SqlColumn> columns) {
		this.columns = columns;
	}

	public void addLine(List line) {
		lines.add(line);
	}

	public String toString() {
		StringBuilder buf = new StringBuilder();
		for (SqlColumn col : columns)
			buf.append("|").append(col.nameZh);
		buf.append("|\n");
		for (List line : lines) {
			for (Object o : line)
				buf.append("|").append(o);
			buf.append("\n");
		}
		return buf.toString();
	}

}
