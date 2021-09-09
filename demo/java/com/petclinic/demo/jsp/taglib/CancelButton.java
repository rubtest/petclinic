package com.petclinic.demo.jsp.taglib;

public class CancelButton {
	private static final String PREFIX = "<button type='button' class='";
	private static final String POSTFIX = "'>Cancel</button>";
	private String s;

	public CancelButton(String s) {
		this.s = s;
	}

	@Override
	public String toString() {
		return HtmlTagCreator.createTag(PREFIX, s, POSTFIX);
	}
}
