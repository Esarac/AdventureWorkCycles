package co.edu.icesi.awc.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

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

	   public Optional<T> findById(K id){
		  T entity = entityManager.find(clazz, id);
	      return entity != null ? Optional.of(entity) : Optional.empty();
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
		  Optional<T> entityOptional = findById(id);
		  if(entityOptional.isPresent()) {
			  delete(entityOptional.get());
		  }
	   }
	}