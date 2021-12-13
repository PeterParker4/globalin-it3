package logon;

import java.sql.*;
import javax.sql.*;
import javax.naming.*;

public class LogonDBBean {
	
	private static LogonDBBean instance = new LogonDBBean();
	
	public LogonDBBean() {	}

	public static LogonDBBean getInstance() {
		return instance;
	}

	private Connection getConnection() throws Exception{
		
//		InitialContext ctx = new InitialContext();
		Context initCtx = new InitialContext();
		Context envCtx = (Context)initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/mydb");
//		DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/mydb");
		
		return ds.getConnection();
	}
	
	public int userCheck(String id, String passwd) throws Exception{
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = -1;
		
		String dbpasswd = "";
		try {
			con = getConnection();
			pstmt = con.prepareStatement("select passwd from tempmember where id = ?");
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dbpasswd = rs.getString("passwd");
				if(dbpasswd.equals(passwd)) 
					x = 1;
				else {
					x=0;
				}
			}else {
				x=-1;
			}
			
		}catch(Exception ex) {
			System.out.println("Exception"+ex);
		}finally {
			if(rs != null)try {rs.close();}catch(SQLException s1) {}
			if(pstmt != null)try {pstmt.close();}catch(SQLException s2) {}
			if(con != null)try {con.close();}catch(SQLException s3) {}
				
		}
		return x;
	}
}
