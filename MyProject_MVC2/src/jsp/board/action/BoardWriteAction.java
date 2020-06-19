package jsp.board.action;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import jsp.board.model.BoardBean;
import jsp.board.model.BoardDAO;
import jsp.common.action.Action;
import jsp.common.action.ActionForward;

public class BoardWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ActionForward forward = new ActionForward();
		
		// 업로드 파일 사이즈 5MByte
		int fileSize = 5*1024*1024;
		// 업로드 폴더 경로
		String uploadPath = request.getServletContext().getRealPath("/UploadFolder");
		
		try {
			//파일 업로드
			MultipartRequest multi = new MultipartRequest(request,uploadPath,fileSize,"UTF-8",new DefaultFileRenamePolicy());
			
			//파일 이름 가져오기 - 공부 더 필요함
			String fileName = "";
			Enumeration<String> names = multi.getFileNames();
			if(names.hasMoreElements()) {
				String name = names.nextElement();
				fileName = multi.getFilesystemName(name);
			}
			
			BoardDAO dao = new BoardDAO();
			BoardBean boardData = new BoardBean();
			
			//boardData.setBoard_num(dao.getNum()); // board_num 세팅
			boardData.setBoard_id(multi.getParameter("board_id")); // 히든값으로 넘긴 id
			boardData.setBoard_subject(multi.getParameter("board_subject"));
			boardData.setBoard_content(multi.getParameter("board_content"));
			boardData.setBoard_file(fileName);
			
			boolean result = dao.boardInsert(boardData);
			
			if(result) {
				forward.setRedirect(true);
				forward.setNextPath("BoardListAction.bo");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Write Fail" + e.getMessage());
		}
		return forward;
	}
	
	
}
