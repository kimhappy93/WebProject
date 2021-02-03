package dao;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.MovieList;
import vo.MovieReviewBean;

public class MovieReviewDAO {

	
	Connection con;
	   private static MovieReviewDAO moviereviewDAO;

	   public MovieReviewDAO() {
	      // TODO Auto-generated constructor stub
	   }

	   public void setConnection(Connection con) {
	      this.con = con;
	   }
	   
	   public static MovieReviewDAO getInstance() {
	      
	      if(moviereviewDAO ==null) {
	    	  moviereviewDAO = new MovieReviewDAO();
	      }
	      return moviereviewDAO;
	   }
	   
	   
	   
	   //영화 리뷰 및 평점 목록 조회
	   public ArrayList<MovieReviewBean> selectMovieReview(MovieReviewBean movieReviewBean,int m_id){
	      
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      ArrayList<MovieReviewBean> movieReview = null;
	      
	      try {
	         pstmt = con.prepareStatement("select * from moviereview where m_id = ? ");
	         pstmt.setInt(1, m_id);
	         rs = pstmt.executeQuery();
	         
	         movieReview = new ArrayList<MovieReviewBean>();
	         
	         while(rs.next()) {
	        	 movieReview.add(new MovieReviewBean(
	        		   rs.getInt("r_num")
	                  ,rs.getInt("m_id")
	                  ,rs.getInt("m_star")
	                  ,rs.getString("m_review")
	                  ,rs.getString("r_date")
	                  ,rs.getString("id")
	                  
	                  ));

	         }
	      }catch(SQLException e) {
	         e.printStackTrace();
	      } finally {
	         close(rs);
	         close(pstmt);
	      }
	      
	      return movieReview;
	   }
	   
	   //영화 리뷰 및 평점 선택
	   public MovieReviewBean selectMovieReview(int r_num) {
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      MovieReviewBean movieReview = null;
	      
	      try {
	         pstmt = con.prepareStatement("select * from moviereview where r_num = ?");
	         pstmt.setInt(1, r_num);
	         rs = pstmt.executeQuery();
	         
	         
	         while(rs.next()) {
	        	 movieReview = new MovieReviewBean(
	        			 rs.getInt("r_num")
		                  ,rs.getInt("m_id")
		                  ,rs.getInt("m_star")
		                  ,rs.getString("m_review")
		                  ,rs.getString("r_date")
		                  ,rs.getString("id")
	                    );
	         }
	         
	      }catch(SQLException e) {
	         e.printStackTrace();
	      } finally {
	         close(rs);
	         close(pstmt);
	      }
	      return movieReview;
	   }
	   
	   
	   //영화 리뷰 및 평점 등록
	   public int insertMovieReview(MovieReviewBean movieReviewBean) {
	      
	      PreparedStatement pstmt = null;
	      int insertCount = 0;
	      String sql = "";
	      
	      try {
	         sql = "insert into moviereview(id,m_id,m_star,m_review,r_date)"
	               + "values(?,?,?,?,now())"; //작성일시는 now()함수 사용
	         pstmt = con.prepareStatement(sql);
	         
	         pstmt.setString(1, movieReviewBean.getId());
	         pstmt.setInt(2, movieReviewBean.getM_id());
	         pstmt.setInt(3, movieReviewBean.getM_star());
	         pstmt.setString(4, movieReviewBean.getM_review());
	         
	         
	         insertCount = pstmt.executeUpdate();
	         
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         close(pstmt);
	      }
	      
	      
	      return insertCount;
	   }
	   
	   //영화 리뷰 및 평점 삭제
	   public int deleteReview(int r_num){

		      PreparedStatement pstmt = null;
		      
		      //moviereview 테이블에서 리뷰 번호를 사용한 조건문으로 리뷰 삭제하는 sql문
		      String review_delete_sql="delete from moviereview where r_num = ?"; 
		      int deleteCount=0;
		      try{
		         pstmt=con.prepareStatement(review_delete_sql);
		         pstmt.setInt(1, r_num);
		         
		         deleteCount=pstmt.executeUpdate();
		      }catch(Exception ex){
		         System.out.println("reviewDelete 에러 : "+ex);
		      }   finally{
		         close(pstmt);
		      }

		      return deleteCount;
	   }   
}
