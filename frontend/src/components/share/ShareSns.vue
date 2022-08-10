<template>
    <div>
        <a id="btnTwitter" class="link-icon twitter" @click="shareTwitter()"></a>
        <a id="btnFacebook" class="link-icon facebook" @click="shareFacebook()"></a>    
        <a id="btnKakao" class="link-icon kakao" @click="shareKakao()"></a>  
    </div>
</template>

<script>
const {Kakao} = window;
const url = "https://i7e201.p.ssafy.io"
export default {
methods: {
    shareTwitter() {
    var sendText = "OnSikGo입니다."; 
    var sendUrl = url;
    window.open("https://twitter.com/intent/tweet?text=" + sendText + "&url=" + sendUrl);
},
    shareFacebook() {
        var sendUrl = url;
        var imageUrl= "https://onsikgo.s3.ap-northeast-2.amazonaws.com/icon/real_logo.png";
        var totalurl=encodeURIComponent(sendUrl+'?img='+imageUrl);
        window.open ('http://www.facebook.com/sharer.php?u='+totalurl,'','width=500, height=500, scrollbars=yes, resizable=no');
    },
    shareKakao() {
    Kakao.Link.createDefaultButton({
        container: '#btnKakao', 
        objectType: 'feed',
        content: {
        title: "OnSikGo입니다.", 
        description: "지구를 구하는 마지막 주문, 당일 폐기 예정 음식 정보 공유 사이트입니다.", 
        imageUrl: "https://onsikgo.s3.ap-northeast-2.amazonaws.com/icon/real_logo.png", 
        link: {
            mobileWebUrl: url,
            webUrl: url
        }
        }
    });
        }
    }
}
</script>
<style scoped>
.link-icon { 
    position: relative; 
    display: inline-block; 
    width: auto;    
    font-size: 14px; 
    font-weight: 500; 
    color: #333; 
    margin-right: 10px; 
    padding-top: 50px; 
}
.link-icon.twitter { 
    background-image: url(https://onsikgo.s3.ap-northeast-2.amazonaws.com/icon/icon-twitter.png); 
    background-repeat: no-repeat; 
    width: 50px;
}
.link-icon.facebook { 
    background-image: url(https://onsikgo.s3.ap-northeast-2.amazonaws.com/icon/icon-facebook.png); 
    background-repeat: no-repeat; 
    width: 50px;
} 
.link-icon.kakao { 
    background-image: url(https://onsikgo.s3.ap-northeast-2.amazonaws.com/icon/icon-kakao.png); 
    background-repeat: no-repeat; 
    width: 50px;
}
</style>