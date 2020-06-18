package jsp.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.board.model.BoardBean;
import jsp.board.model.BoardDAO;
import jsp.common.action.Action;
import jsp.common.action.ActionForward;

//댓글 작성 화면으로 이동 액션
public class BoardReplyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ActionForward forward = new ActionForward();
		
		BoardDAO dao = new BoardDAO();
		
		int num = Integer.parseInt(request.getParameter("num"));
		// 댓글 작성후에 원 페이지로 돌아가기 위한 페이지 번호
		String pageNum = request.getParameter("page");
		
		BoardBean board = dao.getDetail(num);
		request.setAttribute("board", board);
		request.setAttribute("page", pageNum);
		
		forward.setRedirect(false);
		forward.setNextPath("BoardReplyForm.bo");
		
		return forward;
		
	}
	
}
