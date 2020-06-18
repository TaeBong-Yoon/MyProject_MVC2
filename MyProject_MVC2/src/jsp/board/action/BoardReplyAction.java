package jsp.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.board.model.BoardBean;
import jsp.board.model.BoardDAO;
import jsp.common.action.Action;
import jsp.common.action.ActionForward;

// 댓글 작성 처리 Action
public class BoardReplyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("UTF-8");
		ActionForward forward = new ActionForward();

		BoardDAO dao = new BoardDAO();
		BoardBean boardData = new BoardBean();

		// 작성후 원래 페이지로 돌아가기 위한 페이지번호
		String pageNum = request.getParameter("page");

		String id = request.getParameter("board_id");
		String subject = request.getParameter("board_subject");
		String content = request.getParameter("board_content");
		int ref = Integer.parseInt(request.getParameter("board_re_ref"));
		int lev = Integer.parseInt(request.getParameter("board_re_lev"));
		int seq = Integer.parseInt(request.getParameter("board_re_seq"));
		
		// 최신 답글이 위로 올라가도록 처리
		boardData.setBoard_re_ref(ref);
		boardData.setBoard_re_seq(seq);
		dao.updateReSeq(boardData);

		// 댓글 저장
		boardData.setBoard_id(id);
		boardData.setBoard_subject(subject);
		boardData.setBoard_content(content);
		boardData.setBoard_re_ref(ref);
		boardData.setBoard_re_lev(lev + 1);
		boardData.setBoard_re_seq(seq + 1);

		boolean result = dao.boardInsert(boardData);

		if (result) {
			forward.setRedirect(false);
			// 원래 페이지로 돌아가기 위함
			forward.setNextPath("BoardListAction.bo?page=" + pageNum);
		}
		return forward;

	}

}
