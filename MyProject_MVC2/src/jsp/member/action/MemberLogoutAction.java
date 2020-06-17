package jsp.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.common.action.Action;
import jsp.common.action.ActionForward;

public class MemberLogoutAction implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ActionForward forward = new ActionForward();
		
//		방문자 수를 체크하기 위해 ID값만 삭제함
//		세션 삭제시 세션이 변경되어 방문자도 증가함
		request.getSession().removeAttribute("sessionID");;
		
		forward.setRedirect(true);
		forward.setNextPath("MainForm.do");
		
		return forward;
	}

}
