package svc;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.SQLException;

import dao.SearchDao;


public class SearchService {
	private static SearchService  instance = new SearchService ();
	public static SearchService  getInstance() {
		return instance;
	}

	private SearchService () {

	}

	public String  searchId(String Name, String Id) throws Exception {
	
	 Connection conn = null;
     Class.forName("com.mysql.jdbc.Driver");
     
     try {
            String jdbcDriver = "jdbc:mysql://localhost:3306/moviedb?" + "useUnicode=true&characterEncoding=euckr";
            String dbUser = "root";
            String dbPass = "1234";

            conn = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);
            //DAO 객체를 생성 시 Connection 전달 
            SearchDao SearchDao = new SearchDao(conn);
            String userId = SearchDao.searchId(Name, Id);
            //System.out.println(name);
            
            return (userId);
     } 

     finally {
    	 if (conn != null) try { conn.close(); } catch(SQLException ex) {};
     }
	}

	public String  searchPwd(String Id, String Email) throws Exception {
		Connection conn = null;
		Class.forName("com.mysql.jdbc.Driver");

		try {
				String jdbcDriver = "jdbc:mysql://localhost:3306/moviedb?" + "useUnicode=true&characterEncoding=euckr";
				String dbUser = "root";
				String dbPass = "1234";

				conn = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);
				//DAO 객체를 생성 시 Connection 전달 
				SearchDao SearchDao = new SearchDao(conn);
				String userPwd = SearchDao.searchPwd(Id, Email);
				//System.out.println(name);
				return (userPwd);
		} 

		finally {
			if (conn != null) try { conn.close(); } catch(SQLException ex) {};
		}
	}

}
