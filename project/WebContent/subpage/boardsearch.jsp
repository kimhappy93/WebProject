<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Vector" %>
<%@ page import="vo.PageInfo"%>
<%@ page import="vo.MovieBean"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%
	ArrayList<MovieBean> articleList = (ArrayList<MovieBean>)request.getAttribute("articleList");
    PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
	int listCount=pageInfo.getListCount();
	int allCount=pageInfo.getAllCount(); //전체 게시글 집계 변수
	int nowPage=pageInfo.getPage();
	int maxPage=pageInfo.getMaxPage();
	int startPage=pageInfo.getStartPage();
	int endPage=pageInfo.getEndPage();
	
	String boardtype = request.getParameter("boardtype");
	String id = (String)session.getAttribute("idKey");
%>
<!doctype html>
<html>
    <head>
        <meta charset="utf-8">  
        <title>
            MOVIE
        </title>
        <link rel="stylesheet" href="css/boardcommon.css?ver=2">
        <link rel="stylesheet" href="../css/common.css">
        <link rel="stylesheet" href="css/list.css?ver=2" />
        <script src="css/common.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <!-- <script src="css/board.js"></script> --> 
    </head>
    <body>
        <div id="wrapper">
	        <header id="header">
              <h1 class="title" onclick="javascript:location.href='../main/main.jsp'">MOVIEMOA</h1>
            <form method="post" action="../main/search.jsp" class="login">
            
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
                  <span class="btn" onclick="javascript:location.href='boardList.bo?boardtype=BOARDALL'">MovieTalk</span>
                  <span class="btn" onclick="javascript:location.href='../newsList.mo'">MovieNEWS</span>
                  <span class="btn" onclick="javascript:location.href='../main/loc.jsp'">Theater</span> 
              </nav>
            </header>
	        
	        <div class="wrap">

	            <div class="container">	                
	        <!--            게시판 영역 -->
	                <div class="boardListWrap">
	                	<div class="sub_tit_wrap">
	                        <h1 class="contit">Movie Talk</h1>
	                    </div>
	                    <div class="sub_tit_wrap2">
	                        <p>Feel free to talk!</p><br/>
	                        <p>자유롭게 이야기를 나누세요.</p>
	                    </div>
	
	        <!--                게시판-->
	                        <div class="category">
	                            <ul class="categoryTab">
	                                <li><a href="boardList.bo?boardtype=BOARDALL" class="categoryOn" >ALL(<%=allCount%>)</a></li>
	                                <li><a href="#">NOTICE</a></li>
	                                <li><a href="#">HIT</a></li>
	                                <li><a href="boardList.bo?boardtype=BOARDMOVIE">MOVIE</a></li>
	                                <li><a href="boardList.bo?boardtype=BOARDFREE">FREE</a></li>
	                            </ul>
	                        </div>
	                        <table class="boardList">
	                        <%
							if(articleList != null && listCount > 0){
							%>
	                            <thead>
	                                <tr>
	                                    <th>NO.</th>
	                                    <th>GENRE</th>
	                                    <th>TITLE</th>
	                                    <th>WRITTER</th>
	                                    <th>DATE</th>
	                                    <th>VIEW</th>
	                                </tr>
	                            </thead>
	                        <%
							for(int i=0;i<articleList.size();i++){
									
							%>
	                            <tbody class="boardContent">
	
	                                <tr>
	                                    <td><span class="index"><%=articleList.get(i).getMOVIE_NUM()%></span></td>
	                                    <td>
		                                    <a href="#" class="genretitle"><span class="genre_category"><%=articleList.get(i).getMOVIE_GENRE()%></span></a>
	                                    </td>
	                                    <td>
	                                        <a href="boardDetail.bo?boardtype=<%=articleList.get(i).getMOVIE_GENRE()%>&board_num=<%=articleList.get(i).getMOVIE_NUM()%>&page=<%=nowPage%>" class="boardTitle">
	                                        <%=articleList.get(i).getMOVIE_SUBJECT()%>
	                                        </a>
	                                    </td>
	                                    <td><span class="writer"><%=articleList.get(i).getID() %></span></td>
	                                    <td><span class="date"><%=articleList.get(i).getMOVIE_DATE()%></span></td>
	                                    <td><span class="hit"><%=articleList.get(i).getMOVIE_READCOUNT()%></span></td>
	                                </tr>
	                            </tbody>
	                            <%} %>
	                            
	                            <%
							    }
								else if(listCount <= 0)
								{
								%>
								 <thead>
	                                <tr>
	                                    <th>NO.</th>
	                                    <th>GENRE</th>
	                                    <th>TITLE</th>
	                                    <th>WRITTER</th>
	                                    <th>DATE</th>
	                                    <th>VIEW</th>
	                                </tr>
	                            </thead>
	                            <tbody class="boardContent">
		
	                                <tr>
	                                    <td colspan="6" class="nonBoard"><span>등록된 글이 없습니다.</span></td>
	                                </tr>
	                            </tbody>
								<%
								}
							%>
	                        </table>
	
	                                <!--         페이지 리스트 영역 -->
	
	                        <div class="pageList">
	                        	<ul class="pagination">
	                        	
	                        	<!-- 이전페이지 -->
	                        	<%if(nowPage<=1){ %>
	                            
	                                <li><span class="prev"><!--이전--></span></li>
	                            <%}else{ %>
	                            	<li>
	                            		<span class="prevIcon"><a href="boardList.bo?boardtype=<%=boardtype%>&page=<%=nowPage-1 %>" class="prev"><!--이전--></a>
	                            		</span>
	                            	</li>
	                            <%} %>	
	
	
	                            <!-- 숫자페이지 -->
	                            <%-- <%for(int a=startPage ; a<=endPage ; a++){
									if(a==nowPage){%>
									<li class="pageOn"><a href="boardList.bo?page=<%=a %>" class="pageNum"><%=a %></a></li>
								<%}else{ %>
	                                <li class="pageOn"><a href="boardList.bo?page=<%=a %>" class="pageNum"><%=a %></a></li>
	                                <%} %>
								<%} %> --%>
								<%for(int a=startPage ; a<=endPage ; a++){
									if(a==nowPage){%>
									<li class="pageOn"><a href="boardList.bo?boardtype=<%=boardtype%>&page=<%=a%>" class="pageNum"><font color="red"><%=a%></font></a></li>
								<%}else{ %>
	                                <li class="pageOn"><a href="boardList.bo?boardtype=<%=boardtype%>&page=<%=a%>" class="pageNum"><%=a%></a></li>
	                                <%} %>
								<%} %>
	
								
								<!-- 다음페이지 -->	                                
	                             <%if(nowPage>=maxPage){ %>
	                             	<li><span class="next"></span></li>
	                             <%}else{ %>
	                                <li>
	                                	<span class="nextIcon"><a href="boardList.bo?boardtype=<%=boardtype%>&page=<%=nowPage+1 %>" class="next"><!--다음--></a></span>
	                                </li>
	                              <%} %>
	                            </ul>
	                        </div>

			
	                        <!--                글삭제, 글쓰기, 내가 쓴 글 -->
	                        <%if (id != null) {%>
	                        <div class="btn_area">
	                            <div class="btn_side_area">
	                                <a href="#"><span>작성글 목록</span></a>
	                                <a href="boardWriteForm.bo"><span>글쓰기</span></a>
	                            </div>
	                        </div>
	                        <%} else { %>
	                        	<div class="btn_area">
	                        		<div class="btn_side_area2">
	                        			<a href="#"><span>작성글 목록</span></a>
	                                <a href="boardWriteForm.bo"><span>글쓰기</span></a>
	                        		</div>
	                        	</div>
	                        <%} %>
			
	
	        <!--                검색-->
	        				<div class="search_area">
		                        <form>
		                            <fieldset>
		                                <select name="sort" class="serch_category">
		                                    <option value="">분류</option>
		                                    <option value="">제목</option>
		                                    <option value="">내용</option>
		                                </select>
		                                <input type="search" class="inputSearch" placeholder="검색어를 입력하세요." />
		                                <button type="submit" class="btn_search">
		                                    <span class="fa fa-search"></span>
		                                </button>
		                            </fieldset>
		                        </form>
	                        </div>
	                </div>
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