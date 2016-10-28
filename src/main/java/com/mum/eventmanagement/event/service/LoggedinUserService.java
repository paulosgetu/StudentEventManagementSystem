package com.mum.eventmanagement.event.service;

import com.mum.eventmanagement.model.User;

public interface LoggedinUserService {
	public User findByEmail(String email);
}
