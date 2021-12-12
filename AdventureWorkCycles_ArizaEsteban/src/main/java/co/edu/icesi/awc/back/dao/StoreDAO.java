package co.edu.icesi.awc.back.dao;

import org.springframework.stereotype.Repository;

import co.edu.icesi.awc.back.model.sales.Store;

@Repository
public class StoreDAO extends AbstractJpaDAO<Store, Integer> {

	public final static String TABLE = Store.class.getName();
	
	public StoreDAO() {
		setClazz(Store.class);
	}
	
}
