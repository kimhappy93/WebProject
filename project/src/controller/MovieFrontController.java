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


@WebServlet("*.mo") //���� ����
public class MovieFrontController extends HttpServlet {
   
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
      doProcess(request,response);
      
   }

   
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
      doProcess(request,response);
      
   }
   
   protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
      request.setCharacterEncoding("UTF-8");
      
      //���۵� ��û �ľ�
      String requestURI = request.getRequestURI();
      String contextPath = request.getContextPath();
      String command = requestURI.substring(contextPath.length());
      
      Action action = null; //�� ��û�� ó���ϴ� Action Ŭ���� ��ü ��������
      ActionForward forward = null; //Action Ŭ���� ��ü�� execute �޼ҵ� ���� �� ��ȯ�Ǵ� ActionForward ��ü�� �����ϴ� ���� ����
      
      //�ֽſ�ȭ����Ʈ
      if(command.equals("/movieList.mo")){
         action = new MovieListAction();     
         try {
            forward = action.execute(request, response);
         } catch (Exception e) {
            e.printStackTrace();
         }
         
      //��ȭ������������  
      }else if(command.equals("/movieView.mo")) {
    	  action = new MovieViewAction();
    	  try {
    		  forward = action.execute(request, response);
    	  }catch(Exception e){
    		  e.printStackTrace();
    	  }
      
      //�ֽſ�ȭ ���
      }else if(command.equals("/movieRegist.mo")) {
    	  action = new MovieRegistAction();  
    	  try {
    		  forward = action.execute(request, response);
    	  }catch(Exception e) {
    		  e.printStackTrace();
    	  }
    
      //��ȭ ���� �� ���� ���
      }else if(command.equals("/movieReviewRegist.mo")) {
    	  action = new MovieReviewRegistAction();    	  
    	  try {
    		  forward = action.execute(request, response);
    	  }catch(Exception e) {
    		  e.printStackTrace();
    	  }
      
      //��ȭ ���� �� ���� ����
      }else if(command.equals("/movieReviewDelete.mo")){
	    	int m_id=Integer.parseInt(request.getParameter("m_id")); //�ش� ��ȭ���������� ���� ���� ��ȭ���̵�
	  	  	request.setAttribute("m_id",m_id);
	  	  	int r_num=Integer.parseInt(request.getParameter("r_num")); //�ش��ϴ� ���� �� ������ �����ϱ� ���� �����ȣ
	  	  	request.setAttribute("r_num",r_num);
	  	  	forward=new ActionForward();
			action = new MovieReviewDeleteAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		//������� ���
		}else if(command.equals("/newsRegist.mo")) {
	    	  action = new NewsRegistAction();	  
	    	  try {
	    		  forward = action.execute(request, response);
	    	  }catch(Exception e) {
	    		  e.printStackTrace();
	    	  }
	    	  
	    //���������
	    }else if(command.equals("/newsList.mo")) {
	    	  action = new NewsListAction();
	    	  try {
	    		  forward = action.execute(request, response);
	    	  }catch(Exception e) {
	    		  e.printStackTrace();
	    	  }
	    
	    //��������������
	    }else if(command.equals("/newsView.mo")) {
	    	  action = new NewsViewAction(); 
	    	  try {
	    		  forward = action.execute(request, response);
	    	  }catch(Exception e) {
	    		  e.printStackTrace();
	    	  }
	    }
      
    
      //Action Ŭ���� ��ü���� ��ȯ�� ActionForward ��ü ������ ����Ͽ� ������ ó��
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