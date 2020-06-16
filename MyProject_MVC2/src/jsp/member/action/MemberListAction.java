package jsp.member.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.member.model.MemberBean;
import jsp.member.model.MemberDAO;

// 모든 회원정보를 보여주는 Action
public class MemberListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = new ActionForward();

		MemberDAO dao = new MemberDAO();
		ArrayList<MemberBean> memberList = dao.getMemberList();

		request.setAttribute("memberList", memberList);

		forward.setRedirect(false);
		forward.setNextPath("MemberListForm.do");

		return forward;

	}

}
