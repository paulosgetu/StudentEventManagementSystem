package com.mum.eventmanagement.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mum.eventmanagement.model.User;

@Repository
public interface UserSignupRepository extends CrudRepository<User, Integer> {

}
