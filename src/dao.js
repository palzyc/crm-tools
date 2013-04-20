var ioc = {

	dataSource : {
		type : "org.apache.commons.dbcp.BasicDataSource",
		events : {
			depose : 'close'
		},
		fields : {
			driverClassName : 'org.gjt.mm.mysql.Driver',
			url : 'jdbc:mysql://localhost:3306/nutz',
			username : 'root',
			password : 'root'
		}
	},

	dao : {
		type : "org.nutz.dao.impl.NutDao",
		args : [ {
			refer : "dataSource"
		} ]
	},

	/* 声明式事务处理
	txNONE : {
		type : 'org.nutz.aop.interceptor.TransactionInterceptor',
		args : [ 0 ]
	},

	txREAD_COMMITTED : {
		type : 'org.nutz.aop.interceptor.TransactionInterceptor',
		args : [ 2 ]
	},

	$aop : {
		type : 'org.nutz.ioc.aop.config.impl.JsonAopConfigration',
		fields : {
			itemList : [
					[ 'model..+', '(get|set|toString).+', 'ioc:txNONE' ],
					[ 'model..+', '(get|save|update|delete).+', 'ioc:txREAD_COMMITTED' ] 
					[ 'controller ..+', '.+', 'ioc:txREAD_COMMITTED' ] 
					]
		}
	}
	*/
};