package board.model;

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
	
	
	
}
