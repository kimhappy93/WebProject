<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="vo.MovieBean"%>
<%@page import="vo.ReplyBean"%>
<%@page import="svc.BoardCommentService"%>
<%@ page import="java.util.*"%>

<%

	MovieBean article = (MovieBean)request.getAttribute("article");


	String nowPage = (String)request.getAttribute("page");
	int board_num = (Integer)article.getMOVIE_NUM();
	String boardtype = request.getParameter("boardtype");
	String id = (String)session.getAttribute("idKey");
	
	String writer = article.getID();
	
	String tType = null;
	
	ReplyBean replybean = new ReplyBean();
	BoardCommentService boardCommentService = new BoardCommentService();	
	ArrayList<ReplyBean> reply = boardCommentService.selectReply(replybean, board_num, boardtype);

	if(boardtype.equalsIgnoreCase("MOVIE")) {
		tType = "M";
	} else if(boardtype.equalsIgnoreCase("FREE")) {
		tType = "F";
	}	
	
	String filename = article.getMOVIE_FILE();
%>
<!DOCTYPE html>
<html lang="">
    <head>
        <meta charset="utf-8">
        <title>MOVIE</title>
        <link rel="stylesheet" href="css/content.css?ver=2">
        <link rel="stylesheet" href="css/boardcommon.css?ver=2">
        <link rel="stylesheet" href="../css/common.css">
        <style>
        	#noticeMenu { margin-bottom : 30px; } 
        </style>
        <script>

	        function commentcheck(){
	        	var id = "<%=id%>";
	        	
	        	if(id == "null"){
		    		alert("댓글을 작성하려면 로그인 해 주세요.");
		    		return false;
		    	}
	        	
	        	if(document.commentform.<%=tType %>_CONTENT.value==""){
	        		document.commentform.<%=tType %>_CONTENT.focus();
	        		alert("내용을 입력해 주세요.");
	        		
	        		return false;
	        	}
	        	
	        	else return true;
			};
        </script>
        <script type="text/javascript">
			function commentboard(){
				commentform.submit();
			}
			
			/* function down(filename){
				document.downFrm.filename.value=filename;
				document.downFrm.subl
			} */
		</script>
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
<!--            wrap flex item 1-->

            <div class="container">

                <div class="contentWrap">
                    <!-- <div class="sub_tit_wrap">
	                      <h1 class="contit">Movie Talk</h1>
	                </div>
	                <div class="sub_tit_wrap2">
	                      <p>Feel free to talk!</p><br/>
	                      <p>자유롭게 이야기를 나누세요.</p>
	                </div> -->
	                
<!--                    실제 글쓰기 영역-->
                    <div class="contentArea">
                        <div class="titZone">
                        <%if("NOTICE".equals(boardtype)){ %>
                        	<span class="gtitle" style="background : #0D0C33"><%=boardtype %></span>
                        <%} else if("FREE".equals(boardtype)) {%>
                        	<span class="gtitle" style="background : #284866"><%=boardtype %></span>
                        <%} else if("MOVIE".equals(boardtype)) {%>
                        	<span class="gtitle" style="background : #2F40A1"><%=boardtype %></span>
                        <%} %>
                        
                        <h3 class="tit"><%=article.getMOVIE_SUBJECT()%></h3> <!-- before로 카테고리 만들어주기 -->
                        </div>
<!--                        글 작성자 정보-->
                        <div class="info">
                            <ul>
                                <li><span><%=article.getID()%></span></li>
                                <li><span><%=article.getMOVIE_READCOUNT()%></span></li>
                                <li><span><%=article.getMOVIE_DATE()%></span></li>
                            </ul>
                        </div>
						<%
                        	if(!(article.getMOVIE_FILE() == null)) {
                        %>
                        <div class="contentDown">
						<img src="imges/downfile.png"><span class="filedown"><a href="filedownload.jsp?filename=<%=filename%>"> <%=article.getMOVIE_FILE()%>
						</a></span>
						</div>
						<%
							}
						%>
						<!--                        글 본문 -->
                        <div class="content">
                            <%=article.getMOVIE_CONTENT() %>
                        </div>
                        
<!--                        작성자 권한 - 수정/삭제 -->
                        <div class="contentEdit">
                        	<%if("admin".equals(id)){ %>
	                        	<a onclick="return confirm('정말로 삭제하시겠습니까?')" href="boardDeletePro.bo?boardtype=<%=boardtype%>&board_num=<%=board_num%>&page=<%=nowPage%>"><span>삭제</span></a>
	                            <a href="boardModifyForm.bo?boardtype=<%=boardtype%>&board_num=<%=article.getMOVIE_NUM() %>"><span>수정</span></a>
                        	<%} else if (writer.equals(id)){%>
                            	<a onclick="return confirm('정말로 삭제하시겠습니까?')" href="boardDeletePro.bo?boardtype=<%=boardtype%>&board_num=<%=board_num%>&page=<%=nowPage%>"><span>삭제</span></a>
                            	<a href="boardModifyForm.bo?boardtype=<%=boardtype%>&board_num=<%=article.getMOVIE_NUM() %>"><span>수정</span></a>
                            <%} else {%>
                            	<span></span>
                            <% } %>
                        </div>
                        
                        <%if (boardtype.equals("NOTICE")){%>
                        <div class="commentTit"">
                        <span style="text-align:center; font-size:13px"> 공지사항엔 댓글을 작성할 수 없습니다. </span>
                        </div>
                        <div class="menu" id="noticeMenu">
                            <a href="boardList.bo?boardtype=BOARDALL"><span>목록</span></a>
                            <%if ("".equals(id)) {%>
                            	<span></span>
                            <%} else {%>
                            	<a href="boardWriteForm.bo"><span>글쓰기</span></a>
                            <%} %>
                        </div>
                        <%} else { %>
<!--                        댓글 타이틀-->
                        <div class="commentTit">
                            <span>댓글</span>
                        </div>
                        
<!--                        댓글 영역 시작-->

                        	
	                        <div class="commentArea">
	                        <%
	                        	if(reply == null){
	                        %>	
	                         <section> </section>
	                         
	                        <% }else{ %>
	                        
	                            <ul class="comment_list">
	                            	<% for(int i=0 ; i<reply.size() ; i++){ %>
	                                <li>
	                                    <div class="coment">
	                                        <div class="comentHead">
	                                            <span class="name"><%=reply.get(i).getId()%></span>
	                                            <%if(reply.get(i).getId().equals(id)){ %>
	                                            	<%-- <a onclick="return confirm('작성한 댓글을 삭제할까요?')" href="	commentDeletePro.bo?board_num=<%=board_num%>&page=<%=nowPage%>&m_reply_num=<%=reply.get(i).getM_reply_num()%>" class="btn_delete">삭제</a> --%>
	                                            	<a onclick="return confirm('작성한 댓글을 삭제할까요?')" href="	commentDeletePro.bo?boardtype=<%=boardtype%>&board_num=<%=board_num%>&page=<%=nowPage%>&<%=tType %>_reply_num=<%=reply.get(i).getM_reply_num()%>" class="btn_delete">삭제</a>
	                                            <%} else { %>
	                                            	<span></span>
	                                            <%} %>
	                                            <span class="time"><%=reply.get(i).getM_reply_date()%></span>
	                                        </div>
	                                        <div class="comentBody">
	                                            <span class="reply"><%=reply.get(i).getM_content()%></span>
	                                        </div>
	                                    </div>
	                                 </li>
	                                 <%} %>
	                            </ul>
	                        	<%
	                        	}
								%>
								<form action = "comment.bo" method = "post" name="commentform" id="commentform">	  
									<input type="hidden" name= "<%=tType %>_REPLY_REF_NUM" value="<%=board_num%>">
									<input type ="hidden" name = "ID" value = "<%=id%>"/>
									<input type ="hidden" name = "boardtype" value = "<%=boardtype%>"/>
		                            <div class="comment_write">
		                                <div class="c_div">
		                                    <div class="writediv">
		                                        <textarea class="commentedit" name="<%=tType %>_CONTENT" placeholder="내용을 작성해주세요." onfocus="this.placeholder=''" onblur="this.placeholder='내용을 작성해주세요.'"></textarea>
		                                    </div>
		                                    <a href="javascript:commentboard()" class="btn_write" onclick="return commentcheck()">등록</a>
		                                </div>
		                            </div>
		                        </form>
                       		</div>
                        
                        
<!--                        글목록 글쓰기 버튼-->
                        <div class="menu">
                            <a href="boardList.bo?boardtype=BOARDALL"><span>목록</span></a>
                            <%if ("".equals(id)) {%>
                            	<span></span>
                            <%} else {%>
                            	<a href="boardWriteForm.bo"><span>글쓰기</span></a>
                            <%} %>
                        </div>
                        <%} %>
                    </div>
                </div>
<!--                wrap flex item 3-->
                <!--                시간 -->

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