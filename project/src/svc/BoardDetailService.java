package svc;

import java.sql.Connection;

import dao.BoardDAO;
import vo.BoardBean;
import vo.MovieBean;

import static db.JdbcUtil.*;

public class BoardDetailService {
	
	public MovieBean getArticle(int board_num, String boardtype) throws Exception{
	      // TODO Auto-generated method stub
	      
		  MovieBean article = null;
	      Connection con = getConnection();
	      BoardDAO boardDAO = BoardDAO.getInstance();
	      boardDAO.setConnection(con);
	      int updateCount = boardDAO.updateReadCount(board_num, boardtype);
	      
	      if(updateCount > 0){
	         commit(con);
	      }
	      else{
	         rollback(con);
	      }
	      
	      article = boardDAO.selectArticle(board_num, boardtype);
	      close(con);
	      return article;
	      
	   }
}
