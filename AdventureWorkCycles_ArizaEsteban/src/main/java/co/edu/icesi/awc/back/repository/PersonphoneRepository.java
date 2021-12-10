package co.edu.icesi.awc.back.repository;

import org.springframework.data.repository.CrudRepository;

import co.edu.icesi.awc.back.model.person.Personphone;
import co.edu.icesi.awc.back.model.person.PersonphonePK;

public interface PersonphoneRepository extends CrudRepository<Personphone, PersonphonePK>{

}
