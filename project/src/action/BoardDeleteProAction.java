	package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.ActionForward;
import vo.BoardBean;
import svc.BoardDeleteProService;

public class BoardDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
	      int board_num=Integer.parseInt(request.getParameter("board_num"));
	      String nowPage = request.getParameter("page");
	      
	      String boardtype = request.getParameter("boardtype");
	      
	      BoardDeleteProService boardDeleteProService = new BoardDeleteProService();
	      

	         boolean isDeleteSuccess = boardDeleteProService.removeArticle(board_num, boardtype);

	         if(!isDeleteSuccess){
	            response.setContentType("text/html;charset=UTF-8");
	            PrintWriter out=response.getWriter();
	            out.println("<script>");
	            out.println("alert('삭제실패');");
	            out.println("history.back();");
	            out.println("</script>");
	            out.close();
	         }
	         else{
	        	forward = new ActionForward();
	            forward.setRedirect(true);
	            forward.setPath("boardList.bo?boardtype=BOARD" + boardtype);
	         }
	        

	      return forward;
	      
//		ActionForward forward = null;
//		String nowPage = request.getParameter("page");
//		
//		int board_num = Integer.parseInt(request.getParameter("board_num"));
//		String pass = request.getParameter("BOARD_PASS");
//        BoardDeleteProService boardDeleteProService = new BoardDeleteProService();
//        boolean isDeleteSuccess = boardDeleteProService.isArticleWriter(board_num, pass);
//        
//          if(isDeleteSuccess){
//             forward = new ActionForward();
//             forward.setRedirect(true);
//             forward.setPath("boardDeleteForm.bo="  + nowPage);
//          }
//          else{
//             response.setContentType("text/html;charset=UTF-8");
//             PrintWriter out = response.getWriter();
//             out.println("<script>");
//             out.println("alert('삭제 실패')");
//             out.println("history.back()");
//             out.println("</script>");
//          }
//          
//          return forward;
	}

}
