<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<fmt:message key="assign.title" var="title_message"/>
<c:set var="title" scope="request" value="${title_message}"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>

<%@ include file="/WEB-INF/jspf/header.jspf" %>

<div class="container">

    <c:if test="${not empty error}">
        <div class="error-wrap">
            <c:out value="${error}"/>
        </div>
    </c:if>

    <div class="assign-container">
        <form method="post" action="controller">
            <input type="hidden" name="command" value="AssignTeacherForCourse">
            <input type="hidden" name="course_id" value="${course.id}"/>

            <label><fmt:message key="course.name"/> : <c:out value="${course.name}"/></label>

            <label><fmt:message key="assign.teacher_choose"/></label>
            <select name="teacher_id">
                <c:forEach var="teacher" items="${teachers}">
                    <option value="${teacher.id}"
                            <c:if test="${course.teacherId eq teacher.id}">
                                selected
                            </c:if>>
                        <c:out value="${teacher.firstName} ${teacher.lastName}"/>
                    </option>
                </c:forEach>
            </select>

            <fmt:message key="submit.assign" var="submit"/>
            <input type="submit" value="${submit}"/>
        </form>
    </div>
</div>
</body>
</html>
