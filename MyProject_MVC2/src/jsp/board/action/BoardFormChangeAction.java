package jsp.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.common.action.Action;
import jsp.common.action.ActionForward;

public class BoardFormChangeAction implements Action {
	
	private String form = "MainForm.jsp?contentPage=board/";
	private String path;
	
	public void setCommand(String command) {
		int idx = command.indexOf(".");
		path = command.substring(0,idx)+".jsp";
	}
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		
		//메인 화면일 경우 MainForm.jsp만 경로로 지정
		if(path.equals("MainForm.jsp")) {
			forward.setNextPath(path);
		} else {
			forward.setNextPath(form+path);
		}
		return forward;
	}

}
