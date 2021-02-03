package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Vector;

import dao.NewsListDAO;
import vo.MovieList;
import vo.NewsBean;

public class NewsListService {

	public int getListCount() throws Exception{
	      // TODO Auto-generated method stub
	      
	      int listCount = 0;
	      Connection con = getConnection();
	      NewsListDAO newsListDAO = NewsListDAO.getInstance();
	      newsListDAO.setConnection(con);
	      listCount = newsListDAO.selectListCount();
	      close(con);
	      return listCount;
	      
	   }

	
	public ArrayList<NewsBean> getNewsList(int page, int limit) throws Exception{
		
		ArrayList<NewsBean> newsBean = null;
		NewsListDAO newsListDAO = NewsListDAO.getInstance();
		Connection con = getConnection();
		newsListDAO.setConnection(con);
		newsBean = newsListDAO.selectNewsList(page,limit);
		return newsBean;
	}
}
