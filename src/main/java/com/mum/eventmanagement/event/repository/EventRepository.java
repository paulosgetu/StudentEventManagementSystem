package com.mum.eventmanagement.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mum.eventmanagement.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

}
