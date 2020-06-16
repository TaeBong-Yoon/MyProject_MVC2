package jsp.visit.action;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import jsp.visit.model.VisitCountDAO;

public class VisitSessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent sessionEve) {

		// 세션이 생성되면 execute()를 실행함
		if (sessionEve.getSession().isNew()) {
			System.out.println("Session Create!!");
			execute(sessionEve);
		}
	}

	private void execute(HttpSessionEvent sessionEve) {

		VisitCountDAO dao = new VisitCountDAO();

		try {
			
			HttpSession session = sessionEve.getSession();
//			공부하고 추가해보자! - id 중복 제외하고 count++하기
//			String id = session.getAttribute("sessionID").toString();
			
			dao.setTotalCount();

			int totalCount = dao.getTotalCount();

			int todayCount = dao.getTodayCount();


			session.setAttribute("totalCount", totalCount);
			session.setAttribute("todayCount", todayCount);

		} catch (Exception e) {
			System.out.println("Visitor Count Error");
			e.printStackTrace();
		}
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {

	}
}
