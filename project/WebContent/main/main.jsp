<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import = "project.MainBoardBean" %>
<%@page import = "project.BestBoardBean" %>
<%@page import = "project.MovieBean" %>
<%@page import = "news.BoardBean" %>
<%@page import="java.util.Vector" %>
<jsp:useBean id="bMgr" class="project.BoardMgr"/> 

<%

	int numPerPage = 8;		//페이지 당 레코드 수
	int listSize = 0;		//현재 읽어온 게시물의 수
	int nowPage = 1;		//현재 페이지


	int mlistSize = 0;
	int blistSize = 0;
	int nlistSize = 0;
	
	String keyWord = "", keyField = "";
	Vector<MainBoardBean> vlist = null;
	
	
	String BkeyWord = "", BkeyField = "";
	Vector<BestBoardBean> blist = null;
	if(request.getParameter("BkeyWord")!= null){
		BkeyWord = request.getParameter("BkeyWord");
		BkeyField = request.getParameter("BkeyField");
	}
	
	String mWord = "", mField = "";
	Vector<MovieBean> mlist = null;
	if(request.getParameter("mWord")!= null){
		mWord = request.getParameter("mWord");
		mField = request.getParameter("mField");
	}
	
	String nWord = "", nField = "";
	Vector<BoardBean> nlist = null;
	if(request.getParameter("nWord")!= null){
		nWord = request.getParameter("nWord");
		nField = request.getParameter("nField");
	}

	if(request.getParameter("reload")!=null){
		if(request.getParameter("reload").equals("true")){
			keyWord = "";
			keyField = "";
			}
		}
	
	if(request.getParameter("reload")!=null){
		if(request.getParameter("reload").equals("true")){
			BkeyWord = "";
			BkeyField = "";
			}
		}
	
	
	if(request.getParameter("nowPage")!=null){
		nowPage = Integer.parseInt(request.getParameter("nowPage"));
	}
	
	
	 String id = (String)session.getAttribute("idKey");
	

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
    <script src="jquery.bxslider/jquery.bxslider.js"></script>
    <script src="../js/jquery.bxslider.min.js"></script>
    <script src="../js/slide.js"></script>
    <link rel="stylesheet" href="../css/jquery.bxslider.min.css">
    <link rel="stylesheet" href="../css/shape.css">
     <link rel="stylesheet" href="../css/common.css"> 
<script>

  $(function(){
      $('.post_wrap').bxSlider({
          auto: true,
          pause:4000,
          speed:600,  
          pager:false,
          touchEnabled : (navigator.maxTouchPoints > 0)
      });
  });

</script>

</head>
<body>
 <div id="wrapper">
     <header id="header">
              <h1 class="title" onclick="javascript:location.href='main.jsp'">MOVIEMOA</h1>
            <form method="post" action="search.jsp" class="login">
            
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
                  <span class="btn" onclick="javascript:location.href='../subpage/boardList.bo?boardtype=BOARDALL'">MovieTalk</span>
                  <span class="btn" onclick="javascript:location.href='../newsList.mo'">MovieNEWS</span>
                  <span class="btn" onclick="javascript:location.href='loc.jsp'">Theater</span> 
              </nav>
            </header>  
             
             <div id ="slider_wrap">
                <div id = "side" class="slide">
                    <ul>
                    <li><img src="images/slide1.jfif" width="1200px" height="500px"></li>  
                    <li><img src="images/slide2.jfif" width="1200px" height="500px"></li> 
                    <li><img src="images/slide3.jfif" width="1200px" height="500px"></li> 
                    <li><img src="images/slide4.jfif" width="1200px" height="500px"></li> 
                    </ul>    
                </div>
            </div>
                
              <div id="main">
               			
                  <section id="section">
                  
                      <h4 class = "w_title"><article>영화</article></h4>
                      <a href="http://localhost:9988/project/subpage/boardList.bo?boardtype=BOARDMOVIE"><h6 class = "w_plus">+더보기</h6></a>
                     <div class="w_content">
                     	<%
								vlist = bMgr.getBoardList();
								listSize = vlist.size(); //브라우저 화면에 나타날 게시물 개수
									if(vlist.isEmpty()){
										out.println("등록된 게시물이 없습니다.");
									}
							%>	
                      		     
                          <ul class="_ul">
                              <li class="page1">
                                  <ul class="list_unit">
                                  <%
									for(int i = 0; i<numPerPage; i++){
										if(i == listSize)  
											break;
										MainBoardBean bean = vlist.get(i);
										int board_num = bean.getMOVIE_NUM();
										String subject = bean.getMOVIE_SUBJECT();
										String genre = bean.getMOVIE_GENRE();
										%>
                                    <li class="rank">
                                    	
                                    <span class="rank_wrap"><span class="rank_list"><%=i+1 %></span></span>
                                       <span class="rank_main"><a class="main_title" href="../subpage/boardDetail.bo?boardtype=<%=genre%>&board_num=<%=board_num%>&page=<%=nowPage%>"><%=subject %></a></span>
                                       </li>
                                     <% } %> 
                                  </ul>    
                              </li>
                          </ul>
                    </div>
                  </section>
                  
                  <section id="section2">
                    <h4 class = "w_title"><article>잡담</article></h4>
                      <a href="http://localhost:9988/project/subpage/boardList.bo?boardtype=BOARDFREE"><h6 class = "w_plus">+더보기</h6></a>
                      <div class="w_content">
                      	<%
								blist = bMgr.getBestList(BkeyField, BkeyWord);
								blistSize = blist.size(); //브라우저 화면에 나타날 게시물 개수
									if(blist.isEmpty()){
										out.println("등록된 게시물이 없습니다.");
									}
							%>	
                          <ul class="_ul">
                              <li class="page1">
                                  <ul class="list_unit">
                                  <%
									for(int i = 0; i< numPerPage; i++){
										if(i == blistSize)  
											break;
										BestBoardBean bean = blist.get(i);
										int board_num = bean.getFREE_NUM();
										String subject = bean.getFREE_SUBJECT();
										String genre = bean.getFREE_GENRE();
										%>
                                    <li class="rank">
                                      <span class="rank_wrap"><span class="rank_list"><%=i+1 %></span></span>
                                      <span class="rank_main"><a class="main_title" href="../subpage/boardDetail.bo?boardtype=<%=genre%>&board_num=<%=board_num%>&page=<%=nowPage%>"><%=subject %></a></span>
                                    </li>
                                   <% } %>
                                  </ul>    
                              </li>
                          </ul>
                    </div>
                    
                  </section>
                  
                  <section id="section3">
                      <h4 class = "m_banner"><article>현재상영작</article></h4>
                    <div class="post_wrap" >
                      <div class="post_view">
                        <%
                          mlist = bMgr.getMovieList(mField, mWord);
							mlistSize = mlist.size(); //브라우저 화면에 나타날 게시물 개수
								if(mlist.isEmpty()){
									out.println("등록된 게시물이 없습니다.");
								}
						%>
						<%
						for(int i = 0; i < 4; i++){
							if(i == mlistSize)  
								break;
						MovieBean bean = mlist.get(i);
							int m_id = bean.getM_id();
							String m_title = bean.getM_title();
							String m_poster = bean.getM_poster();
							String m_date = bean.getM_date();
						%>
					
                      <a href="../movieView.mo?m_id=<%=m_id%>" class="post_area">
                        <div class="post_thumb"> 
                        <img src="../moviefile/<%=m_poster%>" width="220" height="200"> 
                        </div>
                        <div class="post_info">
                            <span class="m_title"><%=m_title%></span>
                            <dl class="m_list">
                            <dt class="m_dt">개봉일</dt>
                                <dd><%=m_date%></dd>
                            </dl>
                        </div>  
                       
                      </a>
                      <% } %> 
                        </div> 
                        
                         <div class="post_view">
                        <%
                          mlist = bMgr.getMovieList(mField, mWord);
							mlistSize = mlist.size(); //브라우저 화면에 나타날 게시물 개수
								if(mlist.isEmpty()){
									out.println("등록된 게시물이 없습니다.");
								}
						%>
						<%
						for(int i = 4; i < 8; i++){
							if(i == mlistSize)  
								break;
						MovieBean bean = mlist.get(i);
							int m_id = bean.getM_id();
							String m_title = bean.getM_title();
							String m_poster = bean.getM_poster();
							String m_date = bean.getM_date();
						%>
					
                      <a href="../movieView.mo?m_id=<%=m_id%>" class="post_area">
                        <div class="post_thumb"> 
                        <img src="../moviefile/<%=m_poster%>" width="220" height="200"> 
                        </div>
                        <div class="post_info">
                            <span class="m_title"><%=m_title%></span>
                            <dl class="m_list">
                            <dt class="m_dt">개봉일</dt>
                                <dd><%=m_date%></dd>
                            </dl>
                        </div>  
                       
                      </a>
                      <% } %> 
                        </div> 
                          
                      
                      </div>
                               
                  </section>
                  <section id="section4">
                    <div id="news">
                    <%
                          nlist = bMgr.getNewsList(nField, nWord);
							nlistSize = nlist.size(); //브라우저 화면에 나타날 게시물 개수
								if(nlist.isEmpty()){
									out.println("등록된 게시물이 없습니다.");
								}
						%>
                      <ul class="news_list">
                      	<%
							for(int i = 0; i< 3; i++){
								if(i == nlistSize)  
									break;
							BoardBean bean = nlist.get(i);
								int news_num = bean.getNews_num();
							String news_subject = bean.getNews_subject();
							String img_a = bean.getImg_a();
							String content_a = bean.getContent_a();
							
						%>
                        <li>
                            <a href="../newsView.mo?news_num=<%=news_num%>">
                              <img src="../newsfile/<%=img_a%>" class="news_thumb">   
                                <div class="news_text">
                                <p class="news_title">
                                    <%=news_subject%>
                                </p>
                                    <div class="news_content">
                                    <%=content_a%>
                                    </div>    
                                </div>
                            </a>  
                        </li>
                        <% } %>
                        
                          <div class="news_plus"><a href="../newsList.mo">MORE</a></div>
                        </ul>
                      
                      </div>
                  </section>
                </div>    
            
        
           
            <div id="footer">
                  <article>
              copyright@<br><br><hr><br>MOVIE주식회사: 인천광역시 서구 서곶로 284 새터빌딩 4층 연희직업전문학교<p/>대표전화: 032-555-1111&nbsp;/&nbsp;Email: movie@gmail.com 
                </article>      
              </div>
           
          </div>
      
</body>
</html>