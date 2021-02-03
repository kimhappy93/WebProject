package svc;

import java.sql.Connection;

import dao.MovieListDAO;
import vo.MovieList;

import static db.JdbcUtil.*;

public class MovieViewService {

	public MovieList getMovieView(int m_id) {
		
		Connection con = getConnection();
		MovieListDAO movielistDAO = MovieListDAO.getInstance();
		movielistDAO.setConnection(con);
		
//		int updateCount = movielistDAO.updateReadCount(m_id);
//		
//		if(updateCount>0) {
//			commit(con);
//		}else {
//			rollback(con);
//		}
		
		MovieList movieList = movielistDAO.selectMovieList(m_id);
		close(con);
		return movieList;
	}

}
