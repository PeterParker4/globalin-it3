package memberone;

import java.sql.*;
import java.util.*;

import javax.sql.*;
import javax.naming.*;

import jdbc.tempMemberVO;

public class StudentDAO {

	// DB ���� �Լ�
	private Connection getConnection() {
		Connection con = null;
		try {
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/mydb");
			con = ds.getConnection();
		}catch(Exception e) {
			System.out.println("Connection ���� ���� ~~~");
		}
		return con;
	}
	
	// ���̵� üũ �Լ�
	public boolean idCheck(String id) {
		boolean result = true;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// DB ����
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
	
	// �����ȣ�� �����ͺ��̽����� �˻��ؼ� Vector�� ��Ƽ� 
	// �������ִ� ����� DAO�� �߰��Ѵ�.
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
				vecList.addElement(tempZipcode); // ���Ϳ� �߰�
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
	
	// DB���� id/password�� ���Ͽ� �� ����� ���������� �������ִ� �޼ҵ带 ������
	// (1:�α��� ����, 0:��й�ȣ ����, -1:���̵� �������� ����)
	public int loginCheck(String id, String pass) {
		
		// Ŀ�ؼ�, �������, �������� ����
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
	
	/* ���������� Ŭ���ϸ� ���� �α����� ȸ���� ������ ������ �� �ֵ��� �̸� ȭ�鿡 �����־�� ��
	 * 
	 * ���ǿ� ����� ���̵� ������ ȸ�� ������ ���� �޼ҵ尡 �ʿ���
	 * 
	 * StudentDAO�� ���̵� ������ ȸ�������� ���� �޼ҵ带 �߰��ϸ� ��
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
			
			if(rs.next()) {// ���̵� �ش��ϴ� ȸ���� �����Ѵٸ�
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
	
	// �������� ��ư�� Ŭ���� �����ͺ��̽��� update�� �����ؾ� ��
	// ���������� ó���� �� �޼ҵ� �߰�
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
	
	// Ż�� ��ư�� Ŭ���ϸ� ������ �����ͺ��̽����� ȸ�������Ͱ� �����Ǿ�� ��
	// DB���� ȸ�������� ó������ �޼ҵ� �߰�
	public int deleteMember(String id, String pass) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String dbPass = ""; // ���� �����ͺ��̽��� ����� ��й�ȣ
		
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
					result = 1; // ȸ��Ż�� ����
				}else result = 0; // ��й�ȣ ����
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


















