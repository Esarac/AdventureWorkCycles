package co.edu.icesi.awc.dao;

import org.springframework.stereotype.Repository;

import co.edu.icesi.awc.model.person.Businessentityaddress;
import co.edu.icesi.awc.model.person.BusinessentityaddressPK;

@Repository
public class BusinessentityaddressDAO extends AbstractJpaDAO<Businessentityaddress, BusinessentityaddressPK>{
	
	public BusinessentityaddressDAO() {
		setClazz(Businessentityaddress.class);
	}
	
}
