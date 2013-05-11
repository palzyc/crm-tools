package model;

import java.lang.reflect.Field;
import java.sql.Date;
import java.sql.Timestamp;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.entity.EntityField;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.service.EntityService;
import org.springframework.util.ReflectionUtils;

public class BaseModel<T> extends EntityService<T> {

	@Id
	public long id;
	
	@Column
	public Timestamp created;
	@Column
	public Timestamp updated;
	
	public BaseModel() {
		super();
	}

	public BaseModel(Dao dao) {
		super(dao);
	}

	public BaseModel(Dao dao, Class<T> entityType) {
		super(dao, entityType);
	}

	// @Override
	// public Dao dao() {
	// return new NutDao(TestUtils.getDataSource()); //TODO
	// }

	public T fetch(long id) {
		return dao().fetch(getEntityClass(), id);
	}

	public int delete(long id) {
		return dao().delete(getEntityClass(), id);
	}

	public int getMaxId() {
		return dao().getMaxId(getEntityClass());
	}

	public boolean exists(long id) {
		EntityField ef = getEntity().getIdField();
		if (null == ef)
			return false;
		return dao().count(getEntityClass(), Cnd.where(ef.getName(), "=", id)) > 0;
	}
	
	public static void setModelTime(Object obj) {
		Field createdProp = ReflectionUtils.findField(obj.getClass(), "created");
		Field updatedProp = ReflectionUtils.findField(obj.getClass(), "updated");
		if(createdProp != null){
			Object createdVal = ReflectionUtils.getField(createdProp, obj);
			if(createdVal == null)
				ReflectionUtils.setField(createdProp, obj, new Timestamp(System.currentTimeMillis()));
			else
				ReflectionUtils.setField(updatedProp, obj, new Timestamp(System.currentTimeMillis()));
		}
	}
}
