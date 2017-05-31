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
        <div class="sorter">
            <form method="post" action="controller">
                <input type="hidden" name="command" value="ListCoursesSort">
                <label for="sort"><fmt:message key="sort.title"/></label>
                <select id="sort" name="compare_rule">
                    <option value="az"><fmt:message key="sort.az"/></option>
                    <option value="za"><fmt:message key="sort.za"/></option>
                    <option value="duration"><fmt:message key="sort.duration"/></option>
                </select>
                <fmt:message key="sort.sort" var="sort"/>
                <input type="submit" value="${sort}">
            </form>
        </div>
        <table class="table">
            <thead>
            <tr>
                <th><fmt:message key="course.name"/></th>
                <th><fmt:message key="course.subject"/></th>
                <th><fmt:message key="course.teacher"/></th>
                <th><fmt:message key="course.start_date"/></th>
                <th><fmt:message key="course.expiration_date"/></th>
                <c:if test="${user_permission != 'STUDENT'}">
                    <th><fmt:message key="course.subscribed_by"/></th>
                </c:if>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="course" items="${courses}">
                <tr>
                    <print:course course="${course}" user_permission="${user_permission}"/>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>