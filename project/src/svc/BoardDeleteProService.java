package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.BoardDAO;
import vo.BoardBean;

public class BoardDeleteProService {
	
	   public boolean removeArticle(int board_num, String boardtype) throws Exception{
		      // TODO Auto-generated method stub
		      
		      boolean isRemoveSuccess = false;
		      Connection con = getConnection();
		      BoardDAO boardDAO = BoardDAO.getInstance();
		      boardDAO.setConnection(con);
		      int deleteCount = boardDAO.deleteArticle(board_num, boardtype);
		      
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
	   
	   
//	   public boolean isArticleWriter(int board_num, String pass) throws Exception{
//		      // TODO Auto-generated method stub
//				
//			  boolean isDeleteSuccess = false;
//		      Connection con = getConnection();
//		      BoardDAO boardDAO = BoardDAO.getInstance();
//		      boardDAO.setConnection(con);
//		      int deleteCount = boardDAO.deleteArticle(board_num);
//		      
//		      
//		      if(deleteCount > 0){
//		         commit(con);
//		         isDeleteSuccess = true;
//		      }
//		      else{
//		         rollback(con);
//		      }
//		      
//		      close(con);
//		      return deleteCount;
//		      
//		   }
}
