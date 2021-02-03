package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static db.JdbcUtil.*;

import javax.sql.DataSource;

import vo.BoardBean;
import vo.FreeBean;
import vo.MovieBean;
import vo.ReplyBean;

public class BoardDAO {

   DataSource ds;
   Connection con;
   private static BoardDAO boardDAO;

   private BoardDAO() {
      // TODO Auto-generated constructor stub
   }

   public static BoardDAO getInstance(){
      if(boardDAO == null){
         boardDAO = new BoardDAO();
      }
      return boardDAO;
   }

   public void setConnection(Connection con){
      this.con = con;
   }
   
   public int selectListCount() {

      int listCount= 0;
      PreparedStatement pstmt = null;
      ResultSet rs = null;

      try{

         System.out.println("getConnection");
         //���ڵ�
         //pstmt=con.prepareStatement("select count(*) from BOARDMOVIE");
         
         //���̺� �ΰ��� �հ� ī����
         pstmt=con.prepareStatement("SELECT SUM(num) num FROM\r\n"
         		+ "(\r\n"
         		+ "SELECT COUNT(*) num FROM boardfree\r\n"
         		+ "UNION ALL\r\n"
         		+ "SELECT COUNT(*) num FROM boardmovie\r\n"
         		+ "UNION ALL\r\n"
         		+ "SELECT COUNT(*) num FROM boardnotice\r\n"
         		+ ") AS k;");
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
   
// ��� �Խ����� ����¡ ó���� ���� ListCount �޼ҵ� //
   public int selectFreeListCount() {

	      int listCount= 0;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;

	      try{

	         System.out.println("getConnection");
	         //���ڵ�
	         pstmt=con.prepareStatement("select count(*) from BOARDFREE");
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
   
// �� ��ü��ȸ
 public ArrayList<MovieBean> selectAllArticleList(int page,int limit,String boardtype){

    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String tName = "FREE";
    
//    String board_list_sql="SELECT * FROM boardfree\r\n"
//    		+ "UNION\r\n"
//    		+ "SELECT * FROM boardmovie\r\n"
//    		+ "ORDER BY free_num desc limit ?,10";
    
    String board_list_sql="SELECT B.*, @num := @num + 1 as rnum, DATE_FORMAT(FREE_DATE, '%Y-%m-%d %H:%i:%s') AS wtime from (SELECT A.* FROM (SELECT * FROM boardfree UNION all SELECT * FROM boardmovie UNION all SELECT * FROM boardnotice) A ORDER BY free_date) B ORDER BY wtime DESC limit ?,10";
    
    ArrayList<MovieBean> articleAllList = new ArrayList<MovieBean>();
    MovieBean movieboard = null;
    int startrow=(page-1)*10;
 
    try{
       pstmt = con.prepareStatement(board_list_sql);
       pstmt.setInt(1, startrow);
       rs = pstmt.executeQuery();

       while(rs.next()){
      	movieboard = new MovieBean();
      	movieboard.setMOVIE_NUM(rs.getInt(tName + "_NUM"));
      	movieboard.setMOVIE_GENRE(rs.getString(tName + "_GENRE"));
      	movieboard.setMOVIE_SUBJECT(rs.getString(tName + "_SUBJECT"));
      	movieboard.setMOVIE_CONTENT(rs.getString(tName + "_CONTENT"));
      	movieboard.setMOVIE_FILE(rs.getString(tName + "_FILE"));
      	movieboard.setID(rs.getString("ID"));
      	movieboard.setMOVIE_DATE(rs.getDate(tName + "_DATE"));
      	movieboard.setMOVIE_READCOUNT(rs.getInt(tName + "_READCOUNT"));
      	

      	articleAllList.add(movieboard);
      	
      	//����׽�Ʈ
      	//System.out.println(rs.getInt(tName + "_NUM") + " : " + rs.getString(tName + "_GENRE") + " : " + rs.getString(tName + "_SUBJECT") );
       }

    }catch(Exception ex){
       System.out.println("getBoardList ���� : " + ex);
    }finally{
       close(rs);
       close(pstmt);
    }

    return articleAllList;

 }
   
//   ����ȸ
   public ArrayList<MovieBean> selectArticleList(int page,int limit,String boardtype){

      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String tName = null;
      
      if(boardtype.equalsIgnoreCase("BOARDMOVIE")) {
    	  tName = "MOVIE";
      } else if(boardtype.equalsIgnoreCase("BOARDFREE")) {
    	  tName = "FREE";
      } else if(boardtype.equalsIgnoreCase("BOARDNOTICE")) {
    	  tName = "NOTICE";
      } 
     
     
      String board_list_sql="select * from " + boardtype + " order by " + tName + "_NUM desc limit ?,10";
      ArrayList<MovieBean> articleList = new ArrayList<MovieBean>();
      MovieBean movieboard = null;
      int startrow=(page-1)*10; 
      
      
      
//      String tName = null;
//      
//      if(boardtype.equalsIgnoreCase("BOARDMOVIE")) {
//    	  tName = "MOVIE";
//      } else if(boardtype.equalsIgnoreCase("BOARDFREE")) {
//    	  tName = "FREE";
//      }

      try{
         pstmt = con.prepareStatement(board_list_sql);
         pstmt.setInt(1, startrow);
         rs = pstmt.executeQuery();

         while(rs.next()){
        	movieboard = new MovieBean();
        	movieboard.setMOVIE_NUM(rs.getInt(tName + "_NUM"));
        	movieboard.setMOVIE_GENRE(rs.getString(tName + "_GENRE"));
        	movieboard.setMOVIE_SUBJECT(rs.getString(tName + "_SUBJECT"));
        	movieboard.setMOVIE_CONTENT(rs.getString(tName + "_CONTENT"));
        	movieboard.setMOVIE_FILE(rs.getString(tName + "_FILE"));
        	movieboard.setID(rs.getString("ID"));
        	movieboard.setMOVIE_DATE(rs.getDate(tName + "_DATE"));
        	movieboard.setMOVIE_READCOUNT(rs.getInt(tName + "_READCOUNT"));
        	
        	
//        	movieboard.setMOVIE_NUM(rs.getInt("MOVIE_NUM"));
//        	movieboard.setMOVIE_GENRE(rs.getString("MOVIE_GENRE"));
//        	movieboard.setMOVIE_SUBJECT(rs.getString("MOVIE_SUBJECT"));
//        	movieboard.setMOVIE_CONTENT(rs.getString("MOVIE_CONTENT"));
//        	movieboard.setMOVIE_FILE(rs.getString("MOVIE_FILE"));
//        	movieboard.setID(rs.getString("ID"));
//        	movieboard.setMOVIE_DATE(rs.getDate("MOVIE_DATE"));
        	articleList.add(movieboard);
         }

      }catch(Exception ex){
         System.out.println("getBoardList ���� : " + ex);
      }finally{
         close(rs);
         close(pstmt);
      }

      return articleList;

   }
   
   
// ��� �߰�
   public int insertReplyArticle(ReplyBean replybean, String boardtype){

		PreparedStatement pstmt = null;
		ResultSet rs = null;	
		String sql="";
		int insertCount = 0;
		int num = 0;
		String tName = null;
		String tType = null;
	    
		try{
			//
			if(boardtype.equalsIgnoreCase("MOVIE")) {
			     tName = "MOVIE";
			     tType = "M";
			} else if(boardtype.equalsIgnoreCase("FREE")) {
			     tName = "FREE";
			     tType = "F";
			}
			
//			if(boardtype.equalsIgnoreCase("MOVIE")) {
//				tType = "M";
//			} else if(boardtype.equalsIgnoreCase("FREE")) {
//				tType = "F";
//			}	
			
			pstmt=con.prepareStatement("select max(" +tType+ "_REPLY_NUM) from BOARD" +tName+ "REPLY");
			rs = pstmt.executeQuery();

	        if(rs.next())
	           num =rs.getInt(1)+1;
	        else
	           num=1;			
	        
	        //

			sql="insert into BOARD" +tName+ "REPLY(" +tType+ "_REPLY_NUM, " +tType+ "_REPLY_REF_NUM, " +tType+ "_CONTENT, ID, "+tType+ "_REPLY_DATE)";
			sql+="values(?,?,?,?,now())";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setInt(2, replybean.getM_reply_ref_num());
			pstmt.setString(3, replybean.getM_content());
			pstmt.setString(4, replybean.getId());
			
			insertCount = pstmt.executeUpdate();
			
		}catch(SQLException ex){
			System.out.println("Replyinsert error: " + ex);
		}finally{
			close(rs);
			close(pstmt);
		}

		return insertCount;

	}
   
//   ��� ��ȸ
   public ArrayList<ReplyBean> selectReplyArticle(ReplyBean replybean,int board_num,String boardtype){

		PreparedStatement pstmt = null;
		ResultSet rs = null;	
		String sql="";
		ArrayList<ReplyBean> reply = new ArrayList<ReplyBean>();
		
		String tName = null;
		String tType = null;
		
		try{
			////////////////
			// ��� ��ü ��ȸ //
			if(boardtype.equalsIgnoreCase("MOVIE")) {
			     tName = "MOVIE";
			} else if(boardtype.equalsIgnoreCase("FREE")) {
			     tName = "FREE";
			}
			
			if(boardtype.equalsIgnoreCase("MOVIE")) {
				tType = "M";
			} else if(boardtype.equalsIgnoreCase("FREE")) {
				tType = "F";
			}	
			
			sql="select * from BOARD" +tName+ "REPLY where " +tType+ "_REPLY_REF_NUM = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,board_num);
			rs = pstmt.executeQuery();
			
			 while(rs.next()){
		        	ReplyBean replyListBean = new ReplyBean();
				 
		        	replyListBean.setM_reply_num(rs.getInt(tType+ "_REPLY_NUM"));
		        	replyListBean.setM_reply_ref_num(rs.getInt(tType+ "_REPLY_REF_NUM"));
		        	replyListBean.setM_content(rs.getString(tType+ "_CONTENT"));
		        	replyListBean.setId(rs.getString("ID"));
		        	replyListBean.setM_reply_date(rs.getDate(tType+ "_REPLY_DATE"));

		        	reply.add(replyListBean);
		         }
			
		}catch(SQLException ex){
			System.out.println("boardReply ���� : "+ex);
		}finally{
			close(rs);
			close(pstmt);
		}

		return reply;

	}
   
   
//   ��� ����
   public int deleteReplyArticle(int reply_num, String boardtype){
	   
		   String tName = null;
		   String tType = null;
	
	
		   if(boardtype.equalsIgnoreCase("MOVIE")) {
			   tName = "MOVIE";
		   } else if(boardtype.equalsIgnoreCase("FREE")) {
			   tName = "FREE";
		   }
	
		   if(boardtype.equalsIgnoreCase("MOVIE")) {
			   tType = "M";
		   } else if(boardtype.equalsIgnoreCase("FREE")) {
			   tType = "F";
		   }	

	      PreparedStatement pstmt = null;
	      String board_delete_sql="delete from BOARD" +tName+ "REPLY where " +tType+ "_REPLY_NUM=?";
	      int deleteCount=0;

	      try{
	         pstmt=con.prepareStatement(board_delete_sql);
	         pstmt.setInt(1, reply_num);
	         deleteCount=pstmt.executeUpdate();
	      }catch(Exception ex){
	         System.out.println("boardDelete error : "+ex);
	      }   finally{
	         close(pstmt);
	      }

	      return deleteCount;

	   }
   
//   �۾���
   public int insertArticle(MovieBean article, String boardtype){

      PreparedStatement pstmt = null;
      ResultSet rs = null;
      int num =0;
      String sql="";
      int insertCount=0;
      
      try{
    	  
    	  //���ڵ�
         // pstmt=con.prepareStatement("select max(MOVIE_NUM) from boardmovie");
    	  pstmt=con.prepareStatement("select max(" +boardtype+ "_NUM) from BOARD" +boardtype);
    	 
    	 rs = pstmt.executeQuery();
         if(rs.next())
            num =rs.getInt(1)+1;
         else
            num=1;

         //���ڵ�
//         sql="insert into boardmovie(MOVIE_NUM, MOVIE_GENRE,";
//         sql+="MOVIE_SUBJECT, MOVIE_CONTENT, MOVIE_FILE, ID, MOVIE_DATE, MOVIE_READCOUNT)"+
//               "values(?,?,?,?,?,?,now(),?)";
         
         sql="insert into board"+boardtype+"("+boardtype+"_NUM, "+boardtype+"_GENRE,";
         sql+=boardtype+"_SUBJECT, " +boardtype+"_CONTENT, " +boardtype+"_FILE, ID, " +boardtype+"_DATE, " +boardtype+"_READCOUNT)"+
               "values(?,?,?,?,?,?,now(),?)";

         pstmt = con.prepareStatement(sql);
         pstmt.setInt(1, num);
         pstmt.setString(2, article.getMOVIE_GENRE());
         pstmt.setString(3, article.getMOVIE_SUBJECT());
         pstmt.setString(4, article.getMOVIE_CONTENT());
         pstmt.setString(5, article.getMOVIE_FILE());
         pstmt.setString(6, article.getID());
         pstmt.setInt(7, 0);

         insertCount = pstmt.executeUpdate();

      }catch(Exception ex){
         System.out.println("boardInsert error : "+ex);
      }finally{
         close(rs);
         close(pstmt);
      }

      return insertCount;

   }
   

//   ��ȸ��
   public int updateReadCount(int board_num, String boardtype){

	      PreparedStatement pstmt = null;
	      int updateCount = 0;
	      String sql="update BOARD" +boardtype+ " set " +boardtype+ "_READCOUNT = "+
	    		 boardtype + "_READCOUNT+1 where " +boardtype+ "_NUM  = " + board_num;

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
   
   
//   �ۺ���
   public MovieBean selectArticle(int board_num, String boardtype){

	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      MovieBean movieBean = null;
	      
	      String tName = null;
	      
		  
		    
	      try{
//	         pstmt = con.prepareStatement(
//	               "select * from BOARDMOVIE where MOVIE_NUM = ?");
	    	  
	    	 pstmt = con.prepareStatement(
		           "select * from BOARD"+boardtype+ " where " +boardtype+"_NUM = ?");	    	  

	         pstmt.setInt(1, board_num);
	         rs= pstmt.executeQuery();
	         
	         if(boardtype.equalsIgnoreCase("MOVIE")) {
			     tName = "MOVIE";
			    } else if(boardtype.equalsIgnoreCase("FREE")) {
			     tName = "FREE";
			    } else if(boardtype.equalsIgnoreCase("NOTICE")) {
				     tName = "NOTICE";
				    }
	         
	         if(rs.next()){
	        	movieBean = new MovieBean();
	        	movieBean.setMOVIE_NUM(rs.getInt(tName + "_NUM"));
	        	movieBean.setMOVIE_SUBJECT(rs.getString(tName + "_SUBJECT"));
	        	movieBean.setMOVIE_CONTENT(rs.getString(tName + "_CONTENT"));
	        	movieBean.setMOVIE_FILE(rs.getString(tName + "_FILE"));
	        	movieBean.setID(rs.getString("ID"));
	        	movieBean.setMOVIE_DATE(rs.getDate(tName + "_DATE"));
	        	movieBean.setMOVIE_READCOUNT(rs.getInt(tName + "_READCOUNT"));
	         }
	      }catch(Exception ex){
	         System.out.println("getDetail ���� : " + ex);
	      }finally{
	         close(rs);
	         close(pstmt);
	      }

	      return movieBean;

	   }
   
   
   public int deleteArticle(int board_num, String boardtype){

	      PreparedStatement pstmt = null;
	      String board_delete_sql="delete from BOARD" +boardtype+ " where " +boardtype+ "_NUM=?";
	      int deleteCount=0;

	      try{
	         pstmt=con.prepareStatement(board_delete_sql);
	         pstmt.setInt(1, board_num);
	         deleteCount=pstmt.executeUpdate();
	      }catch(Exception ex){
	         System.out.println("boardDelete ���� : "+ex);
	      }   finally{
	         close(pstmt);
	      }

	      return deleteCount;

	   }
   
//   �ۼ���
   public int updateArticle(MovieBean article, String boardtype){

		int updateCount = 0;
		PreparedStatement pstmt = null;
		
		String sql="update BOARD" + boardtype + " set " +boardtype+ "_SUBJECT=?, " + boardtype + "_CONTENT=? where " +boardtype+ "_NUM=?";

		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, article.getMOVIE_SUBJECT());
			pstmt.setString(2, article.getMOVIE_CONTENT());
			pstmt.setInt(3, article.getMOVIE_NUM());
			
			updateCount = pstmt.executeUpdate();
		}catch(Exception ex){
			System.out.println("boardModify ���� : " + ex);
		}finally{
			close(pstmt);
		}

		return updateCount;

	}
   
   
//   �۾���Ȯ��
   public boolean isArticleBoardWriter(int board_num){

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String board_sql="select * from BOARDMOVIE where MOVIE_NUM=?";
		boolean isWriter = false;

		try{
			pstmt=con.prepareStatement(board_sql);
			pstmt.setInt(1, board_num);
			rs=pstmt.executeQuery();
			rs.next();
			isWriter = true;
			
		}catch(SQLException ex){
			System.out.println("isBoardWriter ���� : "+ex);
		}
		finally{
			close(pstmt);
		}

		return isWriter;

	}
   
//   public boolean searchArticle(String search){
//
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		String sql = "select MOVIE_SUBJECT from boardmovie a WHERE a.MOVIE_SUBJECT LIKE '%"+ search + "%' UNION ALL" +
//					"select FREE_SUBJECT from boardfree b  WHERE b.FREE_SUBJECT LIKE '%"+ search + "%'";
//
//		try{
//      		
//      		System.out.println(sql);
//      		
//      		rs = pstmt.executeQuery(sql);
//      		
//      		if(!rs.next()){
//      			System.out.println("해당하는 정보가 없습니다.");
//      		}else{
//      			rs.previous();
//      		}
//      		
//      		while(rs.next()){
//      			String subject = rs.getString("MOVIE_SUBJECT");
//      			String mov_id = rs.getString("ID");
//      			String date = rs.getString("MOVIE_DATE");
//      			int count = rs.getInt("MOVIE_READCOUNT");
//      			String content = rs.getString("MOVIE_CONTENT");
//
//      			}
//			} catch(SQLException ex){
//				System.out.println(" searchArticle error : "+ ex);
//          	}
//   }
}