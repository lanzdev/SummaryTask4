<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<html>

<fmt:message key="users.title" var="users_title"/>
<c:set var="title" scope="request" value="${users_title}"/>
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
                <th><fmt:message key="user.login"/></th>
                <th><fmt:message key="user.password"/></th>
                <th><fmt:message key="user.permission"/></th>
                <th><fmt:message key="user.first_name"/></th>
                <th><fmt:message key="user.last_name"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>${user.login}</td>
                    <td>${user.password}</td>
                    <td>${user.permission}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>
                        <form method="post" action="controller">
                            <input type="hidden" name="command" value="DeleteUser"/>
                            <input type="hidden" name="user_id" value="${user.id}">
                            <fmt:message key="user.delete_user" var="delete_user"/>
                            <input type="submit" value="${delete_user}"/>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>
