<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	 
	  String id = (String)session.getAttribute("idKey");
%>
<html>
<head>
<title>로그인</title>
<link href="style.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="../css/common.css"> 
<script src="js/slide.js"></script>
<script type="text/javascript">
	function loginCheck() {
		if (document.loginFrm.id.value == "") {
			alert("아이디를 입력해 주세요.");
			document.loginFrm.id.focus();
			return;
		}
		if (document.loginFrm.pwd.value == "") {
			alert("비밀번호를 입력해 주세요.");
			document.loginFrm.pwd.focus();
			return;
		}
		document.loginFrm.submit();
	}
</script>
<style>
#login_wrap{
	padding-bottom:300px;
}

#login_wrap h1 {
  margin-top: 80px;
  text-align:center;
}

#login {
  margin: 0 auto;
  width: 400px;
  height: 250px;
  padding:45px 45px 5px;
  text-align: center;
  border-top:1px solid #333;
  border-bottom:1px solid #333;
}


#login label {
  display: inline;
  font-size: 15px;
  font-weight: bold;
  margin-right:15px;
}

#ID, #PW {
  width:250px;
  height: 30px;
}

.find{
	position:relative;
	left:110px;
	font-size:13px;
	cursor:pointer;
}

#login span:nth-child(2):before{
	content:"|";
	margin-right:5px;
}

.inbtn{
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
    <div id="main">
   
      <form name="loginFrm" method="post" action="loginProc.jsp">
      <div id="login_wrap">
        <h1>로그인</h1>
        <div id = "login">
        <label for="ID" style="margin-right:27px">아이디</label>
        <input type="text" name="id" id="ID" placeholder="아이디를 입력하세요.">
        <p />
        <label for="PW">비밀번호</label>
        <input type="password" name="pwd" id="PW" placeholder="비밀번호를 입력하세요.">
        <p />
        <span class="find" onclick="javascript:location.href='searchid.jsp'">ID 찾기</span>
       <span  class="find" onclick="javascript:location.href='PwFindOk.jsp'">PWD 찾기</span>
       <p/>
       <input type="button" value="로그인" class="inbtn" onclick="loginCheck()">&nbsp;
	   <input type="button" value="회원가입" class="inbtn" onClick="javascript:location.href='agree.jsp'">
       
		</div>
      </div>
      </form>
      
      <script src="../src/login.js"></script>
      <script src="../js/link.js"></script>
    </div>
    <div id="footer">
       <article>
     copyright@<br><br><hr><br>MOVIE주식회사: 인천광역시 서구 서곶로 284 새터빌딩 4층 연희직업전문학교<p/>대표전화: 032-555-1111&nbsp;/&nbsp;Email: movie@gmail.com 
       </article>      
    </div>
  </body>
</html>