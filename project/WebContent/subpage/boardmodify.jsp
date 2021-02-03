<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="vo.MovieBean"%>
<%
	String id = (String)session.getAttribute("idKey");
	MovieBean article = (MovieBean)request.getAttribute("article");
	String boardtype = request.getParameter("boardtype");
%>
<!doctype html>
<html>
    <head>
        <meta charset="utf-8">  
        <title>
            MOVIE
        </title>
        <link rel="stylesheet" href="css/write.css?ver=2">
        <link rel="stylesheet" href="css/boardcommon.css?ver=2">
        <link rel="stylesheet" href="../css/common.css">
        <script src="css/common.js"></script> 
        <script src="css/slide.js"></script> 
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
       	 
<!--            wrap flex item 2-->
            <div class="container">
<!--            광고1-->
                
                <div class="writeWrap">
                	<%if (boardtype.equals("NOTICE")){%>
                	<div class="sub_tit_wrap">
	                    <h1 class="contit" style="width : 550px; margin-bottom:10px">관리자용 수정 페이지</h1><br/>
	                </div>
	                <div class="sub_tit_wrap2">
	                </div>
                	<%} else { %>
                    <div class="sub_tit_wrap">
	                    <h1 class="contit">ReWriting</h1>
	                </div>
	                <div class="sub_tit_wrap2">
	                        <p>What should I talk about?</p><br/>
	                        <p>무슨 이야기를 할까요?</p>
	                </div>
	                <%} %>
                    
<!--                    실제 글쓰기 영역-->
                    <div class="writeArea">
                        <form id="postFrm" action="boardModifyPro.bo" method="post" name = "modifyform">
                        	<input type = "hidden" name = "ID" value = "<%=article.getID()%>"/>
                        	<input type = "hidden" name = "board_num" value = "<%=article.getMOVIE_NUM()%>"/>
                            <fieldset>
                                <legend>글쓰기</legend>
                                <table>
                                    <caption>글쓰기</caption>
                                    <tbody>
                                        <tr>
                                            <th><label for="subject1"><span>카테고리</span></label></th>
                                            <td>
                                                <%if ("admin".equals(id)){%>
                                                <select name="MOVIE_GENRE" class="category">
                                                    <option value="">선택</option>
                                                    <option value="NOTICE">공지사항</option>
                                                    <option value="MOVIE">영화</option>
                                                    <option value="FREE">잡담</option>
                                                </select>                                            	
                                            	<%} else { %>
                                                <select name="MOVIE_GENRE" class="category">
                                                    <option value="">선택</option>
                                                    <option value="MOVIE">영화</option>
                                                    <option value="FREE">잡담</option>
                                                </select>
                                                <%} %>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th><label for="subject"><span>제목</span></label></th>
                                            <td><input name="MOVIE_SUBJECT" type="text" id="subject" value = "<%=article.getMOVIE_SUBJECT()%>"/></td>
                                        </tr>
                                        <tr>
                                            <th><span>내용</span></th>
                                            <td>
                                                <div class="textAreaWrap">
                                                    <textarea class="txtEdit" name="MOVIE_CONTENT" placeholder="내용을 입력하세요." onfocus="this.placeholder=''"  onblur="this.placeholder='내용을 입력하세요.'"><%=article.getMOVIE_CONTENT()%> </textarea>
                                              </div>
                                            </td>
                                        </tr>
                                        
<!--                                        사진/파일 업로드 -->
<!--                                         <tr>
                                            <th><span>사진</span></th>
                                            <td>
                                                <div class="file_box">
                                                    <input class="upload_name" value="이미지" disabled="disabled">
                                                    <input name="BOARD_FILE" type="file"id="BOARD_FILE"/>
                                                    <label for="input_file"><img src="imges/link.svg" alt="파일 찾기"></label> 
                                                    <input type="file" id="input_file" class="file_hidden"> 
                                                </div>
                                            </td>
                                        </tr> -->
                                    </tbody>
                                </table>
                                
<!--                                게시하기, 글쓰기 취소-->
                                <div class="btnArea">
                                    <button type="submit" class="wbtn"><span>등록</span></button>
                                    <a onclick="return confirm('페이지를 벗어나면 작성된 내용이 사라집니다.')" href="boardList.bo" class="wbtn">취소</a>
                                </div>
                            </fieldset>
                        </form>
                    </div>
                </div>
                
                
        
<!--                wrap flex item 3-->
                <!--                시간 -->
        
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