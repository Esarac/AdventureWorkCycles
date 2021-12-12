package co.edu.icesi.awc.back.dao;

import org.springframework.stereotype.Repository;
import co.edu.icesi.awc.back.model.sales.Customer;

@Repository
public class CustomerDAO extends AbstractJpaDAO<Customer, Integer> {
	
	public final static String TABLE = Customer.class.getName();
	
	public CustomerDAO() {
		setClazz(Customer.class);
	}
	
}
