<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<html>

<fmt:message key="evaluation.title" var="evaluation_title"/>
<c:set var="title" scope="request" value="${evaluation_title}"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>

<%@ include file="/WEB-INF/jspf/header.jspf" %>

<div class="container">

    <%@ include file="/WEB-INF/jspf/menu.jspf" %>

    <%@ include file="/WEB-INF/jspf/check_error.jspf" %>

    <div class="content">
        <table class="table">
            <thead>
            <tr>
                <th><fmt:message key="course.name"/></th>
                <th><fmt:message key="course.start_date"/></th>
                <th><fmt:message key="course.expiration_date"/></th>
                <th><fmt:message key="user.first_name"/></th>
                <th><fmt:message key="user.last_name"/></th>
                <th><fmt:message key="journal.mark"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="cortege" items="${corteges}">
                <tr>
                    <td>${cortege.course.name}</td>
                    <td>${cortege.course.startDate}</td>
                    <td>${cortege.course.expirationDate}</td>
                    <td>${cortege.student.firstName}</td>
                    <td>${cortege.student.lastName}</td>
                    <td>
                        <c:if test="${cortege.journal.mark != 0}">
                            ${cortege.journal.mark}
                        </c:if>
                    </td>
                    <td>
                        <form method="get" action="controller">
                            <input type="hidden" name="command" value="Evaluate"/>
                            <input type="hidden" name="course_id" value="${cortege.course.id}"/>
                            <input type="hidden" name="student_id" value="${cortege.student.id}"/>
                            <input type="hidden" name="mark" value="${cortege.journal.mark}"/>
                            <fmt:message key="evaluate.submit" var="submit"/>
                            <input type="submit" value="${submit}"/>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <table class="table">
            <thead>
            <tr>
                <th><fmt:message key="course.name"/></th>
                <th><fmt:message key="course.start_date"/></th>
                <th><fmt:message key="course.expiration_date"/></th>
                <th><fmt:message key="user.first_name"/></th>
                <th><fmt:message key="user.last_name"/></th>
                <th><fmt:message key="journal.avg_mark"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="cortege" items="${courses_with_avg_mark}">
                <tr>
                    <td>${cortege.course.name}</td>
                    <td>${cortege.course.startDate}</td>
                    <td>${cortege.course.expirationDate}</td>
                    <td>${cortege.student.firstName}</td>
                    <td>${cortege.student.lastName}</td>
                    <td>
                        <c:if test="${cortege.journal.mark != 0}">
                            ${cortege.journal.mark}
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>