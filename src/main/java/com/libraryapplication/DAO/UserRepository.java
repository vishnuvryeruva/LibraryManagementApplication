package com.libraryapplication.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.libraryapplication.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	
	
	User findByUserName(String userName);
		
}
