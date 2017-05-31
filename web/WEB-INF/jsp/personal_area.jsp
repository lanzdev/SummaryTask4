<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<fmt:message key="personal_area.title" var="personal_area_title"/>
<c:set var="title" scope="request" value="${personal_area_title}"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>

<%@ include file="/WEB-INF/jspf/header.jspf" %>

<div class="container">

    <c:if test="${user_permission != null}">
        <%@ include file="/WEB-INF/jspf/menu.jspf" %>
    </c:if>

    <%@ include file="/WEB-INF/jspf/check_error.jspf" %>

    <div class="form-container">
        <form method="post" action="controller">
            <div class="form-field">
                <label>
                    <fmt:message key="user.login"/> : <br>
                    <c:out value="${user.login}"/>
                </label>
            </div>
            <div class="form-field">
                <label>
                    <fmt:message key="user.password"/> : <br>
                    <c:out value="${user.password}"/>
                </label>
            </div>
            <div class="form-field">
                <label>
                    <fmt:message key="user.permission"/> : <br>
                    <c:out value="${user.permission}"/>
                </label>
            </div>
            <div class="form-field">
                <label>
                    <fmt:message key="user.first_name"/> : <br>
                    <c:out value="${user.firstName}"/>
                </label>
            </div>
            <div class="form-field">
                <label>
                    <fmt:message key="user.last_name"/> : <br>
                    <c:out value="${user.lastName}"/>
                </label>
            </div>
        </form>
    </div>
</div>

</body>
</html>
