<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%
String id = (String)session.getAttribute("idKey");
%>	
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>아이디 찾기</title>
<link rel="stylesheet" href="../css/common.css"> 
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js">
</script>
<style>
#main h1{
  margin-top: 80px;
  text-align:center;
}

#main h5{
	color: #777;
}

#findid{
	margin: 0 auto;
  width: 400px;
  height: 200px;
  padding:35px;
  margin-bottom:300px;
  text-align: center;
  border-top:1px solid #333;
  border-bottom:1px solid #333;
}

#findid label {
  display: inline;
  font-size: 15px;
  font-weight: bold;
  margin-right:15px;
}

.findbtn{
	width:140px;
	height:30px;
	margin:13px;
	margin-top:25px;
	font-size:13px;
	font-weight:200;
	cursor:pointer;
	background-color:#333;
	color:white;
}
</style>
</head>
<body>
 		<header id="header">
              <h1 class="title" onclick="javascript:location.href='../main/main.jsp'">MOVIEMOA</h1>
            <form method="post" action="../main/search.jsp" class="login">
               <%if (id != null) {%>
                 <span id="hi"><%=id %> 님, 반갑습니다.</span>
                 <button type="button" class="login" onclick="javascript:location.href='logout.jsp'">Logout</button>
              <%} else {%>
              <button type="button" class="login" onclick="javascript:location.href='login.jsp'">Login</button>	
              <button type="button" class="login" onclick="javascript:location.href='agree.jsp'">Sign in</button><% } %>
              
	              <input type="text" name="search"  id="search" class="login" placeholder="Search...">
	              <input type="submit" id="searchBtn">
              </form>
                  
             <nav id="nav">
                  <span class="btn" onclick="javascript:location.href='../movieList.mo'">MovieInfo</span>
                  <span class="btn" onclick="javascript:location.href='../subpage/boardList.bo?boardtype=BOARDALL'">MovieTalk</span>
                  <span class="btn" onclick="javascript:location.href='../newsList.mo'">MovieNEWS</span>
                  <span class="btn" onclick="javascript:location.href='../main/loc.jsp'">Theater</span> 
              </nav>
          </header>
              
	  <div id="main" align="center">    
	   
	<form name="searchId" method="post" action="searchfind.jsp">
		<h1>아이디 찾기</h1>
		<h5>※회원정보에 등록된 이름과 이메일로 아이디를 알려드립니다.</h5>
		
		<div id="findid">
		
			<label style="margin-right:27px">이름</label>
			<input type="text" name="Name" placeholder="이름을 입력하세요.">
		<p/>
			<label>이메일</label>
			<input type="text" name="Email" placeholder="이메일을 입력하세요.">
		<p/>
		<input type="submit" class="findbtn" name="search" value="찾기">
		<input type="button" class="findbtn" name="cancel" value="취소" onclick="location.href='login.jsp'">
		
		</div>
		
	</form>		
	
		</div>
	<div id="footer">
       <article>copyright@<br><br><hr><br>MOVIE주식회사: 인천광역시 서구 서곶로 284 새터빌딩 4층 연희직업전문학교<p/>대표전화: 032-555-1111&nbsp;/&nbsp;Email: movie@gmail.com</article>      
    </div>
</body>
</html>
