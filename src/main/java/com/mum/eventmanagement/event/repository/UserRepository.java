package com.mum.eventmanagement.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mum.eventmanagement.model.User;
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

}
