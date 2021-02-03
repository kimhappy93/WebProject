package svc;

import java.sql.Connection;

import dao.MovieListDAO;
import vo.MovieList;
import static db.JdbcUtil.*;

public class MovieRegistService {

   public boolean registMovieList(MovieList movieList) {
      
	  //MovieListDAO 클래스 인스턴스 얻어오기
      MovieListDAO movieListDAO = MovieListDAO.getInstance();
      Connection con = getConnection();
      movieListDAO.setConnection(con);
      
      //db에서 영화 등록 메소드 호출
      int insertMovie = movieListDAO.insertMovieList(movieList);
      
      //영화등록 성공 여부
      boolean isRegistSucess = false;
      
      //영화등록 성공시 트랜잭션 완성
      if(insertMovie > 0) {
         commit(con);
         isRegistSucess = true;
         
      //영화등록 실패시 트랜잭션 취소
      }else {
         rollback(con);
      }
      
      close(con);
   
      return isRegistSucess;
   }
}