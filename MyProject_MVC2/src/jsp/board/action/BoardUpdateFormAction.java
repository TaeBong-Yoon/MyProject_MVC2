package jsp.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.board.model.BoardBean;
import jsp.board.model.BoardDAO;
import jsp.common.action.Action;
import jsp.common.action.ActionForward;

public class BoardUpdateFormAction implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
		
		ActionForward forward = new ActionForward();
		
		String pageNum = request.getParameter("page");
		String num = request.getParameter("num");
		int boardNum = Integer.parseInt(num);
		
		BoardDAO dao = new BoardDAO();
		BoardBean board = dao.getDetail(boardNum);
		
		request.setAttribute("board", board);
		request.setAttribute("pageNum", pageNum);
		
		forward.setRedirect(false);
		forward.setNextPath("BoardUpdateForm.bo");
		
		return forward;
	}

}
