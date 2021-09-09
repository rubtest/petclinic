package com.petclinic.demo.jsp;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class JspHelper {

	public static final String USER_PARAM_NAME = "username";

	List<String> allowedUsers = new ArrayList<String>();

	{
		allowedUsers.add("Alice");
		allowedUsers.add("Bob");
		allowedUsers.add("Eve");
	}

	public boolean isError(HttpServletRequest request) {
		return request.getParameter(USER_PARAM_NAME) != null &&
		       allowedUsers.contains(request.getParameter(USER_PARAM_NAME));
	}

	public String getUsername(HttpServletRequest request) {
		return request.getParameter(USER_PARAM_NAME);
	}

	public String getOkButtonClass(HttpServletRequest request) {
		return "green-button";
	}

	public String getCancelButtonClass(HttpServletRequest request) {
		String colorClass = request.getParameter("colorclass");
		if (colorClass == null) {
			colorClass = "red-button";
		}
		return colorClass;
	}
}
