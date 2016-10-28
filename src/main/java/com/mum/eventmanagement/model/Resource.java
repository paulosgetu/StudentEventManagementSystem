package com.mum.eventmanagement.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Resource {
	@Id
	@GeneratedValue
	private Integer id;
	@NotEmpty(message="{name.requered}")
	private String name;
	@NotEmpty(message="{type.requered}")
	private String type;
	private String description;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "EVENT_RESOURCES",
		joinColumns = @JoinColumn(name = "RESOURCE_ID"),
		inverseJoinColumns = @JoinColumn(name = "EVENT_ID"))
	private List<Event> assingedFor;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Event> getAssingedFor() {
		return assingedFor;
	}

	public void setAssingedFor(List<Event> assingedFor) {
		this.assingedFor = assingedFor;
	}

	@Override
	public String toString() {
		return "Resource [id=" + id + ", name=" + name + ", type=" + type + ", description=" + description + "]";
	}

}
