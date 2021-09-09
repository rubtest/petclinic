package com.petclinic.demo.bank2;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.StringReader;

public class bank2 {

	HttpServletRequest request;
	HttpServletResponse response;
	ServletOutputStream outputStream = response.getOutputStream();
	String content = "";

	void run() {
		if (request.getParameter("action").equals("registration")) {
			registration();
		}
		outputStream.print(content);
	}

	void registration() {
		String tableName = request.getParameter("type_table");
		content += redirectMsg("Registration Continues", 1, "?action=reg_" + tableName);
	}

	String redirectMsg(String msg, int time, String location) {
		//String-replace logic.
		StringReader stringReader = new StringReader("<Some invalid template >_msg_ _time_ _location_ _seconds_");

		StringBuilder builder = new StringBuilder();
		int charsRead = -1;
		char[] chars = new char[100];

		do {
			charsRead = stringReader.read(chars, 0, chars.length);
			if (charsRead > 0) {
				builder.append(chars, 0, charsRead);
			}
		} while (chars > 0);

		String res = builder.toString()
				             .replaceAll("_msg_", msg)
				             .replaceAll("_time_", Integer.toString(time))
				             .replaceAll("_location_", location)
				             .replaceAll("_seconds_", Integer.toString(time * 1000));
		return res;
	}
}