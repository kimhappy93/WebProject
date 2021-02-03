package project;

import static db.JdbcUtil.close;








import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.Vector;



import news.BoardBean;


public class BoardMgr {

   private DBConnectionMgr pool;
   

   public BoardMgr() {
      try {
         pool = DBConnectionMgr.getInstance();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
   
   
   //최신글 불러오기
   public Vector<MainBoardBean> getBoardList() {
		   Connection con = null;
		      PreparedStatement pstmt = null;
		      ResultSet rs = null;
		      String sql = null;
		      Vector<MainBoardBean> vlist = new Vector<MainBoardBean>();
	      
	      try {
	    	  con = pool.getConnection();
	    	  
	    		  sql = "SELECT * FROM boardmovie ORDER BY MOVIE_DATE DESC";
	    		  pstmt = con.prepareStatement(sql);
	    	  
	    	  rs = pstmt.executeQuery();
	    	  while(rs.next()) {
	    		  MainBoardBean bean = new MainBoardBean();
	    		  bean.setMOVIE_NUM(rs.getInt("MOVIE_NUM"));
	    		  bean.setMOVIE_GENRE(rs.getString("MOVIE_GENRE"));
	    		  bean.setMOVIE_SUBJECT(rs.getString("MOVIE_SUBJECT"));
	    		  bean.setMOVIE_CONTENT(rs.getString("MOVIE_CONTENT"));
	    		  bean.setMOVIE_FILE(rs.getString("MOVIE_FILE"));
	    		  bean.setMOVIE_DATE(rs.getDate("MOVIE_DATE"));
	    		  bean.setMOVIE_READCOUNT(rs.getInt("MOVIE_READCOUNT"));
	    		  bean.setID(rs.getString("ID"));
	    		  vlist.add(bean);
	    	  }
	      }catch(Exception e) {
	    	  e.printStackTrace();
	      }finally {
	    	  
	    	  close(pstmt);
	    	  close(con);
	    	  close(rs);
	      }
	      return vlist;
	   }
   
   
//   public int getTotalCount(String keyField, String keyWord) {
//      Connection con = null;
//      PreparedStatement pstmt = null;
//      ResultSet rs = null;
//      String sql = null;
//      int totalCount = 0;
//      
//      try {
//    	  con = pool.getConnection();
//    	  if(keyWord.equals("null")||keyWord.equals("")) {
//    		  sql = "select count(num) from boardmovie";
//    		  pstmt = con.prepareStatement(sql);
//    	  }else {
//    		  sql = "select count(num) from boardmovie where" + keyField + "like ?";
//    		  pstmt = con.prepareStatement(sql);
//    		  pstmt.setString(1, "%"  + keyWord + "%");
//    	  }
//    	  rs = pstmt.executeQuery();
//    	  if(rs.next()) {
//    		  totalCount = rs.getInt(1);
//    	  }
//      }catch(Exception e) {
//    	  e.printStackTrace();
//      }finally {
//    	  pool.freeConnection(con,pstmt,rs);
//      }
//      return totalCount;
//   }
   
 
   
//   // �Խù� ����
//   public MainBoardBean getBoard(int board_num) {
//      Connection con = null;
//      PreparedStatement pstmt = null;
//      ResultSet rs = null;
//      String sql = null;
//      MainBoardBean bean = new MainBoardBean();
//      try {
//         con = pool.getConnection();
//         sql = "select * from boardmovie union all select * from boardfree where num=?";
//         pstmt = con.prepareStatement(sql);
//         pstmt.setInt(1, board_num);
//         rs = pstmt.executeQuery();
//         if (rs.next()) {
//            bean.setMOVIE_SUBJECT(rs.getString("MOVIE_SUBJECT"));
//         }
//      } catch (Exception e) {
//         e.printStackTrace();
//      } finally {
//         pool.freeConnection(con, pstmt, rs);
//      }
//      return bean;
//   }
   
   
   
   //현재상영작 리스트 불러오기
   public Vector<MovieBean> getMovieList(String mField, String mWord) {
	   Connection con = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      String sql = null;
     Vector<MovieBean> mlist = new Vector<MovieBean>();
     
     try {
   	  con = pool.getConnection();
   	  if(mWord.equals("null")||mWord.equals("")) {
   		  sql = "select * from movielist order by m_id desc";
   		  pstmt = con.prepareStatement(sql);
//   		  pstmt.setInt(1, Mstart);
//   		  pstmt.setInt(2, Mend);
   	  }
   	  rs = pstmt.executeQuery();
   	  while(rs.next()) {
   		  MovieBean bean = new MovieBean();
   		  bean.setM_id(rs.getInt("m_id"));
   		  bean.setM_title(rs.getString("m_title"));
   		  bean.setM_poster(rs.getString("m_poster"));
   		  bean.setM_date(rs.getString("m_date"));
   		  mlist.add(bean);
   	  }
     }catch(Exception e) {
   	  e.printStackTrace();
     }finally {
    	 close(pstmt);
   	  close(con);
   	  close(rs);
     }
     return mlist;
  }
   
   
   
   //인기글 불러오기
   public Vector<BestBoardBean> getBestList(String BkeyField, String BkeyWord) {
		   Connection con = null;
		      PreparedStatement pstmt = null;
		      ResultSet rs = null;
		      String sql = null;
	      Vector<BestBoardBean> blist = new Vector<BestBoardBean>();
	      
	      try {
	    	  con = pool.getConnection();
	    	  if(BkeyWord.equals("null")||BkeyWord.equals("")) {
	    		  sql = "SELECT * FROM boardfree ORDER BY FREE_DATE desc";
	    		  pstmt = con.prepareStatement(sql);
//	    		  pstmt.setInt(1, Bstart);
//	    		  pstmt.setInt(2, Bend);
	    	  }
	    	  
	    	  rs = pstmt.executeQuery();
	    	  while(rs.next()) {
	    		  BestBoardBean bean = new BestBoardBean();
	    		  bean.setFREE_NUM(rs.getInt("FREE_NUM"));
	    		  bean.setFREE_GENRE(rs.getString("FREE_GENRE"));
	    		  bean.setFREE_SUBJECT(rs.getString("FREE_SUBJECT"));
	    		  bean.setFREE_CONTENT(rs.getString("FREE_CONTENT"));
	    		  bean.setFREE_FILE(rs.getString("FREE_FILE"));
	    		  bean.setID(rs.getString("ID"));
	    		  bean.setFREE_DATE(rs.getDate("FREE_DATE"));
	    		  bean.setFREE_READCOUNT(rs.getInt("FREE_READCOUNT"));
	    		  blist.add(bean);
	    	  }
	      }catch(Exception e) {
	    	  e.printStackTrace();
	      }finally {
	    	  close(pstmt);
	    	  close(con);
	    	  close(rs);
	      }
	      return blist;
	   }
   
   
   	public BestBoardBean getBest(int num) {
	      Connection con = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      String sql = null;
	      BestBoardBean bean = new BestBoardBean();
	      try {
	         con = pool.getConnection();
	         sql = "select * from boardfree union select * from boardmovie where num=?";
	         pstmt = con.prepareStatement(sql);
	         pstmt.setInt(1, num);
	         rs = pstmt.executeQuery();
	         if (rs.next()) {
	            bean.setFREE_SUBJECT(rs.getString("FREE_SUBJECT"));
	         }
	      } catch (Exception e) {
	         e.printStackTrace();
	      } finally {
	    	  close(pstmt);
	    	  close(con);
	    	  close(rs);
	      }
	      return bean;
	   }
   	
  
   	
    //뉴스 불러오기
    public Vector<BoardBean> getNewsList(String nField, String nWord) {
 	   Connection con = null;
 	      PreparedStatement pstmt = null;
 	      ResultSet rs = null;
 	      String sql = null;
      Vector<BoardBean> nlist = new Vector<BoardBean>();
      
      try {
    	  con = pool.getConnection();
    	  if(nWord.equals("null")||nWord.equals("")) {
    		  sql = "select * from news order by news_num desc";
    		  pstmt = con.prepareStatement(sql);
//    		  pstmt.setInt(1, Mstart);
//    		  pstmt.setInt(2, Mend);
    	  }
    	  rs = pstmt.executeQuery();
    	  while(rs.next()) {
    		  BoardBean bean = new BoardBean();
    		  bean.setNews_num(rs.getInt("news_num"));
    		  bean.setNews_subject(rs.getString("news_subject"));
    		  bean.setImg_a(rs.getString("img_a"));
    		  bean.setContent_a(rs.getString("content_a"));
    		  nlist.add(bean);
    	  }
      }catch(Exception e) {
    	  e.printStackTrace();
      }finally {
    	  close(pstmt);
    	  close(con);
    	  close(rs);
      }
      return nlist;
   }
}

























