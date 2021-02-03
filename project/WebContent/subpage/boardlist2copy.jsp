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
        <link rel="stylesheet" href="css/boardcommon.css">
        <link rel="stylesheet" href="../css/common.css">
        <link rel="stylesheet" href="css/list.css?ver=2" />
        <script src="css/common.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <!-- <script src="css/board.js"></script> --> 
    </head>
    <body>
        
        <header id="header">
              <a href="../main/main.jsp"><h1 class="title">MOVIE</h1></a>
              <%if (id != null) {%>
              	<%=id %> 님, 반갑습니다.
              	<a href="../logout.jsp">로그아웃</a>
              <%} else {%>
              <a href="../login/login.jsp"><button type="button" class="login">로그인</button></a>
              <%} %>
              
              <a href="member.jsp"><button type="button" class="login">회원가입</button></a>
              <input type="search" id="search" class="login" placeholder="검색어를 입력" onkeyup="">
         </header>
         <nav id="nav">
                  <a href="movieList.mo"><span class="btn">영화</span></a>
                  <a href="subpage/boardList.bo"><span class="btn">리뷰</span></a>
                  <span class="btn" onclick="board_3()">읽을거리</span>
                  <span class="btn" onclick="loc()">극장</span> 
         </nav>
        
        <div class="wrap">
<!--            wrap flex item 1-->
            
<!--          미사용 헤더 -->
<!--
            <div class="subHeader_area">
                <ul class="subHeader">
                    <li>
                        <a href="#" class="subHeaderNum">
                            <span> 영화 토크 </span>
                        </a>
                    </li>
                    <li>
                        <a href="#" class="subHeaderNum">
                            <span> 현영씨 메뉴 </span>
                        </a>
                    </li>
                    <li>
                        <a href="#" class="subHeaderNum">
                            <span> 승호씨 메뉴 </span>
                        </a>
                    </li>
                    <li>
                        <a href="#" class="subHeaderNum">
                            <span> ?? </span>
                        </a>
                    </li>
                </ul>
            </div>
-->
        
<!--            wrap flex item 2-->
            <div class="container">
<!--            광고1-->
                <div class="ad_1"></div>
                
        <!--            게시판 영역 -->
                <div class="boardListWrap">
                        <h1 class="contit"> <span>T</span>ALK PLACE</h1>
                        <h2 class="contsubtit"> 자유롭게 이야기를 나누는 공간입니다. </h2>

        <!--                게시판-->
                        <div class="category">
                            <ul class="categoryTab">
                                <li><a href="boardList.bo?boardtype=BOARDALL" class="categoryOn" >전체(<%=allCount%>)</a></li>
                                <li><a href="#">공지사항</a></li>
                                <li><a href="#">HIT</a></li>
                                <li><a href="boardList.bo?boardtype=BOARDMOVIE">영화</a></li>
                                <li><a href="boardList.bo?boardtype=BOARDFREE">잡담</a></li>
                            </ul>
                        </div>
                        <table class="boardList">
                        <%
						if(articleList != null && listCount > 0){
						%>
                            <caption> 게시판 제목 </caption>
                            <thead>
                                <tr>
                                    <th>번호</th>
                                    <th>장르</th>
                                    <th>제목</th>
                                    <th>글쓴이</th>
                                    <th>작성일</th>
                                    <th>조회</th>
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
                                        <a href="boardDetail.bo?board_num=<%=articleList.get(i).getMOVIE_NUM()%>&page=<%=nowPage%>" class="boardTitle">
                                        <%=articleList.get(i).getMOVIE_SUBJECT()%>
                                        </a>
                                    </td>
                                    <td><span class="writer"><%=articleList.get(i).getID() %></span></td>
                                    <td><span class="date"><%=articleList.get(i).getMOVIE_DATE()%></span></td>
                                    <td><span class="hit"><%=articleList.get(i).getMOVIE_READCOUNT()%></span></td>
                                </tr>
                            </tbody>
                            <%} %>
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
								<li class="pageOn"><a href="boardList.bo?boardtype=<%=boardtype%>&page=<%=a%>" class="pageNum"><%=a%></a></li>
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
                        
                        <%
						    }
							else
							{
							%>
							<section id="emptyArea">등록된 글이 없습니다.</section>
							<%
							}
						%>
		
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
                
<!--                wrap flex item 3-->
                <!--                시간 -->
                <div class="time_area">
                    <p>현재 시각은…</p>
                    <div class="time_img"></div>
                    <div class="timeNotice">
                        <span class="realtime">「」</span> <br>
                        <span class="genre">공포 </span>
                        <span class="genre_ment">영화 보기 좋은 시간!</span>
                    </div>
                </div>
            </div>
        </div>
        
        
        <div id="footer">
            copyright@
        </div>
        <script src="css/slide.js"></script> 
    </body>
</html>