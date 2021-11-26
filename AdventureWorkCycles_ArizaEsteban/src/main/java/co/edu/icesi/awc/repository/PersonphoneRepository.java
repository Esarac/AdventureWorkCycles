package co.edu.icesi.awc.repository;

import org.springframework.data.repository.CrudRepository;

import co.edu.icesi.awc.model.person.Personphone;
import co.edu.icesi.awc.model.person.PersonphonePK;

public interface PersonphoneRepository extends CrudRepository<Personphone, PersonphonePK>{

}
