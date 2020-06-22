package jsp.board.comment.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.board.comment.model.CommentBean;
import jsp.board.comment.model.CommentDAO;
import jsp.common.action.Action;
import jsp.common.action.ActionForward;

public class CommentReplyFormAction implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ActionForward forward = new ActionForward();
		
		int comment_num = Integer.parseInt(request.getParameter("num")); // 부모글 글 번호
		
		CommentDAO dao = new CommentDAO();
		CommentBean comment = dao.getComment(comment_num);
		
		request.setAttribute("comment", comment);
		
		forward.setRedirect(false);
		forward.setNextPath("board/comment/CommentReplyForm.jsp");
		
		return forward;
		
	}

}
