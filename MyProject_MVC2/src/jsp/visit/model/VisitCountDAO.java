package jsp.visit.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jsp.common.util.DBConnection;

public class VisitCountDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DBConnection db;

	public void setTotalCount() throws SQLException {

		conn = null;
		pstmt = null;
		db = null;
		
//		공부하고 추가해보자! - id 중복 제외하고 count++하기
//		id는 primary key, 시간은 now() 추가로 totalcnt를 증가 시킨다.
//		id select 후 중복되는 id가 없으면 추가. 있으면 아무것도 실행 안함

		try {
			StringBuffer sql = new StringBuffer();
//			sql.append("INSERT INTO visit (id,v_date) VALUES (?,now())");
			sql.append("INSERT INTO visit (v_date) VALUES (now())");
			
			db = DBConnection.getInstance();
			conn = db.getConnection();

			conn.setAutoCommit(false);

			pstmt = conn.prepareStatement(sql.toString());
//			pstmt.setString(1, id);
			pstmt.executeUpdate();
			conn.commit();
			DBConnection.close(pstmt);
			DBConnection.close(conn);

		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		}

	}

	public int getTotalCount() {

		conn = null;
		pstmt = null;
		rs = null;
		db = null;
		int totalCount = 0;

		try {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT COUNT(*) AS TotalCnt FROM visit");

			db = DBConnection.getInstance();
			conn = db.getConnection();

			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				totalCount = rs.getInt("TotalCnt");
			}

			DBConnection.close(rs);
			DBConnection.close(pstmt);
			DBConnection.close(conn);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalCount;
	}

	public int getTodayCount() {
		conn = null;
		pstmt = null;
		rs = null;
		db = null;
		int todayCount = 0;

		try {
			StringBuffer sql = new StringBuffer();
			sql.append(
					"SELECT COUNT(*) AS TodayCnt FROM visit WHERE date_format(v_date, '%Y-%m-%d') = date_format(now(), '%Y-%m-%d')");

			db = DBConnection.getInstance();
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				todayCount = rs.getInt("TodayCnt");
			}

			DBConnection.close(rs);
			DBConnection.close(pstmt);
			DBConnection.close(conn);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return todayCount;

	}

}
