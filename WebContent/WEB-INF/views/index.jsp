<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="../layout/app.jsp">
    <c:param name="content">
        <h2>タスク一覧</h2>
        <a href="${pageContext.request.contextPath}/new">タスクの追加</a><br/>
        <br/>
        <table>
            <tbody>
            <c:forEach var="task" items="${tasks}">
            <tr><td><a href="${pageContext.request.contextPath}/show?id=${task.id}">
                    <c:out value="${task.content}"/></a></td>
                    <td><fmt:formatDate value="${task.deadline}" pattern="yyyy-MM-dd HH:mm" /></td></tr>

        </c:forEach>
            </tbody>
        </table>


    <div id="pagenation">
        (全 ${tasks_count} 件)<br/>
        <c:forEach var="i" begin="1" end="${((messages_count - 1) / 15) + 1}" step="1">
            <c:choose>
                <c:when test="${i == page}">
                    <c:out value="${i}"/>&nbsp;
               </c:when>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/index?page=${i}"><c:out value="${i}"/></a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </div>
    </c:param>
</c:import>