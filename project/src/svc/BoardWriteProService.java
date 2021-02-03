package svc;

import java.sql.Connection;

import dao.BoardDAO;
import vo.MovieBean;

import static db.JdbcUtil.*;

public class BoardWriteProService {

   public boolean registArticle(MovieBean movieBean, String boardtype) throws Exception{
      
      boolean isWriteSuccess = false;
      Connection con = getConnection();
      BoardDAO boardDAO = BoardDAO.getInstance();
      boardDAO.setConnection(con);
      int insertCount = boardDAO.insertArticle(movieBean, boardtype);
      
      if(insertCount > 0){
         commit(con);
         isWriteSuccess = true;
      }
      else{
         rollback(con);
      }
      
      close(con);
      return isWriteSuccess;
      
   }

}