<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<%@ taglib uri="/WEB-INF/tlds/progress_printer" prefix="pp"%>

<html>

<fmt:message key="progress.title" var="progress_title"/>
<c:set var="title" scope="request" value="${progress_title}"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<%@ include file="/WEB-INF/jspf/header.jspf"%>

<div class="container">

    <%@ include file="/WEB-INF/jspf/menu.jspf" %>

    <%@ include file="/WEB-INF/jspf/check_error.jspf" %>

    <div class="content">
    <table class="table">
        <thead>
            <tr>
                <th><fmt:message key="course.name"/></th>
                <th><fmt:message key="course.subject"/></th>
                <th><fmt:message key="course.start_date"/></th>
                <th><fmt:message key="course.expiration_date"/></th>
                <th><fmt:message key="journal.mark"/></th>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="cortege" items="${progress_cortege}">
            <tr>
                <pp:print cortege="${cortege}"/>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    </div>
</div>
</body>
</html>
