<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>OAuth2 Example</title>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	<style>
		.sharing_btn {width:68px;height:69px;}
	</style>
	<%-- 공유하기 : facebook --%>
	<%-- 이미지 캐시 : https://developers.facebook.com/tools/debug/ --%>
	<meta property="og:title"			content="og title"/>
	<meta property="og:description"		content="og description"/>
	<meta property="og:keyword"			content="og keyword"/>
	<meta property="og:url"				content="http://localhost:8080/view"/>
	<meta property="og:type"			content="website"/>
	<meta property="og:image"			content="http://k.kakaocdn.net/dn/Q2iNx/btqgeRgV54P/VLdBs9cvyn8BJXB3o7N8UK/kakaolink40_original.png"/>
	<meta property="og:image:width"		content="1200">
	<meta property="og:image:height"	content="631">
	<%-- 공유하기 : twitter --%>
	<meta name="twitter:title"			content="twitter sharing title"/>
	<meta name="twitter:description"	content="twitter sharing description"/>
	<meta name="twitter:url"	        content="https://www.naver.com"/>
	<meta name="twitter:card"			content="summary_large_image"/>
	<meta name="twitter:image"			content="http://k.kakaocdn.net/dn/Q2iNx/btqgeRgV54P/VLdBs9cvyn8BJXB3o7N8UK/kakaolink40_original.png"/>
	<%-- 공유하기 : kakao --%>
	<script src="/assets/js/kakao.min.js"></script>
	<script>
		Kakao.init('120203cc95807a7db529b2866810b536'); // 사용하려는 앱의 JavaScript 키 입력
	</script>
</head>
<body>
<a id="kakaotalk-sharing-btn" href="javascript:;">
	<img src="/assets/images/kakaotalk_sharing_btn_medium.png" class="sharing_btn" alt="카카오톡 공유 보내기 버튼" />
</a>
<a id="facebook-sharing-btn" href="javascript:;">
	<img src="/assets/images/facebook_sharing_btn.png" class="sharing_btn" alt="페이스북 공유 보내기 버튼" />
</a>
<a id="twitter-sharing-btn" href="javascript:;">
	<img src="/assets/images/twitter_sharing_btn.png" class="sharing_btn" alt="트위터 공유 보내기 버튼" />
</a>
