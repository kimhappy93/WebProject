package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.SQLException;



public class SearchDao {

  private Connection conn;

     

     public SearchDao(Connection conn){

         this.conn = conn;

      }

     

     public String searchId(String Name, String Email) throws SQLException {

      PreparedStatement pstmt = null;

         ResultSet rs = null;

   try {

    pstmt = conn.prepareStatement( 

      "select id from user " +

      "where name = ? and email = ? ");

    pstmt.setString(1, Name);

       pstmt.setString(2, Email);

    rs = pstmt.executeQuery();

             if( rs.next() )

               return (rs.getString("id"));

              else

                 return null;

   } finally {

    if(rs!=null)try { rs.close(); } catch(SQLException ex) {}

    if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}

   }



   

  }

     public String searchPwd(String Id, String Email) throws SQLException {

      PreparedStatement pstmt = null;

         ResultSet rs = null;

   try {

    pstmt = conn.prepareStatement( 

      "select pwd from user " +

      "where id = ? and Email = ? ");

    pstmt.setString(1, Id);

       pstmt.setString(2, Email);

    rs = pstmt.executeQuery();

             if( rs.next() )

               return (rs.getString("pwd"));

              else

                 return null;

   } finally {

    if(rs!=null)try { rs.close(); } catch(SQLException ex) {}

    if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}

   }



  }



}
