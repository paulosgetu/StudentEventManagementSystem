package com.mum.eventmanagement.resource;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mum.eventmanagement.model.Resource;

@Repository
public interface ResourceRepository extends CrudRepository<Resource, Integer>{
	
	@Query("select r from Resource  r where r.name= :name")
	public List<Resource> findByName(@Param("name") String resourceName);
	
	@Query("select r from Resource  r where r.type= :type")
	public List<Resource> findByType(@Param("type") String resourceType);
	
	List<Resource>findAll();
	
	@SuppressWarnings("unchecked")
	Resource save(Resource resoure);
    }
