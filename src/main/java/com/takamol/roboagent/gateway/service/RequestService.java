package com.takamol.roboagent.gateway.service;

import javax.servlet.http.HttpServletRequest;

public interface RequestService {
	
	String getClientIp(HttpServletRequest request);
	
}
