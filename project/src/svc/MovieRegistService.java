package svc;

import java.sql.Connection;

import dao.MovieListDAO;
import vo.MovieList;
import static db.JdbcUtil.*;

public class MovieRegistService {

   public boolean registMovieList(MovieList movieList) {
      
	  //MovieListDAO Ŭ���� �ν��Ͻ� ������
      MovieListDAO movieListDAO = MovieListDAO.getInstance();
      Connection con = getConnection();
      movieListDAO.setConnection(con);
      
      //db���� ��ȭ ��� �޼ҵ� ȣ��
      int insertMovie = movieListDAO.insertMovieList(movieList);
      
      //��ȭ��� ���� ����
      boolean isRegistSucess = false;
      
      //��ȭ��� ������ Ʈ����� �ϼ�
      if(insertMovie > 0) {
         commit(con);
         isRegistSucess = true;
         
      //��ȭ��� ���н� Ʈ����� ���
      }else {
         rollback(con);
      }
      
      close(con);
   
      return isRegistSucess;
   }
}