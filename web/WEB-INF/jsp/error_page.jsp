<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<html>

<fmt:message key="error.title" var="error_title"/>
<c:set var="title" scope="request" value="${error_title}"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body class="security-app">

<%@ include file="/WEB-INF/jspf/header.jspf" %>

<div class="container">
    <div class="content">
        <h3><fmt:message key="error.title"/></h3>

        <c:set var="code" value="${requestScope['javax.servlet.error.status_code']}"/>
        <c:set var="message" value="${requestScope['javax.servlet.error.message']}"/>

        <c:if test="${not empty code}">
            <h3>
                Error code:
                <c:out value="${code}"/>
            </h3>
        </c:if>

        <c:if test="${not empty error_message}">
            <div class="error-wrap">
                <p>
                    <c:out value="${error_message}"/>
                </p>
            </div>
        </c:if>
    </div>
</div>
</body>
</html>