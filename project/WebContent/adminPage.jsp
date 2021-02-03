<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.sql.*" %>
<%@page import = "project.MainBoardBean" %>  
<%
	String id = (String)session.getAttribute("idKey");

	int nowPage = 1;
	if(request.getParameter("nowPage")!=null){
	nowPage = Integer.parseInt(request.getParameter("nowPage"));
}
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자페이지</title>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="jquery.js"></script>
 <link rel="stylesheet" href="css/common.css"> 
 <link rel="stylesheet" href="css/adminpage.css"> 

<script>
function fetchPage(name){
    fetch(name).then(function(response){
                    response.text().then(function(text){
                    document.querySelector('#manage').innerHTML = text;
                })
            })
}
</script>
</head>
<body>
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
             
            <div id="sidebar">
   			<div class="sub_tab">
   				<ul>
   					<li onclick="javascript:location.href='adminPage.jsp'"><h4>관리자 메인 페이지</h4></li>
   					<li>
   						<span onclick="fetchPage('js/noticeWrite')">공지사항</span>
   					</li>
   					<li>
   						<span onclick="fetchPage('js/movieRegistForm')">최신영화 등록</span>
   					</li>
   					<li>
   						<span onclick="fetchPage('js/newsRegistForm')">최신뉴스 등록</span>
   					</li>
   				</ul>
   			</div>
   		</div>
   		
            <div id="main">
            
           
            <div id = "manage">
            
           		 <%
                  	String search = request.getParameter("search");
                  
                  	Connection conn = null;
                  	Statement stmt = null;
                  	
                  	try{
                  		Class.forName("com.mysql.jdbc.Driver");
                  		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb?useUnicode=true&characterEncoding=utf8","root","1234");
                  		
                  		if(conn == null)
                  			throw new Exception("데이터베이스 연결 실패");
                  		
                  		stmt = conn.createStatement();
                  		
                  		String sql = "select * from boardnotice order by notice_date desc";
                  						
                  		System.out.println(sql);
                  		
                  		ResultSet rs = stmt.executeQuery(sql);
                  		
                  		if(!rs.next()){
                  			out.println("해당하는 정보가 없습니다.");
                  		}else{
                  			rs.previous();
                  		}
                  		
                  		while(rs.next()){
                  			String subject = rs.getString("NOTICE_SUBJECT");
                  			String mov_id = rs.getString("ID");
                  			String date = rs.getString("NOTICE_DATE");
                  			int count = rs.getInt("NOTICE_READCOUNT");
                  			int board_num = rs.getInt("NOTICE_NUM");
                  			String genre = rs.getString("NOTICE_GENRE");
                  			
                  		%>	
                  			
                  			 <div id="admin_content">
                             <P class="admin_title"><a href="subpage/boardDetail.bo?boardtype=NOTICE&board_num=<%=board_num%>&page=<%=nowPage%>"><%=subject%></a></P>
                             <p class="admin_info"><%=genre%>&nbsp;&nbsp;|&nbsp;&nbsp;<%=mov_id %>&nbsp;&nbsp;|&nbsp;&nbsp;<%=date %>&nbsp;&nbsp;|&nbsp;&nbsp;<%=count %>&nbsp;&nbsp;</p>
                        
                     		 
                              </div> 
                          <% 
                  		}
                  	}catch(Exception e){
                  		e.printStackTrace();
                  	}
                  	%>    
            
            </div>
           
            </div>  
               <div id="footer">
                  <article>
              copyright@<br><br><hr><br>MOVIE주식회사: 인천광역시 서구 서곶로 284 새터빌딩 4층 연희직업전문학교<p/>대표전화: 032-555-1111&nbsp;/&nbsp;Email: movie@gmail.com 
                </article>      
              </div>
</body>
</html>