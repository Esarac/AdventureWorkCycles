package co.edu.icesi.awc.dao;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import co.edu.icesi.awc.model.person.Personphone;
import co.edu.icesi.awc.model.person.PersonphonePK;

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
	
	public List<Personphone> specialQuery(){
		TypedQuery<Personphone> q = entityManager.createQuery(
				"from Person p, Personphone h, Phonetype t" + 
				"inner join " +
				" where ", Personphone.class);
		
		return q.getResultList();
	}//Terminar (2.B)
	
}
