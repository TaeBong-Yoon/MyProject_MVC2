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
//			방문자 수를 체크하기 위해 ID값만 삭제함
//			세션 삭제시 세션이 변경되어 방문자도 증가함
			session.removeAttribute("sessionID");
			forward.setRedirect(true);
			forward.setNextPath("ResultForm.do");
		} else {
			System.out.println("Delete Fail");
			return null;
		}
		
		return forward;
		
	}

}
