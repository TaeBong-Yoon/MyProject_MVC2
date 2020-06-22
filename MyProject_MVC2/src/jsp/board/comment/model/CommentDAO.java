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
			sql.append("INSERT INTO board_comment");
			sql.append(" (comment_board,comment_id,comment_date,comment_parent,comment_content)");
			sql.append(" VALUES (?,?,NOW(),?,?)");

			db = DBConnection.getInstance();
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, comment.getComment_board());
			pstmt.setString(2, comment.getComment_id());
			pstmt.setInt(3, comment.getComment_parent());
			pstmt.setString(4, comment.getComment_content());
			
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
			sql.append("SELECT * FROM board_comment WHERE comment_board = ? ORDER BY comment_num ASC");
			
			db = DBConnection.getInstance();
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setInt(1, boardNum);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CommentBean comment = new CommentBean();
				comment.setComment_num(rs.getInt("comment_num"));
				comment.setComment_board(rs.getInt("comment_board"));
				comment.setComment_id(rs.getString("comment_id"));
				comment.setComment_date(rs.getDate("comment_date"));
				comment.setComment_parent(rs.getInt("comment_parent"));
				comment.setComment_content(rs.getString("comment_content"));
				
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
	
	public CommentBean getComment(int comment_num) {
		
		conn = null;
		pstmt = null;
		rs = null;
		db = null;
		CommentBean comment = null;
		
		try {
			
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT * FROM board_comment WHERE comment_num = ?");
			
			db = DBConnection.getInstance();
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, comment_num);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				comment = new CommentBean();
				
				comment.setComment_num(rs.getInt("comment_num"));
				comment.setComment_board(rs.getInt("comment_board"));
				comment.setComment_id(rs.getString("comment_id"));
				comment.setComment_content(rs.getString("comment_content"));
				comment.setComment_date(rs.getDate("comment_date"));
				comment.setComment_parent(rs.getInt("comment_parent"));
			}
			
			DBConnection.close(rs);
			DBConnection.close(pstmt);
			DBConnection.close(conn);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return comment;
	}

}
