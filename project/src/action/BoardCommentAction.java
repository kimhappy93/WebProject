package action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BoardCommentService;
import vo.ActionForward;
import vo.MovieBean;
import vo.ReplyBean;

public class BoardCommentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
	    String nowPage = request.getParameter("page");
	    String boardtype = request.getParameter("boardtype");
	    
	    String tName = null;
	    String tType = null;
	    
		if(boardtype.equalsIgnoreCase("MOVIE")) {
		     tName = "MOVIE";
		     tType = "M";
		} else if(boardtype.equalsIgnoreCase("FREE")) {
		     tName = "FREE";
		     tType = "F";
		}
	    
	    int board_num = Integer.parseInt(request.getParameter(tType+ "_REPLY_REF_NUM"));
	    ReplyBean replybean = new ReplyBean();  		
	    
	    
		
		System.out.println("tType: " + tType);
	
//		if(boardtype.equalsIgnoreCase("MOVIE")) {
//			tType = "M";
//		} else if(boardtype.equalsIgnoreCase("FREE")) {
//			tType = "F";
//		}	
	    
//		�Ķ���� ��� test
		System.out.println(tType+ "_REPLY_NUM: " + request.getParameter(tType+"_REPLY_REF_NUM"));
		System.out.println("id: " + request.getParameter("ID"));
		System.out.println(tType+"_CONTENT: " + request.getParameter(tType+ "_CONTENT"));
	    
	    
		replybean.setM_reply_ref_num(Integer.parseInt(request.getParameter(tType+"_REPLY_REF_NUM")));
		replybean.setId(request.getParameter("ID"));
		replybean.setM_content(request.getParameter(tType+"_CONTENT"));	
	 	BoardCommentService boardCommentService = new BoardCommentService();
	 
	 	
	 	boolean isReplySuccess = boardCommentService.replyArticle(replybean, boardtype);
	 	
	 	
   		if(isReplySuccess){
   		    forward= new ActionForward();
   		    System.out.println("boardtype : " + boardtype);
   	        forward.setPath("boardDetail.bo?boardtype=" +boardtype+ "&board_num=" + board_num + "&page=" + nowPage);
   	        //forward.setPath("boardDetail.bo?boardtype=" +boardtype+ "&board_num=" + board_num + "&page=" + nowPage);
   	        return forward;
   	         
   		} else {
   			response.setContentType("text/html;charset=UTF-8");
   			PrintWriter out = response.getWriter();
   			out.println("<script>");
   			out.println("alert('BoardCommentAction error')");
   			out.println("history.back()");
   			out.println("</script>");
   		}
   		
   		System.out.println(tType+"_CONTENT: " + request.getParameter(tType+"_CONTENT"));

   		
   		return forward;
	}

}
