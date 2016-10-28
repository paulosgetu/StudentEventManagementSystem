package com.mum.eventmanagement.user;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mum.eventmanagement.model.User;

@Service
@Transactional
public class UserSignupServiceImp implements UserSignupService {
	@Autowired
	UserSignupRepository userRepo;
	@Autowired
	LoginSignupRepository loginRepo;

	@Override
	public User findOne(int id) {
		return userRepo.findOne(id);
	}

	@Override
	public void save(User user) {
		userRepo.save(user);
	}

	public void register(User user) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(user.getPassword());
		user.getLogin().setPassword(hashedPassword);
		userRepo.save(user);
	}

	@Override
	public boolean emailExists(String email) {
		return loginRepo.findByEmail(email) != null;
	}

}
