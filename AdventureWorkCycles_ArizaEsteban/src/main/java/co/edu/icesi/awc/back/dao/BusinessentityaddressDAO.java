package co.edu.icesi.awc.back.dao;

import org.springframework.stereotype.Repository;

import co.edu.icesi.awc.back.model.person.Businessentityaddress;
import co.edu.icesi.awc.back.model.person.BusinessentityaddressPK;

@Repository
public class BusinessentityaddressDAO extends AbstractJpaDAO<Businessentityaddress, BusinessentityaddressPK>{
	
	public BusinessentityaddressDAO() {
		setClazz(Businessentityaddress.class);
	}
	
}
