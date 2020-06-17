package jsp.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp.common.action.Action;
import jsp.common.action.ActionForward;
import jsp.member.model.MemberBean;
import jsp.member.model.MemberDAO;

public class MemberInfoAction implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ActionForward forward = new ActionForward();
		
//		세션이 가지고 있는 로그인 ID
		HttpSession session = request.getSession();
		String id = session.getAttribute("sessionID").toString();
		
//		위 ID에 해당하는 회원 정보
		MemberDAO dao = new MemberDAO();
		MemberBean member = dao.getUserInfo(id);
		
//		UserInfoForm.jsp에 회원 정보 전달
		request.setAttribute("memberInfo", member);
		
		forward.setRedirect(false);
		forward.setNextPath("UserInfoForm.do");
		
		return forward;
	}

}
