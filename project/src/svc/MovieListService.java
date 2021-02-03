package svc;

import java.sql.Connection;
import java.util.ArrayList;

import dao.BoardDAO;
import dao.MovieListDAO;
import vo.BoardBean;
import vo.MovieList;

import static db.JdbcUtil.*;

public class MovieListService {

	
	public int getListCount() throws Exception{
	      
	      int listCount = 0; //총 영화의 갯수를 담을 변수 선언
	      Connection con = getConnection();
	      MovieListDAO MovieDAO = MovieListDAO.getInstance();
	      MovieDAO.setConnection(con);
	      listCount = MovieDAO.selectListCount(); //영화리스트에서 영화의 개수 반환하는 메소드 호출
	      close(con);
	      return listCount;
	      
	   }
	
	
	
	
	public ArrayList<MovieList> getMovieList(int page, int limit) throws Exception{
		
		ArrayList<MovieList> movieList = null; //해당 페이지에 출력될 영화 리스트를 담을 변수 선언
		MovieListDAO MovieDAO = MovieListDAO.getInstance();
		Connection con = getConnection();
		MovieDAO.setConnection(con);
		movieList = MovieDAO.selectMovieList(page,limit); //db에서 해당 페이지에 출력될 영화 리스트를 반환하는 메소드 호출
		return movieList;
	}

}
