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
            <input type="hidden" name="command" value="AddCourse">

            <div class="form-field">
                <label for="name"><fmt:message key="course.name"/></label><br>
                <input id="name" type="text" name="course_name"
                       pattern="<%=Regex.COURSE_NAME.getPattern()%>" required>
            </div>
            <div class="form-field">
            <label for="subject"><fmt:message key="course.subject"/></label><br>
                <select id="subject" name="subject_id" required>
                    <c:forEach var="subject" items="${subjects}">
                        <option value="${subject.id}">
                                <c:out value="${subject.name}"/>
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-field">
                <label for="teacher"><fmt:message key="course.teacher"/></label><br>
                <select id="teacher" name="teacher_id">
                    <c:forEach var="teacher" items="${teachers}">
                        <option value="${teacher.id}">
                                <c:out value="${teacher.firstName} ${teacher.lastName}"/>
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-field">
                <label for="start_date"><fmt:message key="course.start_date"/></label><br>
                <input id="start_date" name="start_date" type="date" required/>
            </div>
            <div class="form-field">
                <label for="expiration_date"><fmt:message key="course.expiration_date"/></label><br>
                <input id="expiration_date" name="expiration_date" type="date" required/>
            </div>

            <fmt:message key="submit.add_course" var="submit"/>
            <input type="submit" value="${submit}"/>
        </form>
    </div>
</div>
</body>
</html>
