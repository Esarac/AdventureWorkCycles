package co.edu.icesi.awc.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

public abstract class AbstractJpaDAO<T extends Serializable, K> {

	   private Class<T> clazz;

	   @PersistenceContext
	   EntityManager entityManager;

	   public final void setClazz(Class<T> clazzToSet){
	      this.clazz = clazzToSet;
	   }

	   public T findById(K id){
	      return entityManager.find(clazz, id);
	   }
	   
	   public List<T> findAll(){
	      return entityManager.createQuery("from " + clazz.getName(), clazz).getResultList();
	   }

//	   public void create(T entity){
//	      entityManager.persist( entity );
//	   }
//	   
//	   public T update(T entity){
//	      return entityManager.merge(entity);
//	   }
	   
	   @Transactional
	   public T save(T entity) {
		   return entityManager.merge(entity);
	   }

	   @Transactional
	   public void delete(T entity){
	      entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
	   }
	   
	   @Transactional
	   public void deleteById(K id){
	      T entity = findById(id);
	      delete(entity);
	   }
	}