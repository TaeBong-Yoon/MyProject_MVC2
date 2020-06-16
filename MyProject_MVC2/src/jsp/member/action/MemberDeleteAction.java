package jsp.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp.member.model.MemberDAO;

public class MemberDeleteAction implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ActionForward forward = new ActionForward();
		
		HttpSession session = request.getSession();
		String id = session.getAttribute("sessionID").toString();
		String password = request.getParameter("password");
		
		MemberDAO dao = new MemberDAO();
		int result = dao.deleteMember(id, password);
		
		if(result == 1) {
			session.invalidate();
			forward.setRedirect(true);
			forward.setNextPath("Result.do");
		} else {
			System.out.println("Delete Fail");
			return null;
		}
		
		return forward;
		
	}

}
