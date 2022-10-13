<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../../include/header.jsp"/>

<h1>OAuth2 Example</h1>
<div class="row">
	<div class="col-md-6">
		<%@ include file="../../include/login.jsp"%>
		<a href="/myPage" class="btn btn-success active" role="button">My Page</a>
		<a href="/view" class="btn btn-dark active" role="button">일반 페이지</a>
	</div>
</div>

<jsp:include page="../../include/footer.jsp"/>