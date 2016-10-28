package com.mum.eventmanagement.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mum.eventmanagement.model.Login;

@Service("loginService")
public class LoginServiceImp implements UserDetailsService {
	@Autowired
	LoginRepository repository;

	@Override
	public Login loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Login user = repository.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException(email);
		}
		return user;
	}	 
	     
}
