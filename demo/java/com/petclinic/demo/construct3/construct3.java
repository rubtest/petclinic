package com.petclinic.demo.construct3;

import javax.servlet.*;
import javax.servlet.http.*;

public class construct3 {

	HttpServletRequest request;
	HttpServletResponse response;
	ServletOutputStream outputStream = response.getOutputStream();
	String userInput;

	public void main() {
		userInput = request.getParameter("userInput");
		new Foo3();
	}

	class Foo1 {
		public Foo1() {
			outputStream.println(userInput);
		}
	}

	class Foo2 extends Foo1 {

	}

	class Foo3 extends Foo2 {

	}
}