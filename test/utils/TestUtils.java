package utils;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.nutz.dao.impl.SimpleDataSource;

public class TestUtils {
	public static DataSource getDataSource(){
		SimpleDataSource ds = new SimpleDataSource();
		try {
			ds.setDriverClassName("org.gjt.mm.mysql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		ds.setUsername("root");
		ds.setPassword("root");
		ds.setJdbcUrl("jdbc:mysql://localhost:3306/nutz");
		return ds;
	}
	
	public static Connection getConn() throws SQLException{
		return getDataSource().getConnection();
	}
}	