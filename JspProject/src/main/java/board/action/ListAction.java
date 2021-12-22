package board.action;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardDAO;
import board.model.BoardVO;

public class ListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String pageNum = request.getParameter("pageNum"); // 페이지 번호
		
		if(pageNum == null) {
			pageNum = "1";
		}
		
		int pageSize = 5; // 한 페이지의 글의 개수
		int currentPage = Integer.parseInt(pageNum);
		
		// 한 페이지의 시작 글 번호
		int startRow = (currentPage - 1) * pageSize + 1;
		
		// 한 페이지의 마지막 글 번호
		int endRow = currentPage * pageSize;
		
		int count = 0;
		int number = 0;
		
		List<BoardVO> articleList = null;
		
		// 데이터베이스 연동
		BoardDAO dbPro = BoardDAO.getInstance();
		
		count = dbPro.getArticleCount();
		
		if(count > 0) {// 현재 페이지에 해당하는 글 목록
			articleList = dbPro.getArticles(startRow, endRow);
		}else {
			articleList = Collections.emptyList();
		}
		
		// 글 목록에 표시할 글번호를 의미함
		number = count - (currentPage - 1) * pageSize;
		
		// 해당 뷰에서 사용할 속성 저장(list.jsp)
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("startRow", startRow);
		request.setAttribute("endRow", endRow);
		request.setAttribute("count", count);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("number", number);
		request.setAttribute("articleList", articleList);
		
		return "/board/list.jsp";
	}
	
}
