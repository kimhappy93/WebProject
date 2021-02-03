<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String id = (String)session.getAttribute("idKey");
%>	
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>회원가입</title>
<link rel="stylesheet" href="../css/common.css"> 

<script type="text/javascript" src="script.js"></script>
<script type="text/javascript">
    function idCheck(id) {
        frm = document.regFrm;
        if (id == "") {
            alert("아이디를 입력해 주세요.");
            frm.id.focus();
            return;
        }
        url = "idCheck.jsp?id=" + id;
        window.open(url, "IDCheck", "width=300,height=150");
    }

    function zipSearch() {
        url = "zipSearch.jsp?search=n";
        window
                .open(url, "ZipCodeSearch",
                        "width=500,height=300,scrollbars=yes");
    }
</script>
<style type="text/css">
#main {
	width: 1200px;
	margin: 0 auto;
}

#main h2{
	margin-top:50px;
}

table {
	width: 700px;
	padding-bottom:170px;
	border-top:1px solid #999;
}


th,td {
	padding: 12px;
	border-bottom:1px solid #999;
}

td{
	font-size:13px;
}

.memBtn{
	position:relative;
	left:10px;
	padding: 6px;
	border:1px solid #555;
	border-radius:15px;
	background-color:#555;
	font-size:12px;
	color:white;
	letter-spacing:1px;
}

textarea {
	width:350px;
	height:170px;
}

table tr:last-of-type > td > input{
	margin:5px;
	padding: 8px;
	border:1px solid #555;
	background-color:#333;
	font-size:14px;
	font-weight:bold;
	color:white;
}


</style>
</head>
<body onLoad="regFrm.id.focus()">
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
	<div align="center" id="main">
		<br /> <br />
		<form name="regFrm" method="post" action="memberProc.jsp">
			<h2>회원 가입</h2>
			
			<table cellspacing="0" cellpadding="2">
				<tr>
					<td class="mid">아이디</td>
					<td><input name="id" size="15" /><input type="button" value="ID중복확인" class="memBtn" onClick="idCheck(this.form.id.value)"></td>
					
				</tr>
				<tr>
					<td class="mid">패스워드</td>
					<td><input type="password" name="pwd" size="15" /></td>
					
				</tr>
				<tr>
					<td class="mid">패스워드 확인</td>
					<td><input type="password" name="repwd" size="15" /></td>
					
				</tr>
				<tr>
					<td class="mid">이름</td>
					<td><input name="name" size="15" /></td>
					
				</tr>
				<tr>
					<td class="mid">성별</td>
					<td>남<input type="radio" name="gender" value="1" checked /> 여<input
						type="radio" name="gender" value="2" />
					</td>
					
				</tr>
				<tr>
					<td class="mid">생년월일</td>
					<td><input name="birthday" size="6" />&nbsp;&nbsp; ex)830815</td>
					
				</tr>
				<tr>
					<td class="mid">Email</td>
					<td><input name="email" size="30" /></td>
					
				</tr>
				<tr>
					<td class="mid">주소</td>
					<td><input class="postcodify_postcode5" value=""
						name="zipcode" size="5" readonly /> <input
						id="postcodify_search_button" class="memBtn" type="button" value="우편번호찾기" /><br><br>
						<input class="postcodify_address" name="address" size="45"
						readonly /><br /> <input type="text" name="details"
						class="postcodify_details" value="" /></td>
					
				</tr>
				<tr>
					<td class="mid">영화 장르</td>
					<td>코미디<input type="checkbox" name="genre" value="코미디" /> 액션<input
						type="checkbox" name="genre" value="액션" /> SF<input
						type="checkbox" name="genre" value="SF" /> 스릴러<input
						type="checkbox" name="genre" value="스릴러" /> 판타지<input
						type="checkbox" name="genre" value="판타지" /> 로멘스<input
						type="checkbox" name="genre" value="로멘스" />
					</td>
					
				</tr>
				<tr>
					<td class="mid">소개</td>
					<td colspan="2"><textarea name="info"></textarea></td>
				</tr>
				<tr>
					<td colspan="3" align="center"><input type="button"
						value="회원가입" onclick="inputCheck()" /> &nbsp; &nbsp; <input
						type="reset" value="다시쓰기" /> &nbsp; &nbsp; <input type="button"
						value="로그인" onClick="javascript:location.href='login.jsp'" /></td>
				</tr>
			</table>
		</form>
		
	</div>
	 		<div id="footer">
                  <article>
              copyright@<br><br><hr><br>MOVIE주식회사: 인천광역시 서구 서곶로 284 새터빌딩 4층 연희직업전문학교<p/>대표전화: 032-555-1111&nbsp;/&nbsp;Email: movie@gmail.com 
                </article>      
              </div>
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"
		type="text/javascript"></script>
	<script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"
		type="text/javascript"></script>
	<script type="text/javascript">
        $(function () {
            $("#postcodify_search_button").postcodifyPopUp();
        });
    </script>
</body>
</html>
