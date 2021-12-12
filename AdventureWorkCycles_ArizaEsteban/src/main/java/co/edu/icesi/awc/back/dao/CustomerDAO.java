package co.edu.icesi.awc.back.dao;

import java.sql.Timestamp;
import java.time.Instant;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import co.edu.icesi.awc.back.model.sales.Customer;

@Repository
public class CustomerDAO extends AbstractJpaDAO<Customer, Integer> {
	
	public final static String TABLE = Customer.class.getName();
	
	public CustomerDAO() {
		setClazz(Customer.class);
	}
	
	@Override
	@Transactional
	public Customer save(Customer entity) {
		entity.setModifieddate(Timestamp.from(Instant.now()));
		return super.save(entity);
	}
	
}
