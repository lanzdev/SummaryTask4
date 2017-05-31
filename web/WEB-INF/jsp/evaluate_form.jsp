<%@ page import="com.lanzdev.util.Regex" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<fmt:message key="add_course_form.title" var="title_message"/>
<c:set var="title" scope="request" value="${title_message}"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>

<%@ include file="/WEB-INF/jspf/header.jspf" %>

<div class="container">

    <%@ include file="/WEB-INF/jspf/menu.jspf" %>

    <%@ include file="/WEB-INF/jspf/check_error.jspf" %>

    <div class="form-container">
        <form method="post" action="controller">
            <input type="hidden" name="command" value="Evaluate"/>
            <input type="hidden" name="course_id" value="${course.id}"/>
            <input type="hidden" name="student_id" value="${student.id}"/>

            <div class="form-field">
                <label><fmt:message key="course.name"/>:<br>
                    <c:out value="${course.name}"/></label>
            </div>
            <div class="form-field">
                <label><fmt:message key="student.student"/>:<br>
                    <c:out value="${student.firstName} ${student.lastName}"/>
                </label>
            </div>
            <div class="form-field">
                <label><fmt:message key="journal.mark"/></label><br>
                <input name="mark"
                        <c:if test="${mark != null}">
                            value="${mark}"
                        </c:if>
                       pattern="<%=Regex.MARK.getPattern()%>"
                       required
                />
                </label>
            </div>
            <div class="form-field">
                <fmt:message key="submit.apply_changes" var="submit"/>
                <input type="submit" value="${submit}"/>
            </div>
        </form>
    </div>
</div>
</body>
</html>
