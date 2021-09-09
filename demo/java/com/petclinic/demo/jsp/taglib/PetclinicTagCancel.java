package com.petclinic.demo.jsp.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class PetclinicTagCancel extends SimpleTagSupport {

	private String myAttr;

	public void setMyAttr(String myAttr) {
		this.myAttr = myAttr;
	}

	public void doTag() throws JspException, IOException {

		String escaped = sanitize(myAttr);
		CancelButton cancelButton = new CancelButton(escaped);

		JspWriter out = getJspContext().getOut();
		try {
			out.print(cancelButton.toString());
		} catch (IOException e) {
			throw new JspException(e);
		}
	}

	private String sanitize(String myAttr) {
		return replaceScriptTags(escapeQuotes(myAttr));
	}

	private String escapeQuotes(String input) {
		return input.replaceAll("\"", "");
	}

	private String replaceScriptTags(String input) {
		return input.replaceAll("<", "").replaceAll(">", "");
	}
}
