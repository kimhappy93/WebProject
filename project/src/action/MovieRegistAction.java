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
      
      String realFolder = ""; //서버 상의 파일 경로를 저장할 실제 경로를 저장할 변수 정의
      String saveFolder = "/moviefile"; //파일 업로드할 디렉토리
      String encType = "UTF-8";
      int maxSize = 1024*1024*1024; //업로드할 파일 사이즈
      
      ServletContext context = request.getServletContext();
      realFolder = context.getRealPath(saveFolder); //디렉토리 서버 상의 실제 경로
      
      //파일 업로드를 처리하는 MultipartRequest 객체 생성
      MultipartRequest multi = new MultipartRequest(request,
            realFolder, maxSize, encType,
            new DefaultFileRenamePolicy());
      
      ArrayList<String> movieInfo = new ArrayList<String>();
      ArrayList<String> movieFileName = new ArrayList<String>();
      
      Enumeration files = multi.getFileNames(); //iterator 예전 버전
      
      while(files.hasMoreElements()) {
    	  movieInfo.add((String)files.nextElement()); //업로드 된 파일 역순 전달
       }
       
       int i = 0;
       
       //파일이 역순으로 전달되기 때문에 반복문을 역으로 수행해서 원래의 순서대로 저장
       for(i = movieInfo.size()-1; i >=0; i--) {
    	   movieFileName.add(multi.getFilesystemName(movieInfo.get(i)));
       }
      
       Collections.sort(movieFileName); //파일명 오름차순 정렬
       System.out.println(movieFileName.get(0) + ":" + movieFileName.get(1) + ":" + movieFileName.get(2) + ":" + movieFileName.get(3) + ":" + movieFileName.get(4) + ":" + movieFileName.get(5) + ":" + movieFileName.get(6) + ":" + movieFileName.get(7)) ;      
      
      //영화등록하는 비즈니스 로직이 실행되는 메소드 호출
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
    	 //영화등록성공시 영화리스트 보기 요청 실행
         forward = new ActionForward("movieList.mo",true);
      }else {
    	 //영화등록실패시 경고창 출력후 이전 페이지로 이동
         response.setContentType("text/html;charset=UTF-8");
         PrintWriter out = response.getWriter();
         out.println("<script>");
         out.println("alert('영화등록실패');");
         out.println("history.back();");
         out.println("</script>");
      }
      
      return forward;
   }

}