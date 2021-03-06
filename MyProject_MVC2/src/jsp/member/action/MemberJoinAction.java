package jsp.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.common.action.Action;
import jsp.common.action.ActionForward;
import jsp.member.model.MemberBean;
import jsp.member.model.MemberDAO;

public class MemberJoinAction implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		ActionForward forward = new ActionForward();
		
		MemberDAO dao = new MemberDAO();
		
		MemberBean member = new MemberBean();
		member.setId(request.getParameter("id"));
		member.setPassword(request.getParameter("password"));
		member.setName(request.getParameter("name"));
		member.setGender(request.getParameter("gender"));
		member.setBirthdd(request.getParameter("birthdd"));
		member.setBirthmm(request.getParameterValues("birthmm")[0]);
		member.setBirthyy(request.getParameter("birthyy"));
		member.setMail1(request.getParameter("mail1"));
		member.setMail2(request.getParameterValues("mail2")[0]);
		member.setPhone(request.getParameter("phone"));
		member.setAddress(request.getParameter("address"));

//		회원가입 실행
		dao.insertMember(member);
		
//		가입성공
		forward.setRedirect(true);
		forward.setNextPath("ResultForm.do");
		
//		가입성공 메세지 세션에 담아준다.
		request.getSession().setAttribute("message", 1);
		
		return forward;
	}

}
