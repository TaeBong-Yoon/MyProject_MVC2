package jsp.board.comment.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.board.comment.model.CommentBean;
import jsp.board.comment.model.CommentDAO;
import jsp.common.action.Action;
import jsp.common.action.ActionForward;

public class CommentWriteAction implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		CommentDAO dao = new CommentDAO();
		CommentBean comment = new CommentBean();
		
		int comment_board = Integer.parseInt(request.getParameter("comment_board"));
		String comment_id = request.getParameter("comment_id");
		String comment_content = request.getParameter("comment_content");

		System.out.println(comment_board);
		System.out.println(comment_id);
		System.out.println(comment_content);
		
		comment.setReply_ident_num(comment_board);
		comment.setReply_id(comment_id);
		comment.setReply_content(comment_content);
		
		boolean result = dao.insertComment(comment);
		
		if(result) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("1");
			out.close();
		}
		
		return null;
		
	}

}
