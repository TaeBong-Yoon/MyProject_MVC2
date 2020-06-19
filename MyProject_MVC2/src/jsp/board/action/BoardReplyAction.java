package jsp.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.board.model.BoardBean;
import jsp.board.model.BoardDAO;
import jsp.board.model.ReplyBean;
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
		ReplyBean replyData = new ReplyBean();
		
		// 작성후 원래 페이지로 돌아가기 위한 페이지번호
		String pageNum = request.getParameter("page");

		int num = Integer.parseInt(request.getParameter("board_num"));
		String id = request.getParameter("board_id");
		String subject = request.getParameter("board_subject");
		String content = request.getParameter("board_content");
		int ref = Integer.parseInt(request.getParameter("board_re_ref"));
		
		replyData.setReply_ident_num(num);
		replyData.setReply_id(id);
		replyData.setReply_content(content);
		
		
		// 댓글 저장
		boardData.setBoard_id(id);
		boardData.setBoard_subject(subject);
		boardData.setBoard_content(content);
		boardData.setBoard_re_ref(ref);
		boardData.setBoard_parent(num);
		
		boolean result = dao.boardInsert(boardData);

		if (result) {
			forward.setRedirect(false);
			// 원래 페이지로 돌아가기 위함
			forward.setNextPath("BoardListAction.bo?page=" + pageNum);
		}
		return forward;

	}

}
