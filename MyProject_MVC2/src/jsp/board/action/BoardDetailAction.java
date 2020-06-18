package jsp.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.board.model.BoardBean;
import jsp.board.model.BoardDAO;
import jsp.common.action.Action;
import jsp.common.action.ActionForward;

// 상세보기와 조회수를 올려주는 Action
public class BoardDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = new ActionForward();

		// 파라미터로 넘어온 boardNum
		String num = request.getParameter("num");
		int boardNum = Integer.parseInt(num);

		String pageNum = request.getParameter("pageNum");

		BoardDAO dao = new BoardDAO();
		BoardBean board = dao.getDetail(boardNum);
		boolean result = dao.updateCount(boardNum);

		request.setAttribute("board", board);
		request.setAttribute("pageNum", pageNum);

		if (result) {
			forward.setRedirect(false);
			forward.setNextPath("BoardDetailForm.bo");
		}
		return forward;
	}

}
