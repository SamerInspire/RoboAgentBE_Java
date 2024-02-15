package com.takamol.roboagent.gateway.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.cfg.ImprovedNamingStrategy;

@Entity
@Table(name = "logs")
public class LogsVO {
	@Id
	@Column(name = "ID")
	private int ID;

	@Column(name = "service_code")
	private String service_code;

	@Column(name = "requester_ip")
	private String requester_ip;

	@Column(name = "request")
	private String request;

	@Column(name = "response")
	private String response;

	@Column(name = "creation_date")
	private Date creation_date;

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getService_code() {
		return service_code;
	}

	public void setService_code(String service_code) {
		this.service_code = service_code;
	}

	public String getRequester_ip() {
		return requester_ip;
	}

	public void setRequester_ip(String requester_ip) {
		this.requester_ip = requester_ip;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public Date getCreation_date() {
		return creation_date;
	}

	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}

	@Override
	public String toString() {
		return "LogsVO [ID=" + ID + ", service_code=" + service_code + ", requester_ip=" + requester_ip + ", request="
				+ request + ", response=" + response + ", creation_date=" + creation_date + "]";
	}

}
