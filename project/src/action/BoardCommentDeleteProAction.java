	package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.ActionForward;
import vo.BoardBean;
import svc.BoardCommentDeleteProService;

public class BoardCommentDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String tName = null;
		String tType = null;
		String boardtype = request.getParameter("boardtype");
		
		if(boardtype.equalsIgnoreCase("MOVIE")) {
		     tName = "MOVIE";
		} else if(boardtype.equalsIgnoreCase("FREE")) {
		     tName = "FREE";
		}
		
		if(boardtype.equalsIgnoreCase("MOVIE")) {
			tType = "M";
		} else if(boardtype.equalsIgnoreCase("FREE")) {
			tType = "F";
		}		
		
		int reply_num=Integer.parseInt(request.getParameter(tType+"_reply_num"));
	    int board_num=Integer.parseInt(request.getParameter("board_num"));
	    String nowPage = request.getParameter("page");
	    
	    
	    BoardCommentDeleteProService boardCommentDeleteProService = new BoardCommentDeleteProService();

	    boolean isDeleteSuccess = boardCommentDeleteProService.removeArticle(reply_num, boardtype);

	    if(!isDeleteSuccess){
	            response.setContentType("text/html;charset=UTF-8");
	            PrintWriter out=response.getWriter();
	            out.println("<script>");
	            out.println("alert('Comment Delete Error');");
	            out.println("history.back();");
	            out.println("</script>");
	            out.close();
	    } else {
	        	forward = new ActionForward();
	            forward.setRedirect(true);
	            forward.setPath("boardDetail.bo?boardtype=" +boardtype+ "&board_num=" +board_num+ "&page=" + nowPage);
	    }
	        

	    return forward;
	}

}
