package com.mum.eventmanagement.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.mum.eventmanagement.exception.EventException;

@Entity
public class Event {

	@Id
	@GeneratedValue
	private Integer id;

	@NotEmpty(message = "{NotEmpty}")
	private String name;

	@NotEmpty(message = "{NotEmpty}")
	@Column(columnDefinition = "TEXT")
	private String description;

	@Min(value = 1, message = "{MinmumSizeLimit}")
	private Integer membersSizeLimit;

	@Column(columnDefinition = "TEXT")
	private String resourcesDescription;

	@Type(type = "date")
	@Future(message = "{Future}")
	@DateTimeFormat(pattern = "HH:mm dd/MM/yyyy")
	@NotNull(message = "{NotNull}")
	private Date startTime;

	@Type(type = "date")
	@Future(message = "{Future}")
	@DateTimeFormat(pattern = "HH:mm dd/MM/yyyy")
	private Date endTime;

	private String category;
	@Transient
	private MultipartFile image;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATED_BY_USER_ID")
	private User createdBy;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "APPROVED_BY_USER_ID")
	private User approvedBy;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "APPROVED_MEMBERS_LIST", joinColumns = @JoinColumn(name = "EVENT_ID"), inverseJoinColumns = @JoinColumn(name = "USER_ID"))
	private List<User> approvedMembers;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "WAITING_MEMBERS_LIST", joinColumns = @JoinColumn(name = "EVENT_ID"), inverseJoinColumns = @JoinColumn(name = "USER_ID"))
	private List<User> waitingMembers;

	@OneToMany(mappedBy = "event", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<Comment> comments;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "MEMBERS_LIKE_LIST", joinColumns = @JoinColumn(name = "EVENT_ID"), inverseJoinColumns = @JoinColumn(name = "USER_ID"))
	private List<User> likedBy;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "assingedFor")
	private List<Resource> assingedResources;

	public void cancelJoinRequest(User member) {
		if (isPassed()) {
			throw new EventException("Event is already held.");
		}
		if (!this.approvedMembers.remove(member))
			this.waitingMembers.remove(member);
	}

	public void addJoinRequest(User member) {
		if (isPassed()) {
			throw new EventException("Event is already held.");
		}
		if (waitingMembers.contains(member)) {
			throw new EventException("Already Added");
		}
		waitingMembers.add(member);
	}

	public void approveJoinRequest(User member) {
		if (isPassed()) {
			throw new EventException("Event is already held.");
		}
		if (isFull()) {
			throw new IllegalStateException("Sorry Event can't accept more members.");
		}
		this.waitingMembers.remove(member);
		approvedMembers.add(member);
	}

	public void denyJoinRequest(User member) {
		if (isPassed()) {
			throw new EventException("Event is already held.");
		}
		if (approvedMembers != null)
			approvedMembers.remove(member);
	}

	public boolean isPassed() {
		boolean isPassed = false;
		if (startTime != null && startTime.before(new Date())) {
			isPassed = true;
		}
		return isPassed;
	}

	public boolean isFull() {
		boolean isFull = false;
		if (membersSizeLimit != null && membersSizeLimit == approvedMembers.size()) {
			isFull = true;
		}
		return isFull;
	}

	public void like(User user) {
		if (likedBy.contains(user)) {
			likedBy.remove(user);
		} else {
			likedBy.add(user);
		}
	}

	public boolean isLikedBy(String username) {
		for (User user : likedBy) {
			if (user.getUsername().matches(username)) {
				return true;
			}
		}
		return false;
	}

	public boolean isApproved(String username) {
		for (User user : approvedMembers) {
			if (user.getUsername().matches(username)) {
				return true;
			}
		}
		return false;
	}

	public boolean isWaitingForApproval(String username) {
		for (User user : this.waitingMembers) {
			if (user.getUsername().matches(username)) {
				return true;
			}
		}
		return false;
	}

	public boolean isRegistered(String username) {
		return isApproved(username) || isWaitingForApproval(username);
	}

	public int getLikeCounts() {
		return this.likedBy.size();
	}

	public int getApprovedCounts() {
		return this.approvedMembers.size();
	}

	public int getWaitingMembersCounts() {
		return this.waitingMembers.size();
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getMembersSizeLimit() {
		return membersSizeLimit;
	}

	public void setMembersSizeLimit(Integer membersSizeLimit) {
		this.membersSizeLimit = membersSizeLimit;
	}

	public String getResourcesDescription() {
		return resourcesDescription;
	}

	public void setResourcesDescription(String resourcesDescription) {
		this.resourcesDescription = resourcesDescription;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public User getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(User approvedBy) {
		this.approvedBy = approvedBy;
	}

	public List<User> getApprovedMembers() {
		return approvedMembers;
	}

	public void setApprovedMembers(List<User> approvedMembers) {
		this.approvedMembers = approvedMembers;
	}

	public List<User> getWaitingMembers() {
		return waitingMembers;
	}

	public void setWaitingMembers(List<User> waitingMembers) {
		this.waitingMembers = waitingMembers;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Resource> getAssingedResources() {
		return assingedResources;
	}

	public void setAssingedResources(List<Resource> assingedResources) {
		this.assingedResources = assingedResources;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public int getNumberOfJoines() {
		return this.approvedMembers.size();
	}

	public boolean isNotFull() {
		return membersSizeLimit == null ? true : (membersSizeLimit - getNumberOfJoines() > 0);
	}

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", name=" + name + ", description=" + description + ", category=" + category
				+ ", membersSizeLimit=" + membersSizeLimit + ", resourcesDescription=" + resourcesDescription
				+ ", createdBy=" + createdBy + ", approvedBy=" + approvedBy + ", startTime=" + startTime + ", endTime="
				+ endTime + "]";
	}

}
