<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.HashMap"%>
<%@page import="vo.NewsBean"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	//인코딩
	request.setCharacterEncoding("UTF-8");
	String id = (String)session.getAttribute("idKey");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>

<link rel="stylesheet" href="css/common.css" type="text/css">
<link rel="stylesheet" href="css/newsView.css" type="text/css">
<script src="js/link.js"></script>
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">
<link rel="icon" href="/favicon.ico" type="image/x-icon">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
    function list() {
        document.listFrm.submit();
    }

    function down(filename) {
        document.downFrm.filename.value = filename;
        document.downFrm.submit();
    }
</script>

</head>

<body>


<div id="wrapper">
    <header id="header">
              <h1 class="title" onclick="javascript:location.href='main/main.jsp'">MOVIEMOA</h1>
            <form method="post" action="main/search.jsp" class="login">
            
             <%
             String ids = request.getParameter("id");
             if (id == null) {%>
             <button type="button" class="login" onclick="javascript:location.href='login/login.jsp'">Login</button>	
              <button type="button" class="login" onclick="javascript:location.href='login/agree.jsp'">Sign in</button>
                 
               <%}else if(id.equals("admin")){%>  
                <button type="button" class="login" onclick="javascript:location.href='adminPage.jsp'">관리자 화면</button>
                 <button type="button" class="login" onclick="javascript:location.href='login/logout.jsp'">Logout</button>
              <%} else if(id != null) {%>
              <span id="hi"><%=id %> 님, 반갑습니다.</span>
                 <button type="button" class="login" onclick="javascript:location.href='login/logout.jsp'">Logout</button><%} %>
            
	              <input type="text" name="search"  id="search" class="login" placeholder="Search...">
	              <input type="submit" id="searchBtn">
              </form>
                  
             <nav id="nav">
                  <span class="btn" onclick="javascript:location.href='movieList.mo'">MovieInfo</span>
                  <span class="btn" onclick="javascript:location.href='subpage/boardList.bo?boardtype=BOARDALL'">MovieTalk</span>
                  <span class="btn" onclick="javascript:location.href='newsList.mo'">MovieNEWS</span>
                  <span class="btn" onclick="javascript:location.href='main/loc.jsp'">Theater</span> 
              </nav>
            </header>  
            
    <div id="main">
	    <h2 id="s_title">
		뉴스
		</h2>
		<table align="center" cellspacing="0" id="mainnews">
			
				<tr>	
					<td height="25" align="center" colspan="4"><h2>${newsBean.news_subject}</h2></td>
				</tr>
				<tr>
					<td colspan="4" align="right">${newsBean.news_name}</td>
				</tr>
				<tr>
					<td colspan="4" text-align="center">
						<br>
						<img src="newsfile/${newsBean.img_a}"/>
						<br>
						<br>${newsBean.content_a}
						<br><br>
					<c:choose>
						<c:when test="${newsBean.img_b == null}">
						</c:when>
						<c:when test="${newsBean.img_b != null}">
							<br>
							<img src="newsfile/${newsBean.img_b}">
							<br>
							<br>${newsBean.content_b}<br><br>
						</c:when>
						<c:when test="${newsBean.img_c == null}">
						</c:when>
						<c:when test="${newsBean.img_c != null}">
							<br>
							<img src="newsfile/${newsBean.img_c}">
							<br>
							<br>${newsBean.content_c}
						</c:when>
						<c:when test="${newsBean.img_d == null}">
						</c:when>
						<c:when test="${newsBean.img_d != null}">
							<br>
							<img src="newsfile/${newsBean.img_d}">
							<br>
							<br>${newsBean.content_d}
						</c:when>
						<c:when test="${newsBean.img_e == null}">
						</c:when>
						<c:when test="${newsBean.img_e != null}">
							<br>
							<img src="newsfile/${newsBean.img_e}">
							<br>
							<br>${newsBean.content_e}
						</c:when>
						<c:when test="${newsBean.img_f == null}">
						</c:when>
						<c:when test="${newsBean.img_f != null}">
							<br>
							<img src="newsfile/${newsBean.img_f}">
							<br>
							<br>${newsBean.content_f}
						</c:when>
					</c:choose>
					</td>
				</tr>
				<tr>
					<td colspan="4" align="right">${newsBean.news_date}</td>
				</tr>
		
		</table>
		<div id="list">
			<a href="javascript:location.href='newsList.mo'"><input id="listbtn" type="button"value="List"></a>
		</div>
		
	</div>        
            
            
</div>

		<div id="footer">
            <article>
       			 copyright@<br><br><hr><br>MOVIE주식회사: 인천광역시 서구 서곶로 284 새터빌딩 4층 연희직업전문학교<p/>대표전화: 032-555-1111&nbsp;/&nbsp;Email: movie@gmail.com 
         	</article>      
        </div>
</body>
</html>