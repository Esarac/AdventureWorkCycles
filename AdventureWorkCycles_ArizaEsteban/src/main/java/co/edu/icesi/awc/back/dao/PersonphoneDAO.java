package co.edu.icesi.awc.back.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import co.edu.icesi.awc.back.model.person.Personphone;
import co.edu.icesi.awc.back.model.person.PersonphonePK;

@Repository
public class PersonphoneDAO extends AbstractJpaDAO<Personphone, PersonphonePK>{

	public final static String TABLE = Personphone.class.getName();
	
	public PersonphoneDAO() {
		setClazz(Personphone.class);
	}
	
	public List<Personphone> findByPrefix(String prefix){
		TypedQuery<Personphone> q = entityManager.createQuery(
				"from " + TABLE + 
				" where id.phonenumber like :prefix", Personphone.class);
		q.setParameter("prefix", prefix+"%");
		
		return q.getResultList();
	}
	
	public List<Personphone> findByPhonenumbertype(Integer phonenumbertypeid){
		TypedQuery<Personphone> q = entityManager.createQuery(
				"from " + TABLE + 
				" where id.phonenumbertypeid=:phonenumbertypeid", Personphone.class);
		q.setParameter("phonenumbertypeid", phonenumbertypeid);
		
		return q.getResultList();
	}
	
	public List<Personphone> specialQuery(){//2.B
		TypedQuery<Personphone> q = entityManager.createQuery(""
				+ "SELECT p "
				+ "FROM Personphone p "
				+ "WHERE "
				//Person
				+ "(SELECT person.businessentityid "
				+ "FROM Person person, Personphone phone, Phonenumbertype type "
				+ "WHERE person.businessentityid = phone.id.businessentityid and phone.id.phonenumbertypeid = type.phonenumbertypeid "
				+ "GROUP BY person.businessentityid "
				+ "HAVING COUNT(DISTINCT type.phonenumbertypeid)>1)"
				//...
				+ " = p.id.businessentityid"
				, Personphone.class);
		
		return q.getResultList();
	}
	
}
