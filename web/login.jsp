<%@ page import="com.lanzdev.util.Regex" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<fmt:message key="login.title" var="login_title"/>
<c:set var="title" scope="request" value="${login_title}"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<script src='https://www.google.com/recaptcha/api.js'></script>
<body>

<%@ include file="/WEB-INF/jspf/header.jspf" %>

<div class="container">

    <c:if test="${user_permission != null}">
        <%@ include file="/WEB-INF/jspf/menu.jspf" %>
    </c:if>

    <div class="login-container">
        <c:if test="${not empty error}">
            <div class="error-wrap">
                <c:out value="${error}"/>
            </div>
        </c:if>
        <form method="post" action="controller">
            <input type="hidden" name="command" value="Login">

            <div>
                <div class="login-field">
                    <label for="login"><fmt:message key="login.login"/></label><br>
                    <fmt:message key="regex.title.login" var="regex_login_title"/>
                    <input id="login" type="text" name="login"
                           pattern="<%=Regex.LOGIN.getPattern()%>" title="${regex_login_title}" required>
                </div>
                <div class="g-recaptcha" data-sitekey="6LflTyMUAAAAAEsDbhwa5lzhXZEmkDpDrXHlrldx"></div>
                <div class="login-field">
                    <label for="password"><fmt:message key="login.password"/></label><br>
                    <fmt:message key="regex.title.password" var="regex_password_title"/>
                    <input id="password" type="password" name="password"
                           pattern="<%=Regex.PASSWORD.getPattern()%>" title="${regex_password_title}" required>
                </div>

                <fmt:message key="submit.login" var="submit"/>
                <input type="submit" value="${submit}"/>
            </div>
        </form>
    </div>
</div>
</body>
</html>
