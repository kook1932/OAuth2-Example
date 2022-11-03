<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>SNS Login, Sharing</title>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	<link rel="stylesheet" href="/assets/css/index.css">
	<%-- 공유하기 : facebook --%>
	<meta property="og:title"			content="og title"/>
	<meta property="og:description"		content="og description"/>
	<meta property="og:keyword"			content="og keyword"/>
	<meta property="og:url"				content="https://hdm2.doesbook.kr"/>
	<meta property="og:type"			content="website"/>
	<meta property="og:image"			content="https://hdm2.doesbook.kr/assets/images/smilepet.jpeg"/>
	<meta property="og:image:width"		content="1200">
	<meta property="og:image:height"	content="631">
	<%-- 공유하기 : twitter --%>
	<meta name="twitter:title"			content="twitter sharing title"/>
	<meta name="twitter:description"	content="twitter sharing description"/>
	<meta name="twitter:url"	        content="https://hdm2.doesbook.kr"/>
	<meta name="twitter:card"			content="summary_large_image"/>
	<meta name="twitter:image:src"      content="https://hdm2.doesbook.kr/assets/images/smilepet.jpeg"/>
	<%-- 공유하기 : kakao --%>
	<script src="/assets/lib/kakao.min.js"></script>
	<script>
		Kakao.init('120203cc95807a7db529b2866810b536'); // 사용하려는 앱의 JavaScript 키 입력
	</script>
</head>
<body>