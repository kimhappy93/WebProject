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
	      
	      int listCount = 0; //�� ��ȭ�� ������ ���� ���� ����
	      Connection con = getConnection();
	      MovieListDAO MovieDAO = MovieListDAO.getInstance();
	      MovieDAO.setConnection(con);
	      listCount = MovieDAO.selectListCount(); //��ȭ����Ʈ���� ��ȭ�� ���� ��ȯ�ϴ� �޼ҵ� ȣ��
	      close(con);
	      return listCount;
	      
	   }
	
	
	
	
	public ArrayList<MovieList> getMovieList(int page, int limit) throws Exception{
		
		ArrayList<MovieList> movieList = null; //�ش� �������� ��µ� ��ȭ ����Ʈ�� ���� ���� ����
		MovieListDAO MovieDAO = MovieListDAO.getInstance();
		Connection con = getConnection();
		MovieDAO.setConnection(con);
		movieList = MovieDAO.selectMovieList(page,limit); //db���� �ش� �������� ��µ� ��ȭ ����Ʈ�� ��ȯ�ϴ� �޼ҵ� ȣ��
		return movieList;
	}

}
