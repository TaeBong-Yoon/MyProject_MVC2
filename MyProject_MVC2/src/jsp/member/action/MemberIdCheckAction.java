package jsp.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.common.action.Action;
import jsp.common.action.ActionForward;
import jsp.member.model.MemberDAO;

public class MemberIdCheckAction implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		String id = request.getParameter("id");
		
		MemberDAO dao = new MemberDAO();
		
		boolean result = dao.duplicateIdCheck(id);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if(result) {
			out.println("0");
		} else {
			out.println("1");
		}
		
		out.close();
		
		return null;
	}

}
