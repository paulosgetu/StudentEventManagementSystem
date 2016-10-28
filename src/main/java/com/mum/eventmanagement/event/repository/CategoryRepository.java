package com.mum.eventmanagement.event.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mum.eventmanagement.model.EventCategory;

@Repository
public interface CategoryRepository extends CrudRepository<EventCategory, Integer> {
	List<EventCategory> findAll();
}
