package com.mum.eventmanagement.event.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mum.eventmanagement.event.repository.LoggedInUserRepository;
import com.mum.eventmanagement.model.User;

@Service
public class LoggedinUserServiceImp implements LoggedinUserService {
	@Autowired
	LoggedInUserRepository loggedInUserRepo;

	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return loggedInUserRepo.findByEmail(email).getUser();
	}

}
