package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.MovieReviewDAO;
import vo.MovieReviewBean;

public class MovieReviewRegistService {

	public boolean registMovieReview(MovieReviewBean movieReviewBean) {
	      
		  //MovieReviewDAO 클래스 인스턴스 얻어오기
		  MovieReviewDAO movieReviewDAO = MovieReviewDAO.getInstance();
	      Connection con = getConnection();
	      movieReviewDAO.setConnection(con);
	      
	      //db에서 영화 리뷰 및 평점 등록 메소드 호출
	      int insertMovieReview = movieReviewDAO.insertMovieReview(movieReviewBean);
	      
	      //영화 리뷰 및 평점 등록 성공 여부
	      boolean isRegistSucess = false;
	      
	      //영화 리뷰 및 평점 등록 성공시 트랜잭션 완성
	      if(insertMovieReview  > 0) {
	         commit(con);
	         isRegistSucess = true;
	         
	      //영화 리뷰 및 평점 등록 실패시 트랜잭션 취소
	      }else {
	         rollback(con);
	      }
	      
	      close(con);
	   
	      return isRegistSucess;
	   }
	
}
