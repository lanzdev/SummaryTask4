<%@tag pageEncoding="UTF-8"%>
<%@tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="course" required="true" type="com.lanzdev.domain.entity.Course"%>
<%@ attribute name="user_permission" required="true" type="com.lanzdev.domain.Permission"%>

<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<td>${course.name}</td>
<td>${course.subjectName}</td>
<td>${course.teacherName}</td>
<td>${course.startDate}</td>
<td>${course.expirationDate}</td>
<c:if test="${user_permission != 'STUDENT'}">
    <td>${course.subscribedBy}</td>
</c:if>
<c:if test="${user_permission == 'STUDENT' and sessionScope.is_for_subscribe}">
    <td>
        <form method="post" action="controller">
            <input type="hidden" name="command" value="SubscribeCourse">
            <input type="hidden" name="course_id" value="${course.id}"/>
            <fmt:message key="course.subscribe_course" var="subscribe_course"/>
            <input type="submit" value="${subscribe_course}"/>
        </form>
    </td>
</c:if>
<c:if test="${user_permission == 'ADMIN'}">
    <td>
        <form method="get" action="controller">
            <input type="hidden" name="command" value="AssignTeacherForCourse">
            <input type="hidden" name="course_id" value="${course.id}"/>
            <fmt:message key="assign.edit_teacher" var="edit_teacher"/>
            <input type="submit" value="${edit_teacher}"/>
        </form>
    </td>
    <td>
        <form method="get" action="controller">
            <input type="hidden" name="command" value="EditCourse"/>
            <input type="hidden" name="course_id" value="${course.id}"/>
            <fmt:message key="course.edit_course" var="edit_course"/>
            <input type="submit" value="${edit_course}">
        </form>
    </td>
    <td>
        <form method="post" action="controller">
            <input type="hidden" name="command" value="DeleteCourse"/>
            <input type="hidden" name="course_id" value="${course.id}"/>
            <fmt:message key="course.delete_course" var="delete_course"/>
            <input type="submit" value="${delete_course}">
        </form>
    </td>
</c:if>