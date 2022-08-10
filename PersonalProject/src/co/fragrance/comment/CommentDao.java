package co.fragrance.comment;

import co.fragrance.common.DbManagement;

public class CommentDao extends DbManagement {
//	싱글톤
	public static CommentDao cd = null;
	
	public static CommentDao getInstance() {
		if(cd == null) {
			cd = new CommentDao();
		}
		return cd;
	}
//	생성자
	private CommentDao() {
	}
//	1.댓글 작성
	public Comment writeComment(String cmt) {
		Comment comment = null;
		try {
			conn();
			String sql = "INSERT INTO comment(comment_cmt) VALUES = '?' ";
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return comment;
		
	}
	
	
}