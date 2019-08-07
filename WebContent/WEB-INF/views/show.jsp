<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../layout/app.jsp">
    <c:param name="content">
        <h2>${task.content} の詳細</h2>
        <table>
        <tbody>
            <tr><th>内容</th><td>${task.content}</td></tr>
            <tr><th>最終更新日</th><td>${task.updated_at}</td></tr>
            <tr><th>締め切り</th><td>${task.deadline}</td></tr>
        </tbody>

        </table>
        <a href="${pageContext.request.contextPath}/index">一覧に戻る</a><br/>
        <a href="${pageContext.request.contextPath}/edit?id=${task.id}">編集する</a><br/>

    </c:param>
</c:import>
