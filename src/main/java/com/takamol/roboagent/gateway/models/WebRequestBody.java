package com.takamol.roboagent.gateway.models;

import java.util.ArrayList;
import java.util.List;

public class WebRequestBody {
	private UserVO user;
	private List<ComplaintVO> complaintVO = new ArrayList<ComplaintVO>();
	private String result;
	private String resultDescription;
	private String ZendeskTicketId;
	private String userInput;
	private String logType;

	public UserVO getUser() {
		return user;
	}

	public void setUser(UserVO user) {
		this.user = user;
	}

	public List<ComplaintVO> getComplaintVO() {
		return complaintVO;
	}

	public void setComplaintRequest(List<ComplaintVO> complaintVO) {
		this.complaintVO = complaintVO;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getResultDescription() {
		return resultDescription;
	}

	public void setResultDescription(String resultDescription) {
		this.resultDescription = resultDescription;
	}

	public String getZendeskTicketId() {
		return ZendeskTicketId;
	}

	public void setZendeskTicketId(String zendeskTicketId) {
		ZendeskTicketId = zendeskTicketId;
	}

	public String getUserInput() {
		return userInput;
	}

	public void setUserInput(String userInput) {
		this.userInput = userInput;
	}

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	@Override
	public String toString() {
		return "WebRequestBody [user=" + user + ", complaintVO=" + complaintVO + ", result=" + result
				+ ", resultDescription=" + resultDescription + ", ZendeskTicketId=" + ZendeskTicketId + ", userInput="
				+ userInput + ", logType=" + logType + "]";
	}

}
