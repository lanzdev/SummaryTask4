<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<html>

<fmt:message key="students.title" var="students_title"/>
<c:set var="title" scope="request" value="${students_title}"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>

<%@ include file="/WEB-INF/jspf/header.jspf" %>

<div class="container">

    <%@ include file="/WEB-INF/jspf/menu.jspf"%>

    <c:if test="${not empty error_message}">
        <div class="error-wrap">
            <p>
                <c:out value="${error_message}"/>
            </p>
        </div>
    </c:if>

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
                    <td>
                        <form method="post" action="controller">
                            <input type="hidden" name="command" value="EditUser"/>
                            <input type="hidden" name="user_id" value="${user.id}">
                            <fmt:message key="user.edit_user" var="edit_user"/>
                            <input type="submit" value="${edit_user}">
                        </form>
                    </td>
                    <c:choose>
                        <c:when test="${user.blocked}">
                            <td>
                                <form method="post" action="controller">
                                    <input type="hidden" name="command" value="UnblockStudent"/>
                                    <input type="hidden" name="user_id" value="${user.id}">
                                    <fmt:message key="user.unblock" var="unblock_user"/>
                                    <input type="submit" value="${unblock_user}">
                                </form>
                            </td>
                        </c:when>
                        <c:when test="${!user.blocked}">
                            <td>
                                <form method="post" action="controller">
                                    <input type="hidden" name="command" value="BlockStudent"/>
                                    <input type="hidden" name="user_id" value="${user.id}">
                                    <fmt:message key="user.block" var="block_user"/>
                                    <input type="submit" value="${block_user}">
                                </form>
                            </td>
                        </c:when>
                    </c:choose>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>
