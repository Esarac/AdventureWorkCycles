package co.edu.icesi.awc.dao;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import co.edu.icesi.awc.model.person.Person;

@Repository
public class PersonDAO extends AbstractJpaDAO<Person, Integer>{
	
	public final static String TABLE = Person.class.getName();
	
	public PersonDAO() {
		setClazz(Person.class);
	}
	
	public List<Person> findByTitle(String title){
		TypedQuery<Person> q = entityManager.createQuery(
				"from " + TABLE + 
				" where title=:title", Person.class);
		q.setParameter("title", title);
		
		return q.getResultList();
	}
	
	public List<Person> findByPersontype(String persontype){
		TypedQuery<Person> q = entityManager.createQuery(
				"from " + TABLE + 
				" where persontype=:persontype", Person.class);
		q.setParameter("persontype", persontype);
		
		return q.getResultList();
	}
	
	public List<Person> specialQuery(Timestamp start, Timestamp end){
		TypedQuery<Person> q = entityManager.createQuery(
				"from " + TABLE +
				" where ", Person.class);
		q.setParameter("start", start);
		q.setParameter("end", end);
		
		return q.getResultList();
	}//Terminar (2.A)
	
}
