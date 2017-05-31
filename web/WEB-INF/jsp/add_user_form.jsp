<%@ page import="com.lanzdev.util.Regex" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<fmt:message key="add_user_form.title" var="title_message"/>
<c:set var="title" scope="request" value="${title_message}"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>

<%@ include file="/WEB-INF/jspf/header.jspf" %>

<div class="container">

    <%@ include file="/WEB-INF/jspf/menu.jspf" %>

    <%@ include file="/WEB-INF/jspf/check_error.jspf"%>

    <div class="form-container">
        <form method="post" action="controller">
            <input type="hidden" name="command" value="AddUser">

            <div class="form-field">
                <label for="login"><fmt:message key="user.login"/></label<br><br>
                <input id="login" type="text" name="login"
                       pattern="<%=Regex.LOGIN.getPattern()%>" required>
            </div>
            <div class="form-field">
                <label for="password"><fmt:message key="user.password"/></label><br>
                <input id="password" type="text" name="password"
                       pattern="<%=Regex.LOGIN.getPattern()%>" required>
            </div>
            <div class="form-field">
                <label for="permission"><fmt:message key="user.permission"/></label><br>
                <select id="permission" name="permission" required>
                    <c:forEach var="permission" items="${permissions}">
                        <option value="${permission}"
                                <c:out value="${permission}"/>
                                <c:if test="${permission.getName() eq desired_permission}">
                                    selected
                                </c:if>>
                            <c:out value="${permission}"/>
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-field">
                <label for="first_name"><fmt:message key="user.first_name"/></label><br>
                <input id="first_name" name="first_name"
                       pattern="<%=Regex.USER_NAME.getPattern()%>" required>
            </div>
            <div class="form-field">
                <label for="last_name"><fmt:message key="user.last_name"/></label><br>
                <input id="last_name" name="last_name"
                       pattern="<%=Regex.USER_NAME.getPattern()%>" required>
            </div>

            <fmt:message key="submit.add_user" var="submit"/>
            <input type="submit" value="${submit}"/>
        </form>
    </div>
</div>
</body>
</html>
