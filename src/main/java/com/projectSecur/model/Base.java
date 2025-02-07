package com.projectSecur.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;

@MappedSuperclass
public abstract class Base {
	public abstract Long getId();
	@Transient
	String Error;
	public String getError() {
		return Error;
	}
	public void setError(String error) {
		Error = error;
	}
	@Column(name="CREATED_DATE")
	private Date createDate;
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
