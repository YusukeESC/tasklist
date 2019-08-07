<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br/>
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}"/><br/>
        </c:forEach>
    </div>
</c:if>
<div class="form-group">
<label for="content">タスク</label><br/>
<input class="form-control" type="text" name="content" value="${task.content}"><br/><br/>
<label for="deadline">締め切り</label><br/>
<input class="form-control" type="datetime-local" name="deadline" step="60" value="${task.deadline.toString().replace(' ', 'T')}"/>

<input type="hidden" name="_token" value="${_token}" />
<button type="submit"  class="btn btn-secondary btn-sm mt-2">投稿</button>
</div>
