<%@ page import="com.petclinic.demo.jsp.JspHelper" %>
<%! JspHelper h = new JspHelper(); %>
<%! HttpServletRequest rq; %>
<%! final static String sqlQuery = "SELECT NUMBER_OF_FILES FROM USERS WHERE NAME = "; %>
<%!
    public void setup(HttpServletRequest rq) {
        this.rq = rq;
    }
%>