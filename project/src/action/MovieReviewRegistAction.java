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
      HttpSession session = request.getSession(); //HttpSession ��ü ����
      
       MovieReviewBean movieReviewBean = new MovieReviewBean();
              
       int m_id = Integer.parseInt(request.getParameter("m_id"));
       movieReviewBean.setM_id(m_id);
       movieReviewBean.setM_star(Integer.parseInt(request.getParameter("m_star")));
       movieReviewBean.setM_review(request.getParameter("m_review"));
       movieReviewBean.setId((String)session.getAttribute("idKey")); //���ǿ��� �α����� ���̵� ������ ���� 
       
       //��ȭ ���� �� ���� ��� �޼ҵ� ȣ��
       MovieReviewRegistService movieReviewRegistService = new MovieReviewRegistService();
       boolean isRegistSucess = movieReviewRegistService.registMovieReview(movieReviewBean);
       
       	 //��ȭ ���� �� ���� ��� ���� �� �ش� ��ȭ������ ������ ��ȸ
         if(isRegistSucess){
            forward = new ActionForward("movieView.jsp",true);
            forward.setRedirect(true);
            forward.setPath("movieView.mo?m_id=" + m_id);
         }
         
         //��α��� �� ��ȭ ���� �� ���� ��� �����ϹǷ� �α��� �������� �̵�
         else{
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>");
            out.println("alert('�α����� �ʿ��մϴ�.')");
            out.println("javascript:location.href='./login/login.jsp'");
            out.println("</script>");
         }
         
         return forward;
   }

}