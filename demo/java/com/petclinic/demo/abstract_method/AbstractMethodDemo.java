package com.petclinic.demo.abstract_method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AbstractMethodDemo {

	public interface InputPreparator {
		String prepareInput(String input);
	}

	public class Impl1 implements InputPreparator {
		@Override
		public String prepareInput(String input) {
			return "Hi, your name is".concat("\"" + input + "\"");
		}
	}

	public class Impl2 implements InputPreparator {
		@Override
		public String prepareInput(String input) {
			return "static string";
		}
	}

	public class Impl3 implements InputPreparator {
		@Override
		public String prepareInput(String input) {
			StringBuilder sb = new StringBuilder();
			sb.append("Hello").append("'").append(input).append("'");
			return sb.toString();
		}
	}

	InputPreparator inputPreparator;

	public AbstractMethodDemo(InputPreparator preperator) {
		this.inputPreparator = preperator;
	}

	public void requestHandler(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String name = request.getParameter("username");
		response.getWriter().println(inputPreparator.prepareInput(name));
	}
}
