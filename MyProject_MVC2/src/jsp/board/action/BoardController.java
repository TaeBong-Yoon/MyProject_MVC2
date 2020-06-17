package jsp.board.action;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.common.action.Action;
import jsp.common.action.ActionForward;

public class BoardController  extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private HashMap<String, Action> commandMap;

	// 최초 실행하는 init()
	public void init(ServletConfig config) throws ServletException {
		loadProperties("jsp/board/properties/BoardCommand");
	}

	// get 방식 doGet()
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	// post 방식 doPost()
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	// properties 파일에서 키값, 클래스 정보를 추출 후 Map에 저장.
	private void loadProperties(String filePath) {
		commandMap = new HashMap<String, Action>();

		ResourceBundle rb = ResourceBundle.getBundle(filePath);
		Enumeration<String> actionEnum = rb.getKeys(); // 키값 가져옴

		while (actionEnum.hasMoreElements()) {
			// 명령어를 가져옴
			String command = actionEnum.nextElement();
			// 명령어에 해당하는 ACtion 클래스 이름을 가져옴
			String className = rb.getString(command);

			try {
				// 클래스 생성
				Class actionClass = Class.forName(className);
				// 클래스의 객체를 생성
				Action actionInstance = (Action) actionClass.newInstance();

				// 화면전환 Action 인지 확인한다. 화면전환 Action이면 명령어를 전달함.
				if (className.equals("jsp.board.action.BoardFormChangeAction")) {
					BoardFormChangeAction bf = (BoardFormChangeAction)actionInstance;
					bf.setCommand(command);
				}

				commandMap.put(command, actionInstance);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String requestURI = request.getRequestURI();
		int cmdIdx = requestURI.lastIndexOf("/") + 1;
		String command = requestURI.substring(cmdIdx);

		System.out.println("Board Command : " + command);

		ActionForward forward = null;
		Action action = null;
		
		try {
			action = commandMap.get(command);
			
			if(action == null) {
				System.out.println("Command Error");
				return;
			}
			forward = action.execute(request, response);
		
			if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getNextPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getNextPath());
				dispatcher.forward(request, response);
			}
		}
		
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
