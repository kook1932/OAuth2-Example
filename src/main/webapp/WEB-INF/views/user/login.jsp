<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../../../include/header.jsp"/>

<h1>로그인 페이지</h1>
<div class="row">
	<div class="col-md-6">
		<c:if test="${empty userName}">
			<%-- 스프링 시큐리티에서 기본적으로 제공하는 로그인 URL	--%>
			<a href="/" class="btn btn-primary active" role="button">Home</a>
			<a href="/oauth2/authorization/google" class="btn btn-success active" role="button">Google Login</a>
			<a href="/oauth2/authorization/naver" class="btn btn-secondary active" role="button">Naver Login</a>
			<a href="/oauth2/authorization/kakao" class="btn btn-warning active" role="button">Kakao Login</a>
		</c:if>
	</div>
</div>

<jsp:include page="../../../include/footer.jsp"/>