<%@ page import="com.lanzdev.util.Regex" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<fmt:message key="add_subject_form.title" var="title_message"/>
<c:set var="title" scope="request" value="${title_message}"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>

<%@ include file="/WEB-INF/jspf/header.jspf" %>

<div class="container">

    <%@ include file="/WEB-INF/jspf/menu.jspf" %>

    <%@ include file="/WEB-INF/jspf/check_error.jspf"%>

    <div class="form-container">
        <form method="post" action="controller">
            <input type="hidden" name="command" value="AddSubject">

            <div class="form-field">
                <label for="subject_name"><fmt:message key="subject.name"/></label>
                <input id="subject_name" type="text" name="subject_name"
                       pattern="<%=Regex.COURSE_NAME.getPattern()%>" required/>
            </div>

            <fmt:message key="submit.add_subject" var="submit"/>
            <input type="submit" value="${submit}"/>
        </form>
    </div>
</div>
</body>
</html>
