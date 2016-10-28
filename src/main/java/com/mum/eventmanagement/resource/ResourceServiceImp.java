package com.mum.eventmanagement.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mum.eventmanagement.model.Resource;

@Service
@Transactional
public class ResourceServiceImp implements ResourceService {
	@Autowired
	ResourceRepository repository;

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Override
	public void save(Resource resource) {
		repository.save(resource);
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Override
	public List<Resource> findAll() {
		return (List<Resource>) repository.findAll();
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Override
	public List<Resource> findByName(String resourceName) {
		return (List<Resource>) repository.findByName(resourceName);
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Override
	public List<Resource> findByType(String resourceType) {
		return (List<Resource>) repository.findByType(resourceType);
	}

}
