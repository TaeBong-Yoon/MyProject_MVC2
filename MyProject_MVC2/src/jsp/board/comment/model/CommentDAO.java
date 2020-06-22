package jsp.board.comment.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import jsp.common.util.DBConnection;

public class CommentDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DBConnection db;

	public boolean insertComment(CommentBean comment) {

		conn = null;
		pstmt = null;
		db = null;
		boolean result = false;

		try {

			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO member_reply");
			sql.append(" (reply_ident_num, reply_parent_num, reply_id, reply_content, reply_date)");
			sql.append(" VALUES (?,?,?,?,NOW())");

			db = DBConnection.getInstance();
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, comment.getReply_ident_num());
			pstmt.setInt(2, comment.getReply_parent_num());
			pstmt.setString(3, comment.getReply_id());
			pstmt.setString(4, comment.getReply_content());

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
	
	public ArrayList<CommentBean> getCommentList(int boardNum){
		
		conn = null;
		pstmt = null;
		rs = null;
		db = null;
		ArrayList<CommentBean> list = new ArrayList<CommentBean>();
		
		try { 
			
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT * FROM member_reply WHERE reply_ident_num = ? ORDER BY reply_num DESC");
			
			db = DBConnection.getInstance();
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setInt(1, boardNum);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CommentBean comment = new CommentBean();
				comment.setReply_depth(rs.getInt("reply_depth"));
				comment.setReply_ident_num(rs.getInt("reply_ident_num"));
				comment.setReply_id(rs.getString("reply_id"));
				comment.setReply_content(rs.getString("reply_content"));
				comment.setReply_date(rs.getDate("reply_date"));
				comment.setReply_parent_num(rs.getInt("reply_parent_num"));
				list.add(comment);
			}
			
			DBConnection.close(rs);
			DBConnection.close(pstmt);
			DBConnection.close(conn);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
