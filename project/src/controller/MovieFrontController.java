package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.MovieListAction;
import action.MovieRegistAction;
import action.MovieReviewDeleteAction;
import action.MovieReviewRegistAction;
import action.MovieViewAction;
import action.NewsListAction;
import action.NewsRegistAction;
import action.NewsViewAction;
import vo.ActionForward;


@WebServlet("*.mo") //서블릿 지정
public class MovieFrontController extends HttpServlet {
   
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
      doProcess(request,response);
      
   }

   
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
      doProcess(request,response);
      
   }
   
   protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
      request.setCharacterEncoding("UTF-8");
      
      //전송된 요청 파악
      String requestURI = request.getRequestURI();
      String contextPath = request.getContextPath();
      String command = requestURI.substring(contextPath.length());
      
      Action action = null; //각 요청을 처리하는 Action 클래스 객체 변수정의
      ActionForward forward = null; //Action 클래스 객체의 execute 메소드 실행 후 반환되는 ActionForward 객체를 저장하는 변수 정의
      
      //최신영화리스트
      if(command.equals("/movieList.mo")){
         action = new MovieListAction();     
         try {
            forward = action.execute(request, response);
         } catch (Exception e) {
            e.printStackTrace();
         }
         
      //영화상세정보페이지  
      }else if(command.equals("/movieView.mo")) {
    	  action = new MovieViewAction();
    	  try {
    		  forward = action.execute(request, response);
    	  }catch(Exception e){
    		  e.printStackTrace();
    	  }
      
      //최신영화 등록
      }else if(command.equals("/movieRegist.mo")) {
    	  action = new MovieRegistAction();  
    	  try {
    		  forward = action.execute(request, response);
    	  }catch(Exception e) {
    		  e.printStackTrace();
    	  }
    
      //영화 리뷰 및 평점 등록
      }else if(command.equals("/movieReviewRegist.mo")) {
    	  action = new MovieReviewRegistAction();    	  
    	  try {
    		  forward = action.execute(request, response);
    	  }catch(Exception e) {
    		  e.printStackTrace();
    	  }
      
      //영화 리뷰 및 평점 삭제
      }else if(command.equals("/movieReviewDelete.mo")){
	    	int m_id=Integer.parseInt(request.getParameter("m_id")); //해당 영화상세페이지를 보기 위한 영화아이디
	  	  	request.setAttribute("m_id",m_id);
	  	  	int r_num=Integer.parseInt(request.getParameter("r_num")); //해당하는 리뷰 및 평점을 삭제하기 위한 리뷰번호
	  	  	request.setAttribute("r_num",r_num);
	  	  	forward=new ActionForward();
			action = new MovieReviewDeleteAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		//뉴스기사 등록
		}else if(command.equals("/newsRegist.mo")) {
	    	  action = new NewsRegistAction();	  
	    	  try {
	    		  forward = action.execute(request, response);
	    	  }catch(Exception e) {
	    		  e.printStackTrace();
	    	  }
	    	  
	    //뉴스기사목록
	    }else if(command.equals("/newsList.mo")) {
	    	  action = new NewsListAction();
	    	  try {
	    		  forward = action.execute(request, response);
	    	  }catch(Exception e) {
	    		  e.printStackTrace();
	    	  }
	    
	    //뉴스기사상세페이지
	    }else if(command.equals("/newsView.mo")) {
	    	  action = new NewsViewAction(); 
	    	  try {
	    		  forward = action.execute(request, response);
	    	  }catch(Exception e) {
	    		  e.printStackTrace();
	    	  }
	    }
      
    
      //Action 클래스 객체에서 반환된 ActionForward 객체 정보를 사용하여 포워딩 처리
      if(forward != null) {
         if(forward.isRedirect()) {
            response.sendRedirect(forward.getPath());
         }else {
            RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
            dispatcher.forward(request, response);
         }
      }
   }

}