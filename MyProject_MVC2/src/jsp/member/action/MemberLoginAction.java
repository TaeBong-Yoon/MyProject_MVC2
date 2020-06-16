package jsp.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp.member.model.MemberDAO;

public class MemberLoginAction implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		
//		ID PW 가져오기
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
//		DB에서 가져온값 확인
		MemberDAO dao = new MemberDAO();
		int check = dao.loginCheck(id, password);
		
//		비밀번호 틀린 경우
		if(check == 0) {
			request.setAttribute("fail", "0");
			
			forward.setRedirect(false);
			forward.setNextPath("LoginForm.do");
//		아이디가 없는 경우
		} else if(check == -1) {
			request.setAttribute("faul", "-1");
			
			forward.setRedirect(false);
			forward.setNextPath("LoginForm.do");
		} else {
			session.setAttribute("sessionID", id);
			
			forward.setRedirect(true);
			forward.setNextPath("MainForm.do");
		}
		
		return forward;

	}

}
