package com.mum.eventmanagement.login;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mum.eventmanagement.model.Login;

@Repository
public interface LoginRepository extends CrudRepository<Login, Integer> {
	
	Login findByEmail(@Param("email") String email);
}
