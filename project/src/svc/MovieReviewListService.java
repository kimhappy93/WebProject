package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import dao.MovieReviewDAO;
import vo.MovieReviewBean;


public class MovieReviewListService {

	

	public ArrayList<MovieReviewBean> selectMovieReview(MovieReviewBean movieReviewBean,int m_id) throws Exception{
		// TODO Auto-generated method stub
		
		ArrayList<MovieReviewBean> review= null;
		
		Connection con = getConnection();
		MovieReviewDAO moviereviewDAO = MovieReviewDAO.getInstance();
		moviereviewDAO.setConnection(con);
		review = moviereviewDAO.selectMovieReview(movieReviewBean,m_id);
		
		return review;
	
	}
	
	
	
	
}
