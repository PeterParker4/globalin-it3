package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardDAO;
import board.model.BoardVO;

// �� �󼼺��� ó��
public class ContentAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		// �ش� �� ��ȣ
		int num = Integer.parseInt(request.getParameter("num"));
		
		// �ش� ������ ��ȣ
		String pageNum = request.getParameter("pageNum");
		
		BoardDAO dbPro = BoardDAO.getInstance();
		
		BoardVO article = dbPro.getArticle(num);
		
		// �ش� �信�� ����� �Ӽ� ����
		request.setAttribute("num", num);
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("article", article);
		
		return "/board/content.jsp";
	}

}
