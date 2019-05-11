package com.q.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Jobs entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "job" )
public class Job implements java.io.Serializable {

	// Fields

	private Long jobId;
	private User user;
	private String jobName;
	private String location;

	// Constructors

	/** default constructor */
	public Job() {
	}

	/** full constructor */
	public Job(User user, String jobName, String location) {
		this.user = user;
		this.jobName = jobName;
		this.location = location;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "job_id", unique = true, nullable = false)
	public Long getJobId() {
		return this.jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "owner_id")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "job_name", length = 250)
	public String getJobName() {
		return this.jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	@Column(name = "location", length = 250)
	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}