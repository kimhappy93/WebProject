package action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import svc.MovieRegistService;
import vo.ActionForward;
import vo.MovieList;

public class MovieRegistAction implements Action {

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      
      MovieRegistService movieRegistService = new  MovieRegistService();
      
      String realFolder = ""; //���� ���� ���� ��θ� ������ ���� ��θ� ������ ���� ����
      String saveFolder = "/moviefile"; //���� ���ε��� ���丮
      String encType = "UTF-8";
      int maxSize = 1024*1024*1024; //���ε��� ���� ������
      
      ServletContext context = request.getServletContext();
      realFolder = context.getRealPath(saveFolder); //���丮 ���� ���� ���� ���
      
      //���� ���ε带 ó���ϴ� MultipartRequest ��ü ����
      MultipartRequest multi = new MultipartRequest(request,
            realFolder, maxSize, encType,
            new DefaultFileRenamePolicy());
      
      ArrayList<String> movieInfo = new ArrayList<String>();
      ArrayList<String> movieFileName = new ArrayList<String>();
      
      Enumeration files = multi.getFileNames(); //iterator ���� ����
      
      while(files.hasMoreElements()) {
    	  movieInfo.add((String)files.nextElement()); //���ε� �� ���� ���� ����
       }
       
       int i = 0;
       
       //������ �������� ���޵Ǳ� ������ �ݺ����� ������ �����ؼ� ������ ������� ����
       for(i = movieInfo.size()-1; i >=0; i--) {
    	   movieFileName.add(multi.getFilesystemName(movieInfo.get(i)));
       }
      
       Collections.sort(movieFileName); //���ϸ� �������� ����
       System.out.println(movieFileName.get(0) + ":" + movieFileName.get(1) + ":" + movieFileName.get(2) + ":" + movieFileName.get(3) + ":" + movieFileName.get(4) + ":" + movieFileName.get(5) + ":" + movieFileName.get(6) + ":" + movieFileName.get(7)) ;      
      
      //��ȭ����ϴ� ����Ͻ� ������ ����Ǵ� �޼ҵ� ȣ��
      MovieList movieList = new MovieList(
              0, 
              multi.getParameter("m_title"), 
              movieFileName.get(0),  
              multi.getParameter("m_date"),
              movieFileName.get(1),
              multi.getParameter("m_title_eng"),
              Integer.parseInt(multi.getParameter("m_year")),
              multi.getParameter("m_nation"),
              multi.getParameter("m_age"),
              multi.getParameter("m_genre"),
              multi.getParameter("m_time"),
              multi.getParameter("m_total"),
              multi.getParameter("m_director"),
              multi.getParameter("m_actor"),
              multi.getParameter("m_summary1"),
              multi.getParameter("m_summary2"),
              multi.getParameter("m_summary3"),
              movieFileName.get(2),
              movieFileName.get(3),
              movieFileName.get(4),
              movieFileName.get(5),
              movieFileName.get(6),
              movieFileName.get(7),
              multi.getParameter("m_video"));
      
      
      boolean isRegistSucess = movieRegistService.registMovieList(movieList);
      
      ActionForward forward = null;
      
      if(isRegistSucess) {
    	 //��ȭ��ϼ����� ��ȭ����Ʈ ���� ��û ����
         forward = new ActionForward("movieList.mo",true);
      }else {
    	 //��ȭ��Ͻ��н� ���â ����� ���� �������� �̵�
         response.setContentType("text/html;charset=UTF-8");
         PrintWriter out = response.getWriter();
         out.println("<script>");
         out.println("alert('��ȭ��Ͻ���');");
         out.println("history.back();");
         out.println("</script>");
      }
      
      return forward;
   }

}