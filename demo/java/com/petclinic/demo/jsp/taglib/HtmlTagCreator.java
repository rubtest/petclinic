package com.petclinic.demo.jsp.taglib;

public class HtmlTagCreator {
	public static String createTag(String prefix, String s, String postfix) {
		return new StringBuilder().append(prefix).append(s).append(postfix).toString();
	}
}
