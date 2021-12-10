package co.edu.icesi.awc.back.dao;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import co.edu.icesi.awc.back.model.person.Address;

@Repository
public class AddressDAO extends AbstractJpaDAO<Address, Integer>{
	
	public final static String TABLE = Address.class.getName();
	
	public AddressDAO() {
		setClazz(Address.class);
	}
	
	public List<Address> findByModifieddate(Timestamp modifieddate){
		TypedQuery<Address> q = entityManager.createQuery(
				"from " + TABLE + 
				" where modifieddate=:modifieddate", Address.class);
		q.setParameter("modifieddate", modifieddate);
		
		return q.getResultList();
	}
	
}
