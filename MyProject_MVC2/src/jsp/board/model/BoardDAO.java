package jsp.board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import jsp.common.util.DBConnection;

public class BoardDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DBConnection db;

	// DB의 board_num을 가져오는 메소드
	public int getNum() {

		conn = null;
		pstmt = null;
		rs = null;
		db = null;

		int result = 1; // 1부터 시작

		try {
			StringBuffer sql = new StringBuffer();
			sql.append("select board_num from member_board;");
			db = DBConnection.getInstance();
			conn = db.getConnection();

			pstmt = conn.prepareStatement(sql.toString());

			if (rs.next()) {
				result = rs.getInt(1);
			}

			DBConnection.close(rs);
			DBConnection.close(pstmt);
			DBConnection.close(conn);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// 글 삽입을 위한 메소드
	public boolean boardInsert(BoardBean board) {

		conn = null;
		pstmt = null;
		db = null;

		boolean result = false;

		try {

			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO member_board (board_num,board_id,board_subject,board_content,board_file,");
			sql.append("Board_re_ref,Board_re_lev,Board_re_seq,Board_count,Board_date)");
			sql.append(" VALUES (?,?,?,?,?,?,?,?,?,now());");
			db = DBConnection.getInstance();
			conn = db.getConnection();

			// num 값을 글번호와 그룹번호로 사용한다
			int num = board.getBoard_num();

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, num);
			pstmt.setString(2, board.getBoard_id());
			pstmt.setString(3, board.getBoard_subject());
			pstmt.setString(4, board.getBoard_content());
			pstmt.setString(5, board.getBoard_file());
			pstmt.setInt(6, num);
			pstmt.setInt(7, 0);
			pstmt.setInt(8, 0);
			pstmt.setInt(9, 0);

			int flag = pstmt.executeUpdate();

			if (flag > 0) {
				result = true;
//				conn.commit();
			}

			DBConnection.close(pstmt);
			DBConnection.close(conn);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// DB에서 글 목록 가져오기
	public ArrayList<BoardBean> getBoardList(HashMap<String, Object> listOpt) {

		ArrayList<BoardBean> list = new ArrayList<BoardBean>();

		// 검색옵션 (제목, 내용, 글쓴이..)
		String opt = (String) listOpt.get("opt");
		// 검색내용
		String condition = (String) listOpt.get("condition");
		// 현 페이지 넘버링
		int start = (Integer) listOpt.get("start");

		conn = null;
		pstmt = null;
		rs = null;
		db = null;

		// SELECT * FROM member_board ORDER BY board_num DESC LIMIT 5으로 할까...
		// 마음에 안드는 부분
		// 1. 후반 페이지로 갈수록 넘버링이 1에 가까워 져야함.(최신글 적용)
		// 2. 찾기 할때 setInt를 정해놓으니까 그 이후 애들을 못찾음
		// #1 - setInt를 삭제하고 LIMIT 만 5개로 거는것도 방법.
		// #2 - 근데 보여줄 때 어떻게 보여지는 지를 테스트 해봐야함...
		// #3 - 1페이지 2페이지 둘다 8~4 순으로 5개 보이고 끝. 나머지가 list에 안담김...
		// sql.append("SELECT * FROM member_board ORDER BY board_num desc LIMIT 5;");
		try {

			StringBuffer sql = new StringBuffer();
			db = DBConnection.getInstance();
			conn = db.getConnection();
			// 글 목록 전체를 보여줄 때
			if (opt == null) {
				// board_num 을 내림차순 후 화면에 보여줄 갯수까지 가져온다.(5개까지 설정함)
				sql.append(
						"SELECT * FROM member_board WHERE board_num >=? AND board_num <=? ORDER BY board_num DESC LIMIT 5;");

				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setInt(1, start);
				pstmt.setInt(2, start + 4);

				// sql을 비워준다.
				sql.delete(0, sql.toString().length());
			} else if (opt.equals("0")) {
				// 제목으로 검색할 때
				// where 조건 and 조건 and 조건 활용
				sql.append("SELECT * FROM ");
				sql.append("member_board WHERE board_subject LIKE ? AND ");
				sql.append("board_num >= ? AND board_num <=? ORDER BY board_num DESC LIMIT 5;");

				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, '%' + condition + '%');
				pstmt.setInt(2, start);
				pstmt.setInt(3, start + 4);

				sql.delete(0, sql.toString().length());
			} else if (opt.equals("1")) {
				// 내용으로 검색할 때
				// 위와 구조 동일, board_content로 검색
				sql.append("SELECT * FROM ");
				sql.append("member_board WHERE board_content LIKE ? AND ");
				sql.append("board_num >= ? AND board_num <=? ORDER BY board_num DESC LIMIT 5;");

				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, '%' + condition + '%');
				pstmt.setInt(2, start);
				pstmt.setInt(3, start + 4);

				sql.delete(0, sql.toString().length());
			} else if (opt.equals("2")) {
				// 제목 + 내용으로 검색할 때
				// 위와 구조 동일, board_subject or board_content로 조건 설정
				sql.append("SELECT * FROM ");
				sql.append("member_board WHERE (board_subject LIKE ? OR board_content LIKE ?) AND ");
				sql.append("board_num >= ? AND board_num <=? ORDER BY board_num DESC LIMIT 5;");

				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, '%' + condition + '%');
				pstmt.setString(2, '%' + condition + '%');
				pstmt.setInt(3, start);
				pstmt.setInt(4, start + 4);

				sql.delete(0, sql.toString().length());
			} else if (opt.equals("3")) {
				// 글쓴이로 검색할 때
				sql.append("SELECT * FROM ");
				sql.append("member_board WHERE board_id LIKE ? AND ");
				sql.append("board_num >= ? AND board_num <=? ORDER BY board_num DESC LIMIT 5;");

				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, '%' + condition + '%');
				pstmt.setInt(2, start);
				pstmt.setInt(3, start + 4);

				sql.delete(0, sql.toString().length());
			}

			rs = pstmt.executeQuery();
			while (rs.next()) {
				// 가져온 정보들을 list형식으로 담는다
				BoardBean board = new BoardBean();
				board.setBoard_num(rs.getInt("board_num"));
				board.setBoard_id(rs.getString("board_id"));
				board.setBoard_subject(rs.getString("board_subject"));
				board.setBoard_content(rs.getString("board_content"));
				board.setBoard_file(rs.getString("board_file"));
				board.setBoard_count(rs.getInt("board_count"));
				board.setBoard_re_ref(rs.getInt("board_re_ref"));
				board.setBoard_re_lev(rs.getInt("board_re_lev"));
				board.setBoard_re_seq(rs.getInt("board_re_seq"));
				board.setBoard_date(rs.getDate("board_date"));
				list.add(board);
			}

			DBConnection.close(rs);
			DBConnection.close(pstmt);
			DBConnection.close(conn);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// 글의 갯수를 가져오는 메소드
	public int getBoardListCount(HashMap<String, Object> listOpt) {

		conn = null;
		pstmt = null;
		rs = null;
		db = null;

		int result = 0;
		String opt = (String) listOpt.get("opt");
		String condition = (String) listOpt.get("condition");

		try {
			StringBuffer sql = new StringBuffer();
			db = DBConnection.getInstance();
			conn = db.getConnection();

			if (opt == null) {
				// 전체글 갯수
				sql.append("SELECT COUNT(*) FROM member_board;");

				pstmt = conn.prepareStatement(sql.toString());

				sql.delete(0, sql.toString().length());
			} else if (opt.equals("0")) {
				// 제목으로 검색한 글 갯수
				sql.append("SELECT COUNT(*) FROM member_board");
				sql.append("WHERE board_subject LIKE ?;");

				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, '%' + condition + '%');

				sql.delete(0, sql.toString().length());
			} else if (opt.equals("1")) {
				// 내용으로 검색할 때
				sql.append("SELECT COUNT(*) FROM ");
				sql.append("member_board WHERE board_content LIKE ?;");

				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, '%' + condition + '%');

				sql.delete(0, sql.toString().length());
			} else if (opt.equals("2")) {
				// 제목 + 내용으로 검색할 때
				// 위와 구조 동일, board_subject or board_content로 조건 설정
				sql.append("SELECT CONUT(*) FROM ");
				sql.append("member_board WHERE board_subject LIKE ? OR board_content LIKE ?;");

				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, '%' + condition + '%');
				pstmt.setString(2, '%' + condition + '%');

				sql.delete(0, sql.toString().length());
			} else if (opt.equals("3")) {
				// 글쓴이로 검색할 때
				sql.append("SELECT COUNT(*) FROM ");
				sql.append("member_board WHERE board_id LIKE ?;");

				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, '%' + condition + '%');

				sql.delete(0, sql.toString().length());
			}

			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}

			DBConnection.close(rs);
			DBConnection.close(pstmt);
			DBConnection.close(conn);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// 상세보기
	public BoardBean getDetail(int boardNum) {

		conn = null;
		pstmt = null;
		rs = null;
		db = null;
		BoardBean board = null;
		
		try {

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT * FROM member_board WHERE board_num=?");

			db = DBConnection.getInstance();
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, boardNum);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				board = new BoardBean();
				board.setBoard_num(boardNum);
				board.setBoard_id(rs.getString("board_id"));
				board.setBoard_subject(rs.getString("board_subject"));
				board.setBoard_content(rs.getString("board_content"));
				board.setBoard_file(rs.getString("board_file"));
				board.setBoard_count(rs.getInt("board_count"));
				board.setBoard_re_ref(rs.getInt("Board_re_ref"));
				board.setBoard_re_lev(rs.getInt("Board_re_lev"));
				board.setBoard_re_seq(rs.getInt("Board_re_seq"));
				board.setBoard_date(rs.getDate("Board_date"));
			}
			
			DBConnection.close(rs);
			DBConnection.close(pstmt);
			DBConnection.close(conn);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return board;
	}

	// 조회수 증가
	public boolean updateCount(int boardNum) throws SQLException {

		boolean result = false;
		conn = null;
		pstmt = null;
		db = null;

		try {
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE member_board SET board_count = board_count+1 WHERE board_num = ?");

			
			db = DBConnection.getInstance();
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, boardNum);

			int flag = pstmt.executeUpdate();

			if (flag > 0) {
				result = true;
			}

			DBConnection.close(pstmt);
			DBConnection.close(conn);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}