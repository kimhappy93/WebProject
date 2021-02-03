package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.MovieReviewDAO;

public class MovieReviewDeleteService {

	
	   public boolean removeReview(int r_num) throws Exception{
	      
	      boolean isRemoveSuccess = false;
	      Connection con = getConnection();
	      MovieReviewDAO movieReviewDAO = MovieReviewDAO.getInstance();
	      movieReviewDAO.setConnection(con);
	      int deleteCount = movieReviewDAO.deleteReview(r_num);
	      
	      if(deleteCount > 0){
	         commit(con);
	         isRemoveSuccess=true;
	      }
	      else{
	         rollback(con);
	      }
	      
	      close(con);
	      return isRemoveSuccess;
	   }
}
