<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.HashMap"%>
<%@page import="vo.MovieList"%>
<%@page import="vo.MovieReviewBean"%>
<%@page import="svc.MovieReviewListService"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
   
   request.setCharacterEncoding("UTF-8"); // 인코딩
   String id = (String)session.getAttribute("idKey"); //로그인한 아이디 값
   int m_id = Integer.parseInt(request.getParameter("m_id")); //해당 영화 아이디 파라미터 값    
   
   MovieReviewBean movieReviewBean = new MovieReviewBean();

   MovieReviewListService movieReviewService = new MovieReviewListService();   
   ArrayList<MovieReviewBean> reviewList = movieReviewService.selectMovieReview(movieReviewBean, m_id); //해당 영화의 영화 리뷰 및 평점 목록
   
%>
<!doctype html>
<html>
<head>
      <meta charset="utf-8">
<title>
main
</title>
<link rel="stylesheet" href="css/shape_movie_detatil.css">
<link rel="stylesheet" href="css/common.css" type="text/css">
<script src="css/common.js"></script> 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<body>
    <div id="wrapper">
       <a name="top"></a>
         <header id="header">
          <h1 class="title" onclick="javascript:location.href='main/main.jsp'">MOVIEMOA</h1>
            
          <form method="post" action="main/search.jsp" class="login">        
          <!--       비로그인 시       --> 
	           <%if (id == null) {%>
	            <button type="button" class="login" onclick="javascript:location.href='login/login.jsp'">Login</button>   
	            <button type="button" class="login" onclick="javascript:location.href='login/agree.jsp'">Sign in</button>
          
          <!--  관리자 계정으로 로그인 시  -->   
               <%}else if(id.equals("admin")){%>  
                <button type="button" class="login" onclick="javascript:location.href='adminPage.jsp'">관리자 화면</button>
                <button type="button" class="login" onclick="javascript:location.href='login/logout.jsp'">Logout</button>
          
          <!--        로그인 시       -->    
              <%} else if(id != null) {%>
              <span id="hi"><%=id %> 님, 반갑습니다.</span>
                 <button type="button" class="login" onclick="javascript:location.href='login/logout.jsp'">Logout</button><%} %>
                 <input type="text" name="search"  id="search" class="login" placeholder="Search...">
                 <input type="submit" id="searchBtn">
              </form>
          
          <!--        네비 버튼       -->        
             <nav id="nav">
                  <span class="btn" onclick="javascript:location.href='movieList.mo'">MovieInfo</span>
                  <span class="btn" onclick="javascript:location.href='subpage/boardList.bo?boardtype=BOARDALL'">MovieTalk</span>
                  <span class="btn" onclick="javascript:location.href='newsList.mo'">MovieNEWS</span>
                  <span class="btn" onclick="javascript:location.href='main/loc.jsp'">Theater</span> 
              </nav>
            </header> 
                
		 <!--         사이드바         --> 
         <div id="sidebar">
            <div class="sub_tab">
               <ul>
                  <li>
                     <a href="#info">영화정보</a>
                  </li>
                  <li>
                     <a href="#photo">포토</a>
                  </li>
                  <li>
                     <a href="#video">공식예고편</a>
                  </li>
                  <li>
                     <a href="#review">네티즌리뷰</a>
                  </li>
               </ul>
            </div>
         </div>
         
        <!--         영화 배너         -->  
        <div id="banner"></div>
        
		<!--         영화 정보         --> 
         <div id="main">
            <a name="info"></a>
            <div id="poster">
                <img src="moviefile/${movieList.m_poster}">
            </div>
            <div id="info">
                <h1 class="info_title">${movieList.m_title}</h1>
                <p class="info_eng">${movieList.m_title_eng}</p>
                <span>${movieList.m_year}</span>
                <span>${movieList.m_nation}</span>
                <span class="age">${movieList.m_age}</span><br>
                <span>${movieList.m_genre}</span>
                <span>상영시간:${movieList.m_time}</span><br>
                <span>개봉일:${movieList.m_date}</span>
                <span>누적관객:${movieList.m_total}</span><br>
                <span>감독:${movieList.m_director}</span><br>
                <span>출연:${movieList.m_actor}</span> 
            </div>
            
                <div class="detailinfo showstep1">
                    <span class="content">
                       <h3>${movieList.m_summary1}</h3>
                        <p>${movieList.m_summary2}<br><br>
                        <b>${movieList.m_summary3}</b>
                    </span>
                </div>
                <span href="#" class="btn_open">더보기</span>

        <!--        영화 리뷰 및 평점        --> 
            
            <!--        평점        -->
            <section id="star">
                <h3>별점주기</h3>
                <form action="movieReviewRegist.mo" method="post" name = "reviewForm">
                   <input type = "hidden" name="m_id" value="<%=m_id%>">
                     <h3 id="log"></h3>
                  <fieldset class="rating">
	                  <input type="checkbox" id="star5" name="m_star" value="5" /><label class = "full" for="star5"></label>
	                  <input type="checkbox" id="star4" name="m_star" value="4" /><label class = "full" for="star4"></label>
	                  <input type="checkbox" id="star3" name="m_star" value="3" /><label class = "full" for="star3"></label>
	                  <input type="checkbox" id="star2" name="m_star" value="2" /><label class = "full" for="star2"></label>
	                  <input type="checkbox" id="star1" name="m_star" value="1" /><label class = "full" for="star1"></label>
               	  </fieldset>
               	  
            <!--        리뷰        -->
	                <input type="text" id="review1" name="m_review" required="required"/>
	                <input type = "submit" class="review_button1" value = "리뷰남기기" onclick="return chkchk(this);"/>  
                </form>    
            </section>

            <!--        사진 슬라이드        -->
            <section id="slide">
               <a name="photo"></a>
                <h3 class="s_title">포토</h3>
                <div class="container">
                  <div class="mySlides">
                    <div class="numbertext">1 / 6</div>
                    <img src="moviefile/${movieList.m_slide1}" style="width:100%">
                  </div>

                  <div class="mySlides">
                    <div class="numbertext">2 / 6</div>
                    <img src="moviefile/${movieList.m_slide2}" style="width:100%">
                  </div>

                  <div class="mySlides">
                    <div class="numbertext">3 / 6</div>
                    <img src="moviefile/${movieList.m_slide3}" style="width:100%">
                  </div>

                  <div class="mySlides">
                    <div class="numbertext">4 / 6</div>
                    <img src="moviefile/${movieList.m_slide4}" style="width:100%">
                  </div>

                  <div class="mySlides">
                    <div class="numbertext">5 / 6</div>
                    <img src="moviefile/${movieList.m_slide5}" style="width:100%">
                  </div>

                  <div class="mySlides">
                    <div class="numbertext">6 / 6</div>
                    <img src="moviefile/${movieList.m_slide6}" style="width:100%">
                  </div>

                  <a class="sprev" onclick="plusSlides(-1)">❮</a>
                  <a class="snext" onclick="plusSlides(1)">❯</a>

                  <div class="caption-container">
                    <p id="caption"></p>
                  </div>

                  <div class="row">
                    <div class="column">
                      <img class="demo cursor" src="moviefile/${movieList.m_slide1}" style="width:90%" onclick="currentSlide(1)" alt="영화 스틸컷1">
                    </div>
                    <div class="column">
                      <img class="demo cursor" src="moviefile/${movieList.m_slide2}" style="width:90%" onclick="currentSlide(2)" alt="영화 스틸컷2">
                    </div>
                    <div class="column">
                      <img class="demo cursor" src="moviefile/${movieList.m_slide3}" style="width:90%" onclick="currentSlide(3)" alt="영화 스틸컷3">
                    </div>
                    <div class="column">
                      <img class="demo cursor" src="moviefile/${movieList.m_slide4}" style="width:90%" onclick="currentSlide(4)" alt="영화 스틸컷4">
                    </div>
                    <div class="column">
                      <img class="demo cursor" src="moviefile/${movieList.m_slide5}" style="width:90%" onclick="currentSlide(5)" alt="영화 스틸컷5">
                    </div>    
                    <div class="column">
                      <img class="demo cursor" src="moviefile/${movieList.m_slide6}" style="width:90%" onclick="currentSlide(6)" alt="영화 스틸컷6">
                    </div>
                  </div>
                </div>
            </section>
            
            <!--        영상        -->
            <section id="video">
               <a name="video"></a>
                <h3 class="s_title">공식예고편</h3>
                <div id="videobox">
                    <iframe width="980" height="520" src="${movieList.m_video}" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
                </div>
            </section>
            
            
            <!--        영화 리뷰 및 평점 목록        -->
            <section id="review2">
               <a name="review"></a>
                <h3 class="s_title">네티즌 리뷰</h3>
                <div id="reviewbox">
                
                <!--        영화 리뷰 및 평점이 없을 경우        -->
                <%if(reviewList.size() == 0) {%>
                <p>리뷰가 없습니다. 리뷰를 남겨주세요.</p>
                
                <!--        영화 리뷰 및 평점이 있을 경우        -->
                <%}else{ 
                
                   for(int i=0;i<reviewList.size();i++){
                %>
             
                     <ul id="review_ul">
                     	<li>
	                        <div id="date"><%=reviewList.get(i).getR_date()%></div>
	                        <div id="id"><%=reviewList.get(i).getId() %></div>
	                        <div id="r_star">
                            	<span class="star-prototype"><%=reviewList.get(i).getM_star()%></span> 
                            </div>
                            <div id="comment"><%=reviewList.get(i).getM_review()%></div>
                     		
                     		<!--        비로그인 시 삭제버튼이 노출되지않음        -->
			                    <%if(id == null) {%>
				                    <div class="btn_regist2">
				                    	<a href="movieReviewDelete.mo?r_num=<%=reviewList.get(i).getR_num()%>&m_id=<%=reviewList.get(i).getM_id()%>" onclick="return confirm('정말로 삭제하시겠습니까?')" >삭제</a>
				                    </div>
				                    
				            <!--        관리자 계정 로그인 시 모든 리뷰와 평점 삭제버튼 노출        -->
			                    <%}else if(id.equals("admin")) {%>
			                        <div class="btn_regist">
			                        	<a href="movieReviewDelete.mo?r_num=<%=reviewList.get(i).getR_num()%>&m_id=<%=reviewList.get(i).getM_id()%>" onclick="return confirm('정말로 삭제하시겠습니까?')" >삭제</a>
			                        </div>
			                        
			                <!--        로그인 시(작성자와 로그인한 아이디가 동일할 경우 삭제버튼 노출        -->
			                    <%}else if((id.equals(reviewList.get(i).getId()))) {%>
			                        <div class="btn_regist">
			                        	<a href="movieReviewDelete.mo?r_num=<%=reviewList.get(i).getR_num()%>&m_id=<%=reviewList.get(i).getM_id()%>" onclick="return confirm('정말로 삭제하시겠습니까?')" >삭제</a>
			                        </div>
			                    <%} %>
                        </li>
                     </ul>
   
                <% }
                   }
                %>
                </div>

            </section>
           
            <!--        맨 위로 버튼        -->
            <div class="top">
               <a href="#top"><img src="images/top.jpg"></a>
            </div>
         
        </div>

        <!--        푸터        -->
        <div id="footer">
	        <article>
	        copyright@<p></p><hr> MOVIE주식회사: 인천광역시 서구 서곶로 284 새터빌딩 4층 연희직업전문학교<p/>대표전화: 032-555-1111&nbsp;/&nbsp;Email: movie@gmail.com 
	        </article>      
        </div>      
    </div>
    
<script src="css/movie_detail.js"></script>

<!--        영화 배너        -->
<script>
  $(function(){
    $("#banner").css("background","linear-gradient(to bottom, transparent, black), url(moviefile/${movieList.m_banner}) no-repeat 0% 30% ")
    .css("background-size","cover").css("position","relative").css("width","100%").css("height","400px").css("margin","0 auto");
  });
</script>
            
</body>
</head>
</html>