package sign;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;
import static db.JdbcUtil.*;

public class MemberDAO {
	DataSource ds;
	Connection conn;
	private Connection con;

	private static MemberDAO memberDAO;
	   
	public static MemberDAO getInstance() {
		 if(memberDAO == null){
			 memberDAO = new MemberDAO();
	      }
	      return memberDAO;
	}
	
	public void setConnection(Connection con){
	      this.con = con;
	   }
	
	public String PwFind(String id, String email)throws SQLException{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select id from user where id = ? and email = ?";
		String res = "";
		
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, email);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				res = rs.getString("id");
			}
		}finally {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(conn != null) conn.close();
		}
		return res;
	}
		

	public void updateSecondPw(String pw, String receiver_id)throws SQLException{
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "UPDATE user SET pwd = ? WHERE id = ?";
		
		try {
			conn = getConnection();
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, pw);
			ps.setString(2, receiver_id);
			ps.executeUpdate();
			System.out.println("鍮꾨�踰덊샇"  + pw);
			System.out.println("�븘�씠�뵒"  + receiver_id);
			conn.commit();
			
		}finally {
			if(ps != null) ps.close();
			if(conn != null) conn.close();
		}
	}


	

}
