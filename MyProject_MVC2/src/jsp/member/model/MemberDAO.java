package jsp.member.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jsp.util.DBConnection;

public class MemberDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DBConnection db;

	public Date stringToDate(MemberBean member) {
		String day = member.getBirthdd();
		String month = member.getBirthmm();
		String year = member.getBirthyy();

		Date birthday = Date.valueOf(year + "-" + month + "-" + day);

		return birthday;
	}

	public void insertMember(MemberBean member) throws SQLException {

		conn = null;
		pstmt = null;
		db = null;

		try {

			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO jsp_member VALUES(?, ?, ?, ?, ?, ?, ?, ?, now())");

			db = DBConnection.getInstance();
			conn = db.getConnection();
			// 자동 커밋 false
			conn.setAutoCommit(false);

			stringToDate(member);

			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getGender());
			pstmt.setDate(5, stringToDate(member));
			pstmt.setString(6, member.getMail1() + "@" + member.getMail2());
			pstmt.setString(7, member.getPhone());
			pstmt.setString(8, member.getAddress());
			pstmt.executeUpdate();
			conn.commit();

			DBConnection.close(pstmt);
			DBConnection.close(conn);

		} catch (SQLException e) {
			conn.rollback();
			e.printStackTrace();
		}

	}

	public MemberBean getUserInfo(String id) {
		conn = null;
		pstmt = null;
		rs = null;
		db = null;
		MemberBean member = null;

		try {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT * FROM jsp_member WHERE id=?");

			db = DBConnection.getInstance();
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String birth = rs.getDate("birth").toString();
				String year = birth.substring(0, 4);
				String month = birth.substring(5, 7);
				String day = birth.substring(8, 10);

				String mail = rs.getString("mail");
				int idx = mail.indexOf("@");
				String mail1 = mail.substring(0, idx);
				String mail2 = mail.substring(idx + 1);

				member = new MemberBean();
				member.setId(rs.getString("id"));
				member.setPassword(rs.getString("password"));
				member.setName(rs.getString("name"));
				member.setGender(rs.getString("gender"));
				member.setBirthyy(year);
				member.setBirthmm(month);
				member.setBirthdd(day);
				member.setMail1(mail1);
				member.setMail2(mail2);
				member.setPhone(rs.getString("phone"));
				member.setAddress(rs.getString("address"));
				member.setReg(rs.getTimestamp("reg"));

			}
			return member;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return member;
	}

	public int loginCheck(String id, String pw) {
		conn = null;
		pstmt = null;
		rs = null;
		db = null;

		String dbPW = "";
		int result = -1;

		try {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT password FROM jsp_member WHERE id=?");

			db = DBConnection.getInstance();
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dbPW = rs.getString("password");

				if (dbPW.equals(pw)) {
					result = 1;
				} else {
					result = 0;
				}
			} else {
				result = -1;
			}
			DBConnection.close(rs);
			DBConnection.close(pstmt);
			DBConnection.close(conn);
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public void updateMember(MemberBean member) throws SQLException {
		conn = null;
		pstmt = null;
		db = null;

		try {
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE jsp_member SET password=?, mail=?, phone=?, address=? WHERE id=?");

			db = DBConnection.getInstance();
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql.toString());

			conn.setAutoCommit(false);

			pstmt.setString(1, member.getPassword());
			pstmt.setString(2, member.getMail1() + "@" + member.getMail2());
			pstmt.setString(3, member.getPhone());
			pstmt.setString(4, member.getAddress());
			pstmt.setString(5, member.getId());

			pstmt.executeUpdate();
			conn.commit();

			DBConnection.close(pstmt);
			DBConnection.close(conn);

		} catch (SQLException e) {
			conn.rollback();
			e.printStackTrace();
		}
	}

	public int deleteMember(String id, String pw) {

		conn = null;
		pstmt = null;
		rs = null;
		db = null;

		String dbpw = "";
		int result = -1;

		try {
			StringBuffer sql1 = new StringBuffer();
			sql1.append("SELECT password FROM jsp_member WHERE id=?");
			StringBuffer sql2 = new StringBuffer();
			sql2.append("DELETE FROM jsp_member WHERE id=?");

			db = DBConnection.getInstance();
			conn = db.getConnection();
			conn.setAutoCommit(false);

			pstmt = conn.prepareStatement(sql1.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dbpw = rs.getString("password");
				if (dbpw.equals(pw)) {
					pstmt = conn.prepareStatement(sql2.toString());
					pstmt.setString(1, id);
					pstmt.executeUpdate();
					conn.commit();
					result = 1;
				} else {
					result = 0;
				}
			} else {
				result = -1;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
