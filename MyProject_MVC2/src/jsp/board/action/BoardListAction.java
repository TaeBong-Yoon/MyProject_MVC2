package jsp.board.action;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.board.model.BoardBean;
import jsp.board.model.BoardDAO;
import jsp.common.action.Action;
import jsp.common.action.ActionForward;

// 글 목록 보여주는 Action
// 페이징 처리도 같이 해줌
public class BoardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = new ActionForward();

		// 현재 페이지 번호
		int spage = 1;
		String page = request.getParameter("page");
		
		if(page != null) {
			spage = Integer.parseInt(page);
		}
		
		// 검색조건, 내용을 가져온다
		String opt = request.getParameter("opt");
		String condition = request.getParameter("condition");
		
		// 검색조건과 내용을 Map에 담아준다.
		HashMap<String,Object> listOpt = new HashMap<String,Object>();
		listOpt.put("opt", opt);
		listOpt.put("condition", condition);
		listOpt.put("start", spage*5-4);
		
		BoardDAO dao = new BoardDAO();
		int listCount = dao.getBoardListCount(listOpt);
		ArrayList<BoardBean> list = dao.getBoardList(listOpt);
		
		// 한 화면에 5개 게시글, 페이지 갯수 5개 - 이후 페이지는 [Next] or [Previous]
		// 전체 페이지 갯수
		int maxPage = (int)(listCount/5.0 + 0.9);
		// 시작 페이지 번호
		int startPage = (int)(spage/5.0 +0.8) * 5 - 4;
		// 마지막 페이지 번호
		int endPage = startPage + 4;
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		// 페이지 번호 저장
		request.setAttribute("spage", spage);
		request.setAttribute("maxPage", maxPage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		
		// 글 총 갯수, 글 목록 저장
		request.setAttribute("listCount", listCount);
		request.setAttribute("list", list);
		
		// DB 상태변화 없으므로 forward() 사용
		forward.setRedirect(false);
		forward.setNextPath("BoardListForm.bo");
		
		return forward;
	}

}
