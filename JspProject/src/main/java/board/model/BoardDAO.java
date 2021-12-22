package board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {

	private static BoardDAO instance = null;
	
	public BoardDAO() {}
	
	public static BoardDAO getInstance() { // 연결
		if(instance == null) {
			synchronized (BoardDAO.class) {
				instance = new BoardDAO();
			}
		}
		return instance;
	}
	
	// 이곳에 게시판 작업의 기능들을 하나하나 추가하는 메소드를 작성
	
	
	// 전체 글의 개수를 가져올 메소드 구현
	public int getArticleCount() {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int x = 0;
		
		try {
			con = ConnUtil.getConnection();
			pstmt = con.prepareStatement("select count(*) from board");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				x = rs.getInt(1);
			}
		}catch(Exception e) {
			System.out.println("Exception "+e);
		}finally {
			if(rs != null) try {rs.close();}catch(SQLException s1) {}
			if(pstmt != null) try {pstmt.close();}catch(SQLException s2) {}
			if(con != null) try {con.close();}catch(SQLException s3) {}
		}
		return x;
	}
	
	// board table에서 가져올 메소드 구현 (List로 구현)
	// 검색할 내용을 리스트로 받아옴(what:검색 조건, content:검색 내용, start:시작번호,end:끝번호)
	public List<BoardVO> getArticles(int start, int end) {// 수정1
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardVO> articleList = null;
		
		try {
			con = ConnUtil.getConnection();
			
			// 수정2
			//pstmt = con.prepareStatement("select * from board order by num desc");
			pstmt = con.prepareStatement(
					"select * from (select rownum rnum, num, writer, email, subject, pass, "
							+ "regdate, readcount, ref, step, depth, content, ip from "
							+ "(select * from board order by ref desc, step asc)) "
							+ "where rnum >= ? and rnum <= ?");
			
			// 수정3
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
            rs = pstmt.executeQuery();
			
			if(rs.next()) {
				articleList = new ArrayList<BoardVO>(end - start+1); // 수정4
				do {
					BoardVO article = new BoardVO();
					article.setNum(rs.getInt("num"));
					article.setWriter(rs.getString("writer"));
					article.setEmail(rs.getString("email"));
					article.setSubject(rs.getString("subject"));
					article.setPass(rs.getString("pass"));
					article.setRegdate(rs.getTimestamp("regdate"));
					article.setReadcount(rs.getInt("readcount"));
					article.setRef(rs.getInt("ref"));
					article.setStep(rs.getInt("step"));
					article.setDepth(rs.getInt("depth"));
					article.setContent(rs.getString("content"));
					article.setIp(rs.getString("ip"));
					articleList.add(article);
				}while(rs.next());
			}
			
		}catch(Exception e) {
			System.out.println("Exception "+e);
		}finally {
			if(rs != null) try {rs.close();}catch(SQLException s1) {}
			if(pstmt != null) try {pstmt.close();}catch(SQLException s2) {}
			if(con != null) try {con.close();}catch(SQLException s3) {}
		}
		return articleList;
	}
	
	
}
