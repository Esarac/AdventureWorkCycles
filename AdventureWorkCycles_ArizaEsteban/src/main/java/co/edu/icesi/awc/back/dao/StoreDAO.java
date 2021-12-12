package co.edu.icesi.awc.back.dao;

import java.sql.Timestamp;
import java.time.Instant;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import co.edu.icesi.awc.back.model.sales.Store;

@Repository
public class StoreDAO extends AbstractJpaDAO<Store, Integer> {

	public final static String TABLE = Store.class.getName();
	
	public StoreDAO() {
		setClazz(Store.class);
	}
	
	@Override
	@Transactional
	public Store save(Store entity) {
		entity.setModifieddate(Timestamp.from(Instant.now()));
		return super.save(entity);
	}
	
}
