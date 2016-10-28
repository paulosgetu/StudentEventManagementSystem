package com.mum.eventmanagement.resource;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mum.eventmanagement.model.Resource;
@Service
public interface ResourceService {
	
	public void save(Resource resource);
	
	public List<Resource> findAll();
	
	public List<Resource> findByName(String resourceName);
	
	public List<Resource> findByType(String resourceType);


}
