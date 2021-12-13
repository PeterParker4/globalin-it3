package jdbc;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import jdbc.tempMemberVO;

public class tempMemberDAO {
	
	private final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	private final String USER = "scott";
	private final String PASS = "tiger";
	
	public tempMemberDAO() {
		
		try {
			Class.forName(JDBC_DRIVER);
		}catch(Exception e) {
			System.out.println("Error : JDBC 드라이버 로딩 실패 !!!!!");
		}
	}
	
	private Connection getConnection() {
		Connection con = null;
		try {
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/mydb");
			con = ds.getConnection();
		}catch(Exception e) {
			System.out.println("Connection 생성 실패 ~~~");
		}
		return con;
	}
	
	
	public Vector<tempMemberVO> getMemberList() {
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		Vector<tempMemberVO> vecList = new Vector<tempMemberVO>();
		
		try {
//			con = DriverManager.getConnection(JDBC_URL,USER,PASS);
			con = getConnection();
			
			String strQuery = "select * from tempMember";
			stmt = con.createStatement();
			rs= stmt.executeQuery(strQuery);
			
			while(rs.next()) {
				tempMemberVO vo = new tempMemberVO();
				vo.setId(rs.getString("id"));
				vo.setPasswd(rs.getString("passwd"));
				vo.setName(rs.getString("name"));
				vo.setMem_num1(rs.getString("mem_num1"));
				vo.setMem_num2(rs.getString("mem_num2"));
				vo.setE_mail(rs.getString("e_mail"));
				vo.setPhone(rs.getString("phone"));
				vo.setZipcode(rs.getString("zipcode"));
				vo.setAddress(rs.getString("address"));
				vo.setJob(rs.getString("job"));
				vecList.add(vo); // 리스트에 추가
			}
		}catch(SQLException sq) {
			sq.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try { if(rs != null) rs.close(); }catch(SQLException s) {}
			try { if(stmt != null) stmt.close(); }catch(SQLException s) {}
			try { if(con != null) con.close(); }catch(SQLException s) {}
		}
		return vecList;
	}
}
