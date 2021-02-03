<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.HashMap"%>
<%@page import="vo.NewsBean"%>
<%@page import="vo.PageInfo"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	//인코딩
	request.setCharacterEncoding("UTF-8");
	String id = (String)session.getAttribute("idKey");
	
	//페이징처리
	ArrayList<NewsBean> newsBean =(ArrayList<NewsBean>)request.getAttribute("newsBean");
	PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
	int listCount=pageInfo.getListCount();
	int allCount=pageInfo.getAllCount(); //전체 게시글 집계 변수
	int nowPage=pageInfo.getPage();
	int maxPage=pageInfo.getMaxPage();
	int startPage=pageInfo.getStartPage();
	int endPage=pageInfo.getEndPage();
%>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="css/newsList.css">
<link rel="stylesheet" href="css/common.css" type="text/css">
<script src="js/link.js"></script>
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">
<link rel="icon" href="/favicon.ico" type="image/x-icon">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<style type="text/css">
	
	#productImage{
		width: 300px;
		height: 200px;
		border: none;
	}

	.pagination li .prev { background-image: url(images/previcon.png); background-size: 20px; }
	.pagination li .next { background-image: url(images/nexticon.png); background-size: 20px; }
}
	
</style>
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
    

        
        <!-- 작업영역 -->


<section id = "listForm">
<%
if(listCount > 0){
%>
<c:if test="${newsBean != null}">
	<h2 id="s_title">
	뉴스
	</h2>
<table>
	<tr>
		<c:forEach var = "newsBean" items="${newsBean}" varStatus="status">
			<td>
				<a href="newsView.mo?news_num=${newsBean.news_num}">
				<img src="newsfile/${newsBean.img_a}" id="productImage"/>
				</a>
				<p id="news_subject">${newsBean.news_subject}</p>
				<br><br>
			</td>
			<c:if test="${((status.index+1) mod 3)==0 }">
				</tr>
				<tr>
			</c:if>
		</c:forEach>
	</tr>
</table>
</c:if>

<div class="pageList">
	<ul class="pagination">

		<!-- 이전페이지 -->
		<%if(nowPage<=1){ %>
		  
		    <li><span class="prev"><!--이전--></span></li>
		  <%}else{ %>
		  	<li>
		  		<span class="prevIcon"><a href="newsList.mo?page=<%=nowPage-1 %>" class="prev"><!--이전--></a>
		  		</span>
		  	</li>
		  <%} %>	
		  
		<%for(int a=startPage ; a<=endPage ; a++){
		if(a==nowPage){%>
		<li class="pageOn"><a href="newsList.mo?page=<%=a%>" class="pageNum"><%=a%></a></li>
		<%}else{ %>
		      <li class="pageOn"><a href="newsList.mo?page=<%=a%>" class="pageNum"><%=a%></a></li>
		      <%} %>
		<%} %>
		
		
		<!-- 다음페이지 -->	                                
		   <%if(nowPage>=maxPage){ %>
		   	<li><span class="next"></span></li>
		   <%}else{ %>
		      <li>
		      	<span class="nextIcon"><a href="newsList.mo?page=<%=nowPage+1 %>" class="next"><!--다음--></a></span>
		      </li>
		    <%}%>
		   
	</ul>
</div>

</section>	
<%} %>

<!-- 작업영역 끝 -->
    

  </div>
   <div id="footer">
                  <article>
              copyright@<br><br><hr><br>MOVIE주식회사: 인천광역시 서구 서곶로 284 새터빌딩 4층 연희직업전문학교<p/>대표전화: 032-555-1111&nbsp;/&nbsp;Email: movie@gmail.com 
                </article>      
              </div>


</body>
</html>