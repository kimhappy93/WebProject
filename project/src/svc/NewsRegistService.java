package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.NewsListDAO;
import vo.NewsBean;

public class NewsRegistService {

	public boolean registNews(NewsBean newsBean) {
	      
		  NewsListDAO newsListDAO = NewsListDAO.getInstance();
	      Connection con = getConnection();
	      newsListDAO.setConnection(con);
		
	      
	      int insertNews = newsListDAO.insertNews(newsBean);
	      
	      boolean isRegistSucess = false;
	      
	      if(insertNews > 0) {
	         commit(con);
	         isRegistSucess = true;
	      }else {
	         rollback(con);
	      }
	      
	      close(con);
	   
	      return isRegistSucess;
	   }
}
