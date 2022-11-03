let url = encodeURIComponent(window.location.href);
let sharingText = encodeURIComponent('sns 공유하기');
let sns_br = encodeURIComponent("\n");

// 공유하기 : kakao
Kakao.Share.createDefaultButton({
	container: '#kakaotalk-sharing-btn',
	objectType: 'feed',
	content: {
		title: '카카오톡 공유하기 테스트',
		description: '카카오톡 공유하기 기능 테스트입니다',
		// 메세지 템플릿 이미지 업로드 : https://developers.kakao.com/tool/template-builder/app/562374/template
		imageUrl: 'http://k.kakaocdn.net/dn/Q2iNx/btqgeRgV54P/VLdBs9cvyn8BJXB3o7N8UK/kakaolink40_original.png',
		link: {
			mobileWebUrl: url,
			webUrl: url,
		},
	},
	buttons: [
		{
			title: '웹으로 보기',
			link: {
				mobileWebUrl: url,
				webUrl: url,
			},
		},
	],
});

// 공유하기 : facebook
document.getElementById("facebook-sharing-btn").addEventListener("click", () => {
	window.open("https://www.facebook.com/sharer/sharer.php?u=" + url, "_blank", "width=600,height=400")
})

// 공유하기 : twitter
document.getElementById("twitter-sharing-btn").addEventListener("click", () => {
	window.open("https://twitter.com/intent/tweet?text=" + sharingText + "&url=" + url);
})

// 공유하기 : naver
document.getElementById("naver-sharing-btn").addEventListener("click", () => {
	window.open("https://share.naver.com/web/shareView?url=" + url + "&title=" + sharingText);
})

// 공유하기 : line
document.getElementById("line-sharing-btn").addEventListener("click", () => {
	window.open("http://line.me/R/msg/text/?" + sharingText + sns_br + url);
})
