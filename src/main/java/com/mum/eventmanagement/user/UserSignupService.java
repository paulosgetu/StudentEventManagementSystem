package com.mum.eventmanagement.user;

import com.mum.eventmanagement.model.User;

public interface UserSignupService {
	User findOne(int id);
	void save(User user);
	boolean emailExists(String email);
	void register(User user);
	
}
