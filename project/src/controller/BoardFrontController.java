package controller;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.AdminNotice;
import action.BoardCommentAction;
import action.BoardCommentDeleteProAction;
import action.BoardDeleteProAction;
import action.BoardDetailAction;
import action.BoardListAction;
import action.BoardModifyFormAction;
import action.BoardModifyProAction;
import action.BoardWriteProAction;
import action.MovieListAction;
import action.MovieRegistAction;
import action.MovieViewAction;
import vo.ActionForward;

@WebServlet("*.bo")
public class BoardFrontController extends HttpServlet {
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	    String RequestURI=request.getRequestURI();
	    String contextPath=request.getContextPath();
	    String command=RequestURI.substring(contextPath.length());
	    ActionForward forward=null;
	    Action action=null;
	    
//	    占싻기가 占쏙옙占쏙옙占쏙옙 占싸븝옙 (url占쏙옙 占쌍소곤옙占쏙옙 占쏙옙占쏙옙 占실댐옙)
	    if(command.equals("/subpage/boardList.bo")){
            action = new BoardListAction();
            try{
               forward=action.execute(request, response); // BoardListAction 클占쏙옙占쏙옙占쏙옙 占쌨소듸옙 占쏙옙占쏙옙
            }catch(Exception e){
               e.printStackTrace();
            }
	    } else if(command.equals("/subpage/boardWriteForm.bo")){ 
	         forward=new ActionForward();
	         forward.setPath("boardwrite.jsp");
	    } else if(command.equals("/subpage/boardWritePro.bo")){
	         action  = new BoardWriteProAction();
	         try {
	            forward= action.execute(request, response);
	         } catch (Exception e) {
	            e.printStackTrace();
	         }
	    } else if(command.equals("/subpage/boardDetail.bo")){
	         action = new BoardDetailAction();
	         try{
	            forward=action.execute(request, response);
	         }catch(Exception e){
	            e.printStackTrace();
	         }
	    } else if(command.equals("/subpage/boardDeletePro.bo")){
	         action = new BoardDeleteProAction();
	         
	         try{
	            forward=action.execute(request, response);
	         }catch(Exception e){
	            e.printStackTrace();
	         }
	    } else if(command.equals("/subpage/boardModifyForm.bo")){
			action = new BoardModifyFormAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		} else if(command.equals("/subpage/boardModifyPro.bo")){
			action = new BoardModifyProAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		} else if(command.equals("/subpage/comment.bo")){
			action = new BoardCommentAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		} else if(command.equals("/subpage/commentDeletePro.bo")){
	         action = new BoardCommentDeleteProAction();
	         
	         try{
	            forward=action.execute(request, response);
	         }catch(Exception e){
	            e.printStackTrace();
	         }
	   } else if(command.equals("/boardWritePro.bo")){//愿�由ъ옄�럹�씠吏� 怨듭��궗�빆�엯�땲�떎.
	         action  = new AdminNotice();
	         try {
	            forward= action.execute(request, response);
	         } catch (Exception e) {
	            e.printStackTrace();
	         }
	    }
	    
	    
	    
	    
//	    占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占싱듸옙
	    if(forward != null){
	         
	         if(forward.isRedirect()){
	            response.sendRedirect(forward.getPath());
	         }else{
	            RequestDispatcher dispatcher=
	                  request.getRequestDispatcher(forward.getPath());
	            dispatcher.forward(request, response);
	         }
	         
	      }
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
