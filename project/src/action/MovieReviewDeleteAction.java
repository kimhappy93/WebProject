package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.MovieReviewDeleteService;
import vo.ActionForward;

public class MovieReviewDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		
	      int r_num=Integer.parseInt(request.getParameter("r_num")); //삭제되는 리뷰 및 평점의 글번호
	      int m_id = Integer.parseInt(request.getParameter("m_id")); //해당 영화 페이지 파라미터 값
	      
	      MovieReviewDeleteService movieReviewDeleteService = new MovieReviewDeleteService();
	      
	      //영화 리뷰 및 평저 삭제 성공 여부
          boolean isDeleteSuccess = movieReviewDeleteService.removeReview(r_num);

          //삭제 실패 시 경고창
          if(!isDeleteSuccess){
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out=response.getWriter();
            out.println("<script>");
            out.println("alert('리뷰 삭제 실패');");
            out.println("history.back();");
            out.println("</script>");
            out.close();
          }
          
          //삭제 성공 시 이전에 보던 해당 영화상세정보 페이지로 이동
          else{
        	
            forward = new ActionForward();
            forward.setRedirect(true);
            forward.setPath("movieView.mo?m_id=" + m_id);
          }
         


       return forward;
	}

}
