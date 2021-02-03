package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.NewsListDAO;
import vo.NewsBean;

public class NewsViewService {

	public NewsBean getNewsView(int news_num) {
			
			Connection con = getConnection();
			NewsListDAO newslistDAO = NewsListDAO.getInstance();
			newslistDAO.setConnection(con);
			
			int updateCount = newslistDAO.updateReadCount(news_num);
			
			if(updateCount>0) {
				commit(con);
			}else {
				rollback(con);
			}
			
			NewsBean newsBean =newslistDAO.selectNewsList(news_num);
			close(con);
			return newsBean;
		}
}
