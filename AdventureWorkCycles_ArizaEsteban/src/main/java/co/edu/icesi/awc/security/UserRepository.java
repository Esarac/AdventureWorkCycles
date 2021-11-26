package co.edu.icesi.awc.security;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserApp, Integer>{
	List<UserApp> findByUsername(String username);
}
