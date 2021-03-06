<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${task != null}">
                <h2>${task.content} の詳細</h2>
                    <table class="table table-hover">
                    <tbody>
                        <tr><th>内容</th><td>${task.content}</td></tr>
                        <tr><th>最終更新日</th><td>${task.updated_at}</td></tr>
                        <tr><th>締め切り</th><td>${task.deadline}</td></tr>
                    </tbody>

                    </table>
                    <div class="mt-2">
                    <a href="${pageContext.request.contextPath}/index"  class="btn btn-secondary btn-sm mr-2">一覧に戻る</a>
                    <a href="${pageContext.request.contextPath}/edit?id=${task.id}"  class="btn btn-secondary btn-sm">編集する</a>
                    </div>

            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした</h2>

                <p><a href="${pageContext.request.contextPath}/index">一覧に戻る</a></p>
            </c:otherwise>
        </c:choose>
    </c:param>
</c:import>
