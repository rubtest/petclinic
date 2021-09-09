package com.petclinic.demo.lombok;

import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

public class TestDataAnnotations {

	public void testDataAnnotationOnClass(@RequestBody MySource mysource) {
		File f = new File(mysource.getName());
	}

	public void testDataAnnotationOnField(@RequestBody MySource2 mysource) {
		File f = new File(mysource.getName());
	}

	public void testDataAnnotationWithGetterAndSetter(HttpServletRequest request) throws IOException {

		String s = request.getParameter("userinput");

		MyDataClass data = new MyDataClass();
		data.setName(s);

		File f = new File(data.getName());
	}
}
