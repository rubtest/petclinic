<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="rips" uri="http://petclinic.com/jsp/demo/taglib" %>
<%@ include file="init.jsp" %>

<% setup(request); %>

<html><body>
    <div class="someclass">
        <c:if test="<%= h.isError(rq) %>">
            User <%= h.getUsername(rq) %> does not exist.
        </c:if>
        <%! String uq; %>
        <% uq = sqlQuery + h.getUsername(rq); %>
        <c:if test="<%= !h.isError(rq) %>">
            The requested user's number of files is
            <sql:query sql='<%= uq %>' />.
        </c:if>

        <div class="buttons">
            <petclinic:cancelButton myAttr = "<%= h.getCancelButtonClass(rq) %>" />
        </div>
    </div>
</body></html>