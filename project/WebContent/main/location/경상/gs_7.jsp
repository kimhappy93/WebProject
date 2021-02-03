<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String id = (String)session.getAttribute("idKey");
%>     
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" href="../../../css/shape.css" type="text/css">
<link rel="stylesheet" href="../../../css/common.css" type="text/css">
<link rel="stylesheet" href="../../../css/loc.css" type="text/css">    
<script src="slide.js"></script> 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>

   function back(){
    history.go(-1)
} 
</script>  

</head>
<body>
 <div id="wrapper">
     
          <header id="header">
              <h1 class="title" onclick="javascript:location.href='../../main.jsp'">MOVIEMOA</h1>
            <form method="post" action="search.jsp" class="login">
               <%if (id != null) {%>
                 <span id="hi"><%=id %> 님, 반갑습니다.</span>
                 <button type="button" class="login" onclick="javascript:location.href='../../../login/logout.jsp'">Logout</button>
              <%} else {%>
              <button type="button" class="login" onclick="javascript:location.href='../../../login/login.jsp'">Login</button>	
              <button type="button" class="login" onclick="javascript:location.href='../../../login/agree.jsp'">Sign in</button><% } %>
              
	              <input type="text" name="search"  id="search" class="login" placeholder="Search...">
	              <input type="submit" id="searchBtn">
              </form>
                  
             <nav id="nav">
                  <span class="btn" onclick="javascript:location.href='../../../movieList.mo'">MovieInfo</span>
                  <span class="btn" onclick="javascript:location.href='../../../subpage/boardList.bo?boardtype=BOARDALL'">MovieTalk</span>
                  <span class="btn" onclick="javascript:location.href='../../../news/list.jsp?listtype=1'">MovieNEWS</span>
                  <span class="btn" onclick="javascript:location.href='../../loc.jsp'">Theater</span> 
              </nav>
         </header>
             
            <div id="loc_name">
             <h2>THEATER INFO</h2>        
            </div>
                     
        <!--
          <div id ="image_wrap">
            <div id = "loc_img" class="loc_img">
                <img src="../../images/del2.jfif" width="1100px" height="350px">  
            </div>
        </div>     
-->             
                     
           <div id="main">
           
           <div id="the_info">
               <caption><p class="info_name">모퉁이극장</p></caption>
               
               <table id="info_table" border="1" align="center">
                <tr>
                    <td>상세 주소</td>
                    <td>부산광역시 중구 중앙동 40계단길 7</td>
                </tr>
                <tr>
                    <td>전화번호</td> 
                    <td>051-465-1895</td>
                </tr>
                <tr>
                    <td>홈페이지</td> 
                    <td><a href="https://blog.naver.com/cornertheate/222140597464" target="_blank">https://blog.naver.com/cornertheate/222140597464</a></td>
                </tr>
                <tr>
                    <td width="200">지도</td>
                    <td><div id="map"><br>&nbsp;지도가 표시될 영역</div></td>
                </tr>
                <tr>
                    <td>기타</td>
                    <td></td>
                </tr>
               </table>
               <p class="info_list" onclick="back()">목록으로</p> 
           </div>

       </div>
        <div id="footer">
                  <article>
              copyright@<br><br><hr><br>MOVIE주식회사: 인천광역시 서구 서곶로 284 새터빌딩 4층 연희직업전문학교<p/>대표전화: 032-555-1111&nbsp;/&nbsp;Email: movie@gmail.com 
                </article>      
              </div>
    </div>
    
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=953c1d4d4db1458d5c8700392f39c23d"></script>  
    
    <script>
    var container = document.getElementById('map');
    var options = {
			center: new kakao.maps.LatLng(35.104094, 129.035093),
			level: 3
		};

    var map = new kakao.maps.Map(container, options);
        
        // 지도를 클릭한 위치에 표출할 마커입니다
        var marker = new kakao.maps.Marker({ 
        // 지도 중심좌표에 마커를 생성합니다 
        position: map.getCenter() 
        }); 
        // 지도에 마커를 표시합니다
        marker.setMap(map);
         
   if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(function(position){
        
        var lat = position.coords.latitude, 
            lon = position.coords.longitude;
        
        var locPosition = new kakao.maps.LatLng(lat, lon)
         
        displayMarker(locPosition);   
      });
    } else {
    
    var locPosition = new kakao.maps.LatLng(35.104094, 129.035093)   
        message = 'geolocation을 사용할수 없어요..'
        
    displayMarker(locPosition, message);
}

    function displayMarker(locPosition){

    var marker = new kakao.maps.Marker({  
        map: map, 
        position: locPosition
    }); 
    
    map.setCenter(locPosition);      
    };     
        
//    var iwContent = '<span>KU 시네마테크</span>',
//    iwPosition = new kakao.maps.LatLng(37.543107, 127.073067),
//     
//    infowindow = new kakao.maps.InfoWindow({
//    map: map,
//    position : iwPosition, 
//    content : iwContent,
//});    
        
    var zoomControl = new kakao.maps.ZoomControl();
    map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
        
    </script>
    

</body>
</html>