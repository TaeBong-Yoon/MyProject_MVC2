package jsp.board.action;

import java.io.File;
import java.io.FileInputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.common.action.Action;
import jsp.common.action.ActionForward;

// 파일 다운로드 위한 액션
public class FileDownloadAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String fileName = request.getParameter("file_name");

		// 파일이 들어있는 절대경로
		String folder = request.getServletContext().getRealPath("UploadFolder");

		// 파일의 절대경로를 만들어준다.
		String filePath = folder + "/" + fileName;

		try {
			File file = new File(filePath);
			byte[] b = new byte[(int) file.length()];

			response.reset();
			response.setContentType("application/octet-stream");

			// 한글 인코딩 - https://croak.tistory.com/18 참고
			String encoding = new String(fileName.getBytes("UTF-8"), "8859_1");

			// 파일 링크 클릭했을 때 다운로드 저장 화면 출력되게 처리
			// https://m.blog.naver.com/vivacarla/220987094379
			// 크롬에서
			response.setHeader("Content-Disposition", "attachment;filename=\"" + encoding+"\"");
			// 다른 IE
//			response.setHeader("Content-Disposition", "attachment;filename=" + encoding);
			response.setHeader("Content-Length", String.valueOf(file.length()));

			// 파일이 있을 경우
			if (file.isFile()) {
				FileInputStream fileInputStream = new FileInputStream(file);
				ServletOutputStream servletOutputStream = response.getOutputStream();

				// 파일을 읽어온 후 클라이언트쪽에 저장
				int readNum = 0;
				while ((readNum = fileInputStream.read(b)) != -1) {
					servletOutputStream.write(b, 0, readNum);
				}
				servletOutputStream.close();
				fileInputStream.close();
			}

		} catch (Exception e) {
			System.out.println("Download Exception : " + e.getMessage());
		}
		return null;

	}

}
