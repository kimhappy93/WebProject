package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import svc.BoardDetailService;
import vo.ActionForward;
import vo.BoardBean;
import vo.MovieBean;

public class BoardModifyFormAction implements Action {
	
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 
		 	ActionForward forward = new ActionForward();
			int board_num=Integer.parseInt(request.getParameter("board_num"));
			
			String boardtype = request.getParameter("boardtype");
			
			BoardDetailService boardDetailService = new BoardDetailService();	
		   	MovieBean article =boardDetailService.getArticle(board_num, boardtype);
		   	request.setAttribute("article", article);
	   		forward.setPath("boardmodify.jsp");
	   		return forward;
	   		
	 }
	 
}