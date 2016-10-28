package com.mum.eventmanagement.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mum.eventmanagement.model.Login;

@Repository
public interface LoggedInUserRepository extends JpaRepository<Login, Integer> {
	Login findByEmail(String email);
}
