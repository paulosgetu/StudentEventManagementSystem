package com.mum.eventmanagement.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mum.eventmanagement.model.Login;
import com.mum.eventmanagement.model.User;

@Repository
public interface LoginSignupRepository extends CrudRepository<Login, Integer> {
	Login findByEmail(String email);
	Login save(Login login);
}
