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
		
		String pageNum = request.getParameter("pageNum"); // ������ ��ȣ
		
		if(pageNum == null) {
			pageNum = "1";
		}
		
		int pageSize = 5; // �� �������� ���� ����
		int currentPage = Integer.parseInt(pageNum);
		
		// �� �������� ���� �� ��ȣ
		int startRow = (currentPage - 1) * pageSize + 1;
		
		// �� �������� ������ �� ��ȣ
		int endRow = currentPage * pageSize;
		
		int count = 0;
		int number = 0;
		
		List<BoardVO> articleList = null;
		
		// �����ͺ��̽� ����
		BoardDAO dbPro = BoardDAO.getInstance();
		
		count = dbPro.getArticleCount();
		
		if(count > 0) {// ���� �������� �ش��ϴ� �� ���
			articleList = dbPro.getArticles(startRow, endRow);
		}else {
			articleList = Collections.emptyList();
		}
		
		// �� ��Ͽ� ǥ���� �۹�ȣ�� �ǹ���
		number = count - (currentPage - 1) * pageSize;
		
		// �ش� �信�� ����� �Ӽ� ����(list.jsp)
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
