package svc;

import java.sql.Connection;
import java.util.ArrayList;

import dao.BoardDAO;
import vo.BoardBean;
import vo.MovieBean;

import static db.JdbcUtil.*;

public class BoardListService {
	
		public int getListCount() throws Exception{
	      
	      int listCount = 0;
	      Connection con = getConnection();
	      BoardDAO boardDAO = BoardDAO.getInstance();
	      boardDAO.setConnection(con);
	      listCount = boardDAO.selectListCount();
	      close(con);
	      return listCount;
	      
	   }
		
		// ��� �Խ����� ����¡ ó���� ���� ListCount �޼ҵ� //
		public int getFreeListCount() throws Exception{
		      
		      int listCount = 0;
		      Connection con = getConnection();
		      BoardDAO boardDAO = BoardDAO.getInstance();
		      boardDAO.setConnection(con);
		      listCount = boardDAO.selectFreeListCount();
		      close(con);
		      return listCount;
		      
		   }
		
//		all article list
		public ArrayList<MovieBean> getAllArticleList(int page, int limit,String boardtype) throws Exception{
		      
		      ArrayList<MovieBean> articleList = null;
		      Connection con = getConnection();
		      BoardDAO boardDAO = BoardDAO.getInstance();
		      boardDAO.setConnection(con);
		      articleList = boardDAO.selectAllArticleList(page,limit,boardtype);
		      close(con);
		      return articleList;
		      
		   }
		
//		
		public ArrayList<MovieBean> getArticleList(int page, int limit,String boardtype) throws Exception{
		      
		      ArrayList<MovieBean> articleList = null;
		      Connection con = getConnection();
		      BoardDAO boardDAO = BoardDAO.getInstance();
		      boardDAO.setConnection(con);
		      articleList = boardDAO.selectArticleList(page,limit,boardtype);
		      close(con);
		      return articleList;
		      
		   }
		
//		Notice article list
//		public ArrayList<MovieBean>getNoticeArticleList(int page, int limit,String boardtype) throws Exception{
//		      
//		      ArrayList<MovieBean> articleList = null;
//		      Connection con = getConnection();
//		      BoardDAO boardDAO = BoardDAO.getInstance();
//		      boardDAO.setConnection(con);
//		      articleList = boardDAO.selectArticleList(page,limit,boardtype);
//		      close(con);
//		      return articleList;
//		   }
}
