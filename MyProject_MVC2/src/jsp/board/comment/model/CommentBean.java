package jsp.board.comment.model;

import java.sql.Date;

public class CommentBean {

	private int reply_num; // 댓글 넘버
	private int reply_ident_num; // 원글의 번호 foreign key - board_num
	private int reply_parent_num; // 대댓글을 달때 댓글 넘버 reply_num참고
	private int reply_order_num; // 댓글 보여지는 순서
	private String reply_id; // 댓글러 아이디
	private String reply_content; // 댓글 내용
	private int reply_depth; // 댓글의 깊이
	private Date reply_date; // 댓글 날짜

	public int getReply_num() {
		return reply_num;
	}

	public void setReply_num(int reply_num) {
		this.reply_num = reply_num;
	}

	public int getReply_ident_num() {
		return reply_ident_num;
	}

	public void setReply_ident_num(int reply_ident_num) {
		this.reply_ident_num = reply_ident_num;
	}

	public int getReply_parent_num() {
		return reply_parent_num;
	}

	public void setReply_parent_num(int reply_parent_num) {
		this.reply_parent_num = reply_parent_num;
	}

	public int getReply_order_num() {
		return reply_order_num;
	}

	public void setReply_order_num(int reply_order_num) {
		this.reply_order_num = reply_order_num;
	}

	public String getReply_id() {
		return reply_id;
	}

	public void setReply_id(String reply_id) {
		this.reply_id = reply_id;
	}

	public String getReply_content() {
		return reply_content;
	}

	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}

	public int getReply_depth() {
		return reply_depth;
	}

	public void setReply_depth(int reply_depth) {
		this.reply_depth = reply_depth;
	}

	public Date getReply_date() {
		return reply_date;
	}

	public void setReply_date(Date reply_date) {
		this.reply_date = reply_date;
	}

}
