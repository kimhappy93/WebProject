package dao;

import static db.JdbcUtil.close;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import vo.NewsBean;

public class NewsListDAO {

	   Connection con;
	   private static NewsListDAO newslistDAO;

	   public NewsListDAO() {
	      // TODO Auto-generated constructor stub
	   }

	   public void setConnection(Connection con) {
	      this.con = con;
	   }
	   
	   public static NewsListDAO getInstance() {
	      
	      if(newslistDAO ==null) {
	    	  newslistDAO = new NewsListDAO();
	      }
	      return newslistDAO;
	   }
	
	   
	   public int selectListCount() {

		      int listCount= 0;
		      PreparedStatement pstmt = null;
		      ResultSet rs = null;

		      try{


		         System.out.println("getConnection");
		         pstmt=con.prepareStatement("select count(*) from news");
		         rs = pstmt.executeQuery();

		         if(rs.next()){
		            listCount=rs.getInt(1);
		         }
		      }catch(Exception ex){
		         System.out.println("getListCount ����: " + ex);         
		      }finally{
		         close(rs);
		         close(pstmt);
		      }

		      return listCount;

		   }
	   
	   
	   
	   public ArrayList<NewsBean> selectNewsList(int page,int limit){
		      
		      PreparedStatement pstmt = null;
		      ResultSet rs = null;
		      String news_sql="select * from news order by news_num desc limit ?,6";
		      ArrayList<NewsBean> newsBean = new ArrayList<NewsBean>();
		      int startrow=(page-1)*6;
		      
		      try {
		         pstmt = con.prepareStatement(news_sql);
		         pstmt.setInt(1, startrow);
		         rs = pstmt.executeQuery();
		         
		         newsBean = new ArrayList<NewsBean>();
		         
		         while(rs.next()) {
		        	 newsBean.add(new NewsBean(
		        			   rs.getInt("news_num")
			                  ,rs.getString("news_name")
			                  ,rs.getString("news_subject")
			                  ,rs.getString("img_a")
			                  ,rs.getString("content_a")
			                  ,rs.getString("img_b")
			                  ,rs.getString("content_b")
			                  ,rs.getString("img_c")
			                  ,rs.getString("content_c")
			                  ,rs.getString("img_d")
			                  ,rs.getString("content_d")
			                  ,rs.getString("img_e")
			                  ,rs.getString("content_e")
			                  ,rs.getString("img_f")
			                  ,rs.getString("content_f")
			                  ,rs.getString("news_date")
			                  ,rs.getInt("count")        
		                  ));
		         }
		      }catch(SQLException e) {
		         e.printStackTrace();
		      } finally {
		         close(rs);
		         close(pstmt);
		      }
		      
		      return newsBean;
		   }
	   
	   
	   
	
	
	   public NewsBean selectNewsList(int news_num){
		      
		      PreparedStatement pstmt = null;
		      ResultSet rs = null;
		      NewsBean newsBean = null;
		      
		      try {
		         pstmt = con.prepareStatement("select * from news where news_num = ?");
		         pstmt.setInt(1, news_num);
		         rs = pstmt.executeQuery();

		         while(rs.next()) {
		        	 newsBean = new NewsBean(
		                  rs.getInt("news_num")
		                  ,rs.getString("news_name")
		                  ,rs.getString("news_subject")
		                  ,rs.getString("img_a")
		                  ,rs.getString("content_a")
		                  ,rs.getString("img_b")
		                  ,rs.getString("content_b")
		                  ,rs.getString("img_c")
		                  ,rs.getString("content_c")
		                  ,rs.getString("img_d")
		                  ,rs.getString("content_d")
		                  ,rs.getString("img_e")
		                  ,rs.getString("content_e")
		                  ,rs.getString("img_f")
		                  ,rs.getString("content_f")
		                  ,rs.getString("news_date")
		                  ,rs.getInt("count"));
		         }
		         
		      }catch(SQLException e) {
		         e.printStackTrace();
		      } finally {
		         close(rs);
		         close(pstmt);
		      }
		      
		      return newsBean;
		   }
	   
	   
	     
	   
	public int insertNews(NewsBean newsBean) {
		 
	      PreparedStatement pstmt = null;
	      int insertCount = 0;
	      String sql = "";
	      
	      try {
	         sql = "insert into news(news_name,news_subject,img_a,content_a,img_b,content_b,img_c,content_c,img_d,content_d,img_e,content_e,img_f,content_f,news_date)"
	               + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	         pstmt = con.prepareStatement(sql);
	         pstmt.setString(1, newsBean.getNews_name());
	         pstmt.setString(2, newsBean.getNews_subject());
	         pstmt.setString(3, newsBean.getImg_a());
	         pstmt.setString(4, newsBean.getContent_a());
	         pstmt.setString(5, newsBean.getImg_b());
	         pstmt.setString(6, newsBean.getContent_b());
	         pstmt.setString(7, newsBean.getImg_c());
	         pstmt.setString(8, newsBean.getContent_c());
	         pstmt.setString(9, newsBean.getImg_d());
	         pstmt.setString(10, newsBean.getContent_d());
	         pstmt.setString(11, newsBean.getImg_e());
	         pstmt.setString(12, newsBean.getContent_e());
	         pstmt.setString(13, newsBean.getImg_f());
	         pstmt.setString(14, newsBean.getContent_f());
	         pstmt.setString(15, newsBean.getNews_date());

	         insertCount = pstmt.executeUpdate();
	         
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         close(pstmt);
	      }
	      
	      
	      return insertCount;
	   }
	
	
	
	public int updateReadCount(int news_num){

	      PreparedStatement pstmt = null;
	      int updateCount = 0;
	      String sql="update news set count = count + 1 where news_num = " + news_num;

	      try{
	         pstmt=con.prepareStatement(sql);
	         updateCount = pstmt.executeUpdate();
	      }catch(SQLException ex){
	         System.out.println("setReadCountUpdate error : "+ex);
	      }
	      finally{
	         close(pstmt);

	      }

	      return updateCount;

	   }

}