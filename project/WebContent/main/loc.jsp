<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");		//Server -> server.xml UTI?인코딩 UTF-8로 변환하니 해결
	String id = (String)session.getAttribute("idKey");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>극장</title>
<link rel="stylesheet" href="../css/shape.css" type="text/css">
<link rel="stylesheet" href="../css/common.css" type="text/css">
<link rel="stylesheet" href="../css/loc.css" type="text/css">    
<script src="js/slide.js"></script>
<script src="js/link.js"></script>    
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
function fetchPage(name){
    fetch(name).then(function(response){
                    response.text().then(function(text){
                    document.querySelector('#the').innerHTML = text;
                })
            })
}
</script>

</head>
<body>
  <div id="wrapper">	
     <header id="header">
              <h1 class="title" onclick="javascript:location.href='main.jsp'">MOVIEMOA</h1>
            <form method="post" action="search.jsp" class="login">
            
             <%
             String ids = request.getParameter("id");
             if (id == null) {%>
             <button type="button" class="login" onclick="javascript:location.href='../login/login.jsp'">Login</button>	
              <button type="button" class="login" onclick="javascript:location.href='../login/agree.jsp'">Sign in</button>
                 
               <%}else if(id.equals("admin")){%>  
                <button type="button" class="login" onclick="javascript:location.href='../adminPage.jsp'">관리자 화면</button>
                 <button type="button" class="login" onclick="javascript:location.href='../login/logout.jsp'">Logout</button>
              <%} else if(id != null) {%>
              <span id="hi"><%=id %> 님, 반갑습니다.</span>
                 <button type="button" class="login" onclick="javascript:location.href='../login/logout.jsp'">Logout</button><%} %>
            
	              <input type="text" name="search"  id="search" class="login" placeholder="Search...">
	              <input type="submit" id="searchBtn">
              </form>
                  
             <nav id="nav">
                  <span class="btn" onclick="javascript:location.href='../movieList.mo'">MovieInfo</span>
                  <span class="btn" onclick="javascript:location.href='../subpage/boardList.bo?boardtype=BOARDALL'">MovieTalk</span>
                  <span class="btn" onclick="javascript:location.href='../newsList.mo'">MovieNEWS</span>
                  <span class="btn" onclick="javascript:location.href='loc.jsp'">Theater</span> 
              </nav>
            </header>  
         
         <div id="sidebar">
   			<div class="sub_tab">
   				<ul>
   					<li>
   						<span onclick="fetchPage('location/서울/seoul')"><a href="#the">서울</a></span>
   					</li>
   					<li>
   						<span onclick="fetchPage('location/경기/gyeonggi')"><a href="#the">경기</a></span>
   					</li>
   					<li>
   						<span onclick="fetchPage('location/인천/incheon')"><a href="#the">인천</a></span>
   					</li>
   					<li>
   						<span onclick="fetchPage('location/제주/jeju')"><a href="#the">제주</a></span>
   					</li>
   					<li>
   						<span onclick="fetchPage('location/강원/gangwon')"><a href="#the">강원도</a></span>
   					</li>
   					<li>
   						<span onclick="fetchPage('location/충청/chungc')"><a href="#the">충청도</a></span>
   					</li>
   					<li>
   						<span onclick="fetchPage('location/전라/jeonlado')"><a href="#the">전라도</a></span>
   					</li>
   					<li>
   						<span onclick="fetchPage('location/경상/gyeongs')"><a href="#the">경상도</a></span>
   					</li>
   				</ul>
   			</div>
   		</div>
         <div id="loc_name">
             <h2>THEATER INFO</h2>        
        </div>
               
         <div id ="image_wrap">
            <div id = "loc_img" class="loc_img">
                <img src="images/del2.jfif" width="1100px" height="350px">  
            </div>
        </div>
        
       <div id="main">
           
           <h3 id="loc_title"><span>독립영화 상영관</span></h3>
          	
          	<div class="loc_tab">
   				<ul>
   					<li>
   						<span onclick="fetchPage('location/서울/seoul')">서울</span>
   					</li>
   					<li>
   						<span onclick="fetchPage('location/경기/gyeonggi')">경기</span>
   					</li>
   					<li>
   						<span onclick="fetchPage('location/인천/incheon')">인천</span>
   					</li>
   					<li>
   						<span onclick="fetchPage('location/제주/jeju')">제주</span>
   					</li>
   					<li>
   						<span onclick="fetchPage('location/강원/gangwon')">강원도</span>
   					</li>
   					<li>
   						<span onclick="fetchPage('location/충청/chungc')">충청도</span>
   					</li>
   					<li>
   						<span onclick="fetchPage('location/전라/jeonlado')">전라도</span>
   					</li>
   					<li>
   						<span onclick="fetchPage('location/경상/gyeongs')">경상도</span>
   					</li>
   				</ul>
   			</div>
   			
           <div id="the">
                <table border="1" align="center" width="680" height="820"  class="theater_list">
                <thead>
                    <tr>
                        <td>극장명</td>
                        <td>극장위치</td>
                        <td>연락처</td>
                        <td>상세위치</td>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>KU 씨네마테크</td>
                        <td>광진구</td>
                        <td>02-446-6579</td>
                        <td><button type="button"><a href="location/서울/su_1.jsp">상세보기</a></button></td>
                    </tr>
                    <tr>
                        <td>롯데시네마 건대입구 아르떼클래식</td>
                        <td>광진구</td>
                        <td>1544-8855</td>
                        <td><button type="button"><a href="location/서울/su_2.jsp">상세보기</a></button></td>
                    </tr>
                    <tr>
                        <td>CGV 강변 아트하우스</td>
                        <td>광진구</td>
                        <td>1544-1122</td>
                        <td><button type="button"><a href="location/서울/su_3.jsp">상세보기</a></button></td>
                    </tr>
                    <tr>
                        <td>CGV 압구정 아트하우스</td>
                        <td>강남구</td>
                        <td>1544-1122</td>
                        <td><button type="button"><a href="location/서울/su_4.jsp">상세보기</a></button></td>
                    </tr>
                     <tr>
                        <td>자체휴강 시네마</td>
                        <td>관악구</td>
                        <td>010-9640-0987</td>
                        <td><button type="button"><a href="location/서울/su_5.jsp">상세보기</a></button></td>
                    </tr>
                    <tr>
                        <td>더숲 아트시네마</td>
                        <td>노원구</td>
                        <td>02-951-0206</td>
                        <td><button type="button"><a href="location/서울/su_6.jsp">상세보기</a></button></td>
                    </tr>
                    <tr>
                        <td>롯데시네마 월드타워 아르떼클래식</td>
                        <td>송파구</td>
                        <td>02-3213-5000</td>
                        <td><button type="button"><a href="location/서울/su_7.jsp">상세보기</a></button></td>
                    </tr>
                    <tr>
                        <td>아트나인</td>
                        <td>동작구</td>
                        <td>1544-0070</td>
                        <td><button type="button"><a href="location/서울/su_8.jsp">상세보기</a></button></td>
                    </tr>
                    <tr>
                        <td>아리랑시네센터</td>
                        <td>성북구</td>
                        <td>02-3291-5541</td>
                        <td><button type="button"><a href="location/서울/su_9.jsp">상세보기</a></button></td>
                    </tr>
                    <tr>
                        <td>대한극장</td>
                        <td>중구</td>
                        <td>02-3393-3600</td>
                        <td><button type="button"><a href="location/서울/su_10.jsp">상세보기</a></button></td>
                    </tr>
                    <tr>
                        <td>롯데시네마 신도림 아르떼클래식</td>
                        <td>구로구</td>
                        <td>1544-8855</td>
                        <td><button type="button"><a href="location/서울/su_11.jsp">상세보기</a></button></td>
                    </tr>
                    <tr>
                        <td>CGV 구로 아트하우스</td>
                        <td>구로구</td>
                        <td>1544-1122</td>
                        <td><button type="button"><a href="location/서울/su_12.jsp">상세보기</a></button></td>
                    </tr>
                    <tr>
                        <td>CGV 여의도 아트하우스</td>
                        <td>영등포구</td>
                        <td>1544-1122</td>
                        <td><button type="button"><a href="location/서울/su_13.jsp">상세보기</a></button></td>
                    </tr>
                    <tr>
                        <td>KT&G 시네마 상상마당</td>
                        <td>마포구</td>
                        <td>02-330-6263</td>
                        <td><button type="button"><a href="location/서울/su_14.jsp">상세보기</a></button></td>
                    </tr>
                     <tr>
                        <td>성미산 마을극장</td>
                        <td>마포구</td>
                        <td>02-322-0345</td>
                        <td><button type="button"><a href="location/서울/su_15.jsp">상세보기</a></button></td>
                    </tr>
                     <tr>
                        <td>한국영상자료원</td>
                        <td>마포구</td>
                        <td>02-3153-2001</td>
                        <td><button type="button"><a href="location/서울/su_16.jsp">상세보기</a></button></td>
                    </tr>
                    <tr>
                        <td>아트하우스 모모</td>
                        <td>서대문구</td>
                        <td>02-363-5333</td>
                        <td><button type="button"><a href="location/서울/su_17.jsp">상세보기</a></button></td>
                    </tr>
                    <tr>
                        <td>필름포럼</td>
                        <td>서대문구</td>
                        <td>02-363-2537</td>
                        <td><button type="button"><a href="location/서울/su_18.jsp">상세보기</a></button></td>
                    </tr>
                </tbody>
            </table>
                
               

           </div>

       </div>
       
      <div id="footer">
                  <article>
              copyright@<br><br><hr><br>MOVIE주식회사: 인천광역시 서구 서곶로 284 새터빌딩 4층 연희직업전문학교<p/>대표전화: 032-555-1111&nbsp;/&nbsp;Email: movie@gmail.com 
                </article>      
              </div>
    </div>
</body>
</html>