package co.fragrance.Comment;

import co.fragrance.common.DbManagement;

public class CommentDao extends DbManagement {
	// 싱글톤
	private static CommentDao bd = null;

	// 생성자
	private CommentDao() {
	}

	public static CommentDao getInstance() {
		if(bd == null) {
			bd = new CommentDao();
		}
		return bd;
	}
	
	public 

//   1.로그인 메소드
	public Comment loginInfo(String id) {
		Comment member = null;
		try {
			conn();
			String sql = "SELECT member_id, member_pw, member_as, member_aut FROM member WHERE member_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();


		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return member;
	}
}