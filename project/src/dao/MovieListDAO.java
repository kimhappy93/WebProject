package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static db.JdbcUtil.*;

import vo.BoardBean;
import vo.MovieList;

public class MovieListDAO {
   
   Connection con;
   
   //외부클래스에서 직접접근제한
   private static MovieListDAO movielistDAO;

   public MovieListDAO() {
      // TODO Auto-generated constructor stub
   }

   public void setConnection(Connection con) {
      this.con = con;
   }
   
   //MovieListDAO 객체를 생성해서 리턴해주는 메소드 정의
   public static MovieListDAO getInstance() {
      
      if(movielistDAO ==null) {
    	 movielistDAO = new MovieListDAO();
      }
      return movielistDAO;
   }
   
   
   
   //영화리스트 카운트
   public int selectListCount() {

	      int listCount= 0;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;

	      try{


	         System.out.println("getConnection");
	         pstmt=con.prepareStatement("select count(*) from movielist");
	         rs = pstmt.executeQuery();

	         if(rs.next()){
	            listCount=rs.getInt(1);
	         }
	      }catch(Exception ex){
	         System.out.println("getListCount 에러: " + ex);         
	      }finally{
	         close(rs);
	         close(pstmt);
	      }

	      return listCount;

	   }
   
   
   //영화리스트 조회
   public ArrayList<MovieList> selectMovieList(int page,int limit){
      
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String movie_list_sql="select * from movielist order by m_id desc limit ?,8"; //한 페이지에 영화 8개 조회
      ArrayList<MovieList> movieList = new ArrayList<MovieList>();
      int startrow=(page-1)*8; //시작 레코드의 인덱스 번호
      
      try {
         pstmt = con.prepareStatement(movie_list_sql);
         pstmt.setInt(1, startrow);
         rs = pstmt.executeQuery();
         
         movieList = new ArrayList<MovieList>();
         
         while(rs.next()) {
        	 movieList.add(new MovieList(
                  rs.getInt("m_id")
                  ,rs.getString("m_title")
                  ,rs.getString("m_poster")
                  ,rs.getString("m_date")
                  ,rs.getString("m_banner")
                  ,rs.getString("m_title_eng")
                  ,rs.getInt("m_year")
                  ,rs.getString("m_nation")
                  ,rs.getString("m_age")
                  ,rs.getString("m_genre")
                  ,rs.getString("m_time")
                  ,rs.getString("m_total")
                  ,rs.getString("m_director")
                  ,rs.getString("m_actor")
                  ,rs.getString("m_summary1")
                  ,rs.getString("m_summary2")
                  ,rs.getString("m_summary3")
                  ,rs.getString("m_slide1")
                  ,rs.getString("m_slide2")
                  ,rs.getString("m_slide3")
                  ,rs.getString("m_slide4")
                  ,rs.getString("m_slide5")
                  ,rs.getString("m_slide6")
                  ,rs.getString("m_video")          
                  ));
         }
      }catch(SQLException e) {
         e.printStackTrace();
      } finally {
         close(rs);
         close(pstmt);
      }
      
      return movieList;
   }
   
   //영화상세정보페이지 조회 //영화조회 조건값 -> m_id
   public MovieList selectMovieList(int m_id) {
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      MovieList movieList = null;
      
      try {
         pstmt = con.prepareStatement("select * from movielist where m_id = ?");
         pstmt.setInt(1, m_id);
         rs = pstmt.executeQuery();
         
         
         while(rs.next()) {
            movieList = new MovieList(
            		rs.getInt("m_id")
                    ,rs.getString("m_title")
                    ,rs.getString("m_poster")
                    ,rs.getString("m_date")
                    ,rs.getString("m_banner")
                    ,rs.getString("m_title_eng")
                    ,rs.getInt("m_year")
                    ,rs.getString("m_nation")
                    ,rs.getString("m_age")
                    ,rs.getString("m_genre")
                    ,rs.getString("m_time")
                    ,rs.getString("m_total")
                    ,rs.getString("m_director")
                    ,rs.getString("m_actor")
                    ,rs.getString("m_summary1")
                    ,rs.getString("m_summary2")
                    ,rs.getString("m_summary3")
                    ,rs.getString("m_slide1")
                    ,rs.getString("m_slide2")
                    ,rs.getString("m_slide3")
                    ,rs.getString("m_slide4")
                    ,rs.getString("m_slide5")
                    ,rs.getString("m_slide6")
                    ,rs.getString("m_video"));
         }
         
      }catch(SQLException e) {
         e.printStackTrace();
      } finally {
         close(rs);
         close(pstmt);
      }
      return movieList;
   }
   

   //영화등록
   public int insertMovieList(MovieList movieList) {
      
      PreparedStatement pstmt = null;
      int insertCount = 0;
      String sql = "";
      
      try {
         sql = "insert into movielist(m_title,m_poster,m_date,m_banner,m_title_eng,m_year,m_nation,m_age,m_genre,m_time,m_total,m_director,m_actor,m_summary1,m_summary2,m_summary3,m_slide1,m_slide2,m_slide3,m_slide4,m_slide5,m_slide6,m_video)"
               + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, movieList.getM_title());
         pstmt.setString(2, movieList.getM_poster());
         pstmt.setString(3, movieList.getM_date());
         pstmt.setString(4, movieList.getM_banner());
         pstmt.setString(5, movieList.getM_title_eng());
         pstmt.setInt(6, movieList.getM_year());
         pstmt.setString(7, movieList.getM_nation());
         pstmt.setString(8, movieList.getM_age());
         pstmt.setString(9, movieList.getM_genre());
         pstmt.setString(10, movieList.getM_time());
         pstmt.setString(11, movieList.getM_total());
         pstmt.setString(12, movieList.getM_director());
         pstmt.setString(13, movieList.getM_actor());
         pstmt.setString(14, movieList.getM_summary1());
         pstmt.setString(15, movieList.getM_summary2());
         pstmt.setString(16, movieList.getM_summary3());
         pstmt.setString(17, movieList.getM_slide1());
         pstmt.setString(18, movieList.getM_slide2());
         pstmt.setString(19, movieList.getM_slide3());
         pstmt.setString(20, movieList.getM_slide4());
         pstmt.setString(21, movieList.getM_slide5());
         pstmt.setString(22, movieList.getM_slide6());
         pstmt.setString(23, movieList.getM_video());
         insertCount = pstmt.executeUpdate();
         
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close(pstmt);
      }
      
      
      return insertCount;
   }
   
   
   
   

}