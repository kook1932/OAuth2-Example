<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<a href="/" class="btn btn-primary active" role="button">Home</a>
<c:if test="${not empty userName}">
	Log in as : <span id="user">${userName}</span>
	<a href="/logout" class="btn btn-info active" role="button">Logout</a>
</c:if>
<c:if test="${empty userName}">
	<a href="/user/login" class="btn btn-info active" role="button">Login</a>
</c:if>