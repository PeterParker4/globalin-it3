package memberone;

import java.sql.*;
import java.util.*;

import javax.sql.*;
import javax.naming.*;

import jdbc.tempMemberVO;

public class StudentDAO {

	// DB 연결 함수
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
	
	// 아이디 체크 함수
	public boolean idCheck(String id) {
		boolean result = true;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// DB 연결
			con = getConnection();
			pstmt = con.prepareStatement("select * from student where id=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(!rs.next()) result = false;
		}catch(SQLException se) {
			se.printStackTrace();
		}finally {
			if(rs != null) try {rs.close();}catch(SQLException s1) {}
			if(pstmt != null) try {pstmt.close();}catch(SQLException s1) {}
			if(con != null) try {con.close();}catch(SQLException s1) {}
		}
		return result;
	}
	
	// 우편번호를 데이터베이스에서 검색해서 Vector에 담아서 
	// 리턴해주는 기능을 DAO에 추가한다.
	public Vector<ZipcodeVO> zipcodeRead(String dong) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		Vector<ZipcodeVO> vecList = new Vector<ZipcodeVO>();
		
		try {
			con = getConnection();
			
			String strQuery = "select * from zipcode where dong like '"+dong+"%'";
			pstmt = con.prepareStatement(strQuery);
			rs = pstmt.executeQuery(strQuery);
			
			while(rs.next()) {
				ZipcodeVO tempZipcode = new ZipcodeVO();
				tempZipcode.setZipcode(rs.getString("zipcode"));
				tempZipcode.setSido(rs.getString("sido"));
				tempZipcode.setGugun(rs.getString("gugun"));
				tempZipcode.setDong(rs.getString("dong"));
				tempZipcode.setRi(rs.getString("ri"));
				tempZipcode.setBunji(rs.getString("bunji"));
				vecList.addElement(tempZipcode); // 벡터에 추가
			}
		}catch(SQLException sq) {
			sq.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try { if(rs != null) rs.close(); }catch(SQLException s) {}
			try { if(pstmt != null) pstmt.close(); }catch(SQLException s) {}
			try { if(con != null) con.close(); }catch(SQLException s) {}
		}
		return vecList;
	}
	
	public boolean memberInsert(StudentVO vo) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean flag = false;
		
		try {
			con = getConnection();
			String strQuery = "insert into student values(?,?,?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(strQuery);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPass());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getPhone1());
			pstmt.setString(5, vo.getPhone2());
			pstmt.setString(6, vo.getPhone3());
			pstmt.setString(7, vo.getEmail());
			pstmt.setString(8, vo.getZipcode());
			pstmt.setString(9, vo.getAddress1());
			pstmt.setString(10, vo.getAddress2());
			
			int count = pstmt.executeUpdate();
			if(count > 0) flag = true;
			
		}catch(Exception e) {
			System.out.println("Exception "+e);
		}finally {
			if(rs != null) try {rs.close();}catch(SQLException s1) {}
			if(pstmt != null) try {pstmt.close();}catch(SQLException s2) {}
			if(con != null) try {con.close();}catch(SQLException s3) {}
		}
		return flag;
	}
	
	// DB에서 id/password를 비교하여 그 결과를 정수형으로 리턴해주는 메소드를 구현함
	// (1:로그인 성공, 0:비밀번호 오류, -1:아이디 존재하지 않음)
	public int loginCheck(String id, String pass) {
		
		// 커넥션, 결과집합, 동적쿼리 생성
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int check = -1;
		
		try {
			con = getConnection();
			String strQuery = "select pass from student where id=?";
			pstmt = con.prepareStatement(strQuery);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String dbPass = rs.getString("pass");
				
				if(pass.equals(dbPass)) check = 1;
				else check = 0;
			}
		}catch(Exception e) {
			System.out.println("Exception "+e);
		}finally {
			if(rs != null) try {rs.close();}catch(SQLException s1) {}
			if(pstmt != null) try {pstmt.close();}catch(SQLException s2) {}
			if(con != null) try {con.close();}catch(SQLException s3) {}
		}
		return check;
	}
	
	/* 정보수정을 클릭하면 현재 로그인한 회원의 정보를 수정할 수 있도록 미리 화면에 보여주어야 함
	 * 
	 * 세션에 저장된 아이디를 가지고 회원 정보를 얻어올 메소드가 필요함
	 * 
	 * StudentDAO에 아이디를 가지고 회원정보를 얻어올 메소드를 추가하면 됨
	 */
	
	public StudentVO getMember(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StudentVO vo = null;
		
		try {
			con = getConnection();
			String strQuery = "select * from student where id=?";
			pstmt = con.prepareStatement(strQuery);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {// 아이디에 해당하는 회원이 존재한다면
				vo = new StudentVO();
				vo.setId(rs.getString("id"));
				vo.setPass(rs.getString("pass"));
				vo.setName(rs.getString("name"));
				vo.setPhone1(rs.getString("phone1"));
				vo.setPhone2(rs.getString("phone2"));
				vo.setPhone3(rs.getString("phone3"));
				vo.setEmail(rs.getString("email"));
				vo.setZipcode(rs.getString("zipcode"));
				vo.setAddress1(rs.getString("address1"));
				vo.setAddress2(rs.getString("address2"));
			}
		}catch(Exception e) {
			System.out.println("Exception "+e);
		}finally {
			if(rs != null) try {rs.close();}catch(SQLException s1) {}
			if(pstmt != null) try {pstmt.close();}catch(SQLException s2) {}
			if(con != null) try {con.close();}catch(SQLException s3) {}
		}
		return vo;
	}
	
	// 정보수정 버튼을 클릭시 데이터베이스에 update를 수행해야 함
	// 정보수정을 처리해 줄 메소드 추가
	public void updateMember(StudentVO vo) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = getConnection();
			String strQuery = "update student set pass=?, phone1=?, phone2=?, phone3=?, email=?, zipcode=?, address1=?, address2=? where id=?";
			pstmt = con.prepareStatement(strQuery);
			pstmt.setString(1, vo.getPass());
			pstmt.setString(2, vo.getPhone1());
			pstmt.setString(3, vo.getPhone2());
			pstmt.setString(4, vo.getPhone3());
			pstmt.setString(5, vo.getEmail());
			pstmt.setString(6, vo.getZipcode());
			pstmt.setString(7, vo.getAddress1());
			pstmt.setString(8, vo.getAddress2());
			pstmt.setString(9, vo.getId());
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("Exception "+e);
		}finally {
			if(pstmt != null) try {pstmt.close();}catch(SQLException s1) {}
			if(con != null) try {con.close();}catch(SQLException s2) {}
		}
	}
	
	// 탈퇴 버튼을 클릭하면 실제로 데이터베이스에서 회원데이터가 삭제되어야 함
	// DB에서 회원삭제를 처리해줄 메소드 추가
	public int deleteMember(String id, String pass) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String dbPass = ""; // 실제 데이터베이스에 저장된 비밀번호
		
		int result = -1;
		
		try {
			con = getConnection();
			String strQuery = "select pass from student where id=?";
			pstmt = con.prepareStatement(strQuery);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dbPass = rs.getString("pass");
				
				if(dbPass.equals(pass)) {
					pstmt = con.prepareStatement("delete from student where id=?");
					pstmt.setString(1, id);
					pstmt.executeUpdate();
					result = 1; // 회원탈퇴 성공
				}else result = 0; // 비밀번호 오류
			}
		}catch(Exception e) {
			System.out.println("Exception "+e);
		}finally {
			if(rs != null) try {rs.close();}catch(SQLException s1) {}
			if(pstmt != null) try {pstmt.close();}catch(SQLException s2) {}
			if(con != null) try {con.close();}catch(SQLException s3) {}
		}
		return result;
	}
	
}


















