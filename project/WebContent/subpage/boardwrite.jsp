<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id = (String)session.getAttribute("idKey");
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
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="css/common.js"></script> 
        <script src="css/slide.js"></script> 
        <script src="css/writecheck.js?v=<%=System.currentTimeMillis()%>"></script>
        <script src="css/writejs.js?v=<%=System.currentTimeMillis()%>"></script>
        <!-- 스마트에디터 -->
        <script type="text/javascript" src="../smarteditor/js/HuskyEZCreator.js" charset="utf-8"></script>
        <script type="text/javascript">
			var oEditors = [];
			 
			$(function(){
			   nhn.husky.EZCreator.createInIFrame({
			      oAppRef: oEditors,
			      elPlaceHolder: "ir1",
			      //SmartEditor2Skin.html 파일이 존재하는 경로
			      sSkinURI: "/smarteditorSample/SE2/SmartEditor2Skin.html",  
			      htParams : {
			          // 툴바 사용 여부 (true:사용/ false:사용하지 않음)
			          bUseToolbar : true,             
			          // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
			          bUseVerticalResizer : false,     
			          // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
			          bUseModeChanger : true,         
			          fOnBeforeUnload : function(){
			               
			          }
			      }, 
			      fOnAppLoad : function(){
			          //textarea 내용을 에디터상에 바로 뿌려주고자 할때 사용
			          oEditors.getById["ir1"].exec("PASTE_HTML", ["ㅎㅇ 시작하자마자 이문구 작성됨."]);
			      },
			      fCreator: "createSEditor2"
			      });
		</script>
         <!-- <script>
        	function check(){
        		
	        	if(document.postFrm.MOVIE_GENRE.value==""){
	        		alert("카테고리를 선택해 주세요.");
	        		
	        		return false;
	        	}
	
	        	if(document.postFrm.MOVIE_SUBJECT.value==""){
	        		alert("제목을 입력해 주세요.");
	        		document.postFrm.MOVIE_SUBJECT.focus();
	        		return false;
	        	}
	        	
	        	if(document.postFrm.MOVIE_CONTENT.value==""){
	        		alert("내용을 입력해 주세요.");
	        		document.postFrm.MOVIE_SUBJECT.focus();
	        		return false;
	        	}
	        	
	        	else return true;
        	} -->
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

<!--            wrap flex item 2-->
            <div class="container">

                <div class="writeWrap">
                    <div class="sub_tit_wrap">
	                    <h1 class="contit">Writing</h1>
	                </div>
	                <div class="sub_tit_wrap2">
	                        <p>What should I talk about?</p><br/>
	                        <p>무슨 이야기를 할까요?</p>
	                </div>
                    
<!--                    실제 글쓰기 영역-->
                    <div class="writeArea">
                        <form id="postFrm" name="postFrm" action="boardWritePro.bo" method="post" enctype="multipart/form-data" onsubmit="return check()">
                        	<input type = "hidden" name = "ID" value = "<%=id%>"/>
                            <fieldset>
                                <legend>글쓰기</legend>
                                <table>
                                    <caption>글쓰기</caption>
                                    <tbody>
                                        <tr>
                                            <th><label for="subject"><span>카테고리</span></label></th>
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
                                            <td><input type="text" name="MOVIE_SUBJECT" id="subject" placeholder="제목을 입력하세요." onfocus="this.placeholder=''" onblur="this.placeholder='제목을 입력하세요.'"></td>
                                        </tr>
                                        <tr>
                                            <th><span>내용</span></th>
                                            <td>
                                                <div class="textAreaWrap">
                                                    <textarea class="txtEdit" name="MOVIE_CONTENT" placeholder="내용을 입력하세요." onfocus="this.placeholder=''" onblur="this.placeholder='내용을 입력하세요.'"></textarea>
                                                </div>
                                            </td>
                                        </tr>
                                        
<!--                                        사진/파일 업로드 -->
                                        <tr>
                                            <th><span>사진</span></th>
                                            <td>
                                            	<!-- <input name="BOARD_FILE" type="file" id="BOARD_FILE" value="이미지를 업로드 하세요."> -->
                                                <div class="file_box">
                                                    
                                                    <input class="upload_name" value="이미지를 업로드 하세요." disabled="disabled">
                                                    <label for="input_file"><img src="imges/link.svg" alt="파일 찾기"></label> 
                                                    <input name="BOARD_FILE" type="file" id="input_file" class="file_hidden">
                                                </div>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                                
<!--                                게시하기, 글쓰기 취소-->
                                <div class="btnArea">
                                    <button type="submit" class="wbtn"><span>등록</span></button>
                                    <a onclick="return confirm('페이지를 벗어나면 작성된 내용이 사라집니다.')" href="boardList.bo?boardtype=BOARDALL" class="wbtn">취소</a>
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