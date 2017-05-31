<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="print" %>
<html>

<fmt:message key="courses.title" var="courses_title"/>
<c:set var="title" scope="request" value="${courses_title}"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>

<%@ include file="/WEB-INF/jspf/header.jspf" %>

<div class="container">

    <%@ include file="/WEB-INF/jspf/menu.jspf"%>

    <%@ include file="/WEB-INF/jspf/check_error.jspf"%>

    <div class="content">
        <table class="table">
            <thead>
            <tr>
                <th><fmt:message key="subject.name"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="subject" items="${subjects}">
                <tr>
                    <td><c:out value="${subject.name}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>