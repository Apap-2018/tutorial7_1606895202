package com.apap.tutorial7.rest;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PilotDetail {
	private String status;
	
	@JsonProperty("valid-until")
	private Date validUntil;
	
	public void setStatus(String status) {
		this.status = status;
	}

	public Date getValidUntil() {
		return validUntil;
	}

	public void setValidUntil(Date validUntil) {
		this.validUntil = validUntil;
	}

	public String getStatus() {
		return status;
	}
	
	
	
}
