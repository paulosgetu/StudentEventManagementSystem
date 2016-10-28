package com.mum.eventmanagement.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Comment {

	@Id
	@GeneratedValue
	private Integer id;
	private String content;
	private Date dateCreated;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "USER_ID")
	private User givenBy;
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "EVENT_ID")
	private Event event;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public User getGivenBy() {
		return givenBy;
	}

	public void setGivenBy(User givenBy) {
		this.givenBy = givenBy;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", content=" + content + ", dateCreated=" + dateCreated + ", givenBy=" + givenBy
				+ ", event=" + event + "]";
	}

}
