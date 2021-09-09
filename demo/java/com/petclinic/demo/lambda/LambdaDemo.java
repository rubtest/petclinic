package com.petclinic.demo.lambda;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LambdaDemo {

	public void lambdaDemo1(HttpServletRequest request, HttpServletResponse response) {

		String username = request.getParameter("username");

		List<String> greetings = new ArrayList<>();
		greetings.add("Hello");
		greetings.add("Hi");

		greetings.forEach(greeting -> {
			try {
				response.getWriter().println(greeting + " " + username + "!<br/>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
}