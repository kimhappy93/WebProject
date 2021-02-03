package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MovieReviewRegistService;
import vo.ActionForward;
import vo.MovieReviewBean;

public class MovieReviewRegistAction implements Action {

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      
      ActionForward forward = null;
      HttpSession session = request.getSession(); //HttpSession 객체 생성
      
       MovieReviewBean movieReviewBean = new MovieReviewBean();
              
       int m_id = Integer.parseInt(request.getParameter("m_id"));
       movieReviewBean.setM_id(m_id);
       movieReviewBean.setM_star(Integer.parseInt(request.getParameter("m_star")));
       movieReviewBean.setM_review(request.getParameter("m_review"));
       movieReviewBean.setId((String)session.getAttribute("idKey")); //세션에서 로그인한 아이디 데이터 추출 
       
       //영화 리뷰 및 평점 등록 메소드 호출
       MovieReviewRegistService movieReviewRegistService = new MovieReviewRegistService();
       boolean isRegistSucess = movieReviewRegistService.registMovieReview(movieReviewBean);
       
       	 //영화 리뷰 및 평점 등록 성공 시 해당 영화상세정보 페이지 조회
         if(isRegistSucess){
            forward = new ActionForward("movieView.jsp",true);
            forward.setRedirect(true);
            forward.setPath("movieView.mo?m_id=" + m_id);
         }
         
         //비로그인 시 영화 리뷰 및 평점 등록 실패하므로 로그인 페이지로 이동
         else{
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>");
            out.println("alert('로그인이 필요합니다.')");
            out.println("javascript:location.href='./login/login.jsp'");
            out.println("</script>");
         }
         
         return forward;
   }

}