package model;

import org.nutz.dao.impl.sql.SqlLiteral;

public class test_nutsql {
	public static void main(String[] args) {
		SqlLiteral ss = new SqlLiteral().valueOf("select t.userinfoid 主键, t.truename 姓名, addDate 添加时间, addIp from user$info t where t.truename = @truename");
		System.out.println(ss.getVarIndexes().getOrders());
		System.out.println(ss.getParamIndexes().getOrders());
	}
}
