package jsp.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberLogoutAction implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ActionForward forward = new ActionForward();
		
		request.getSession().invalidate();
		
		forward.setRedirect(true);
		forward.setNextPath("MainForm.do");
		
		return forward;
	}

}
