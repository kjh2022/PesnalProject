package co.fragrance.Comment;

import java.util.ArrayList;
import java.util.List;

import co.fragrance.common.DbManagement;

public class CommentDao extends DbManagement {
	
//	싱글톤
	public static CommentDao cd = null;

	public static CommentDao getInstance() {
		if (cd == null) {
			cd = new CommentDao();
		}
		return cd;
	}

//	생성자
	private CommentDao() {
	}

//	게시글 조회 시 나오는 댓글 목록
	public List<CommentList> selectCmt(int boardNum) {
		List<CommentList> list = new ArrayList<CommentList>();
		try {
			conn();
			String sql = " SELECT c.comment_num, m.member_as, c.comment_cmt, c.comment_time " //
					+ " FROM comment_list c, member m " //
					+ " WHERE c.member_id = m.member_id " //
					+ " AND c.board_num = ? " //
					+ "ORDER BY c.comment_num"; //
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNum);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CommentList commentList = new CommentList();
				int cmtNum = rs.getInt(1);
				String nickName = rs.getString(2);
				String cmtCmt = rs.getString(3);
				String cmtTime = rs.getString(4);

				commentList.setCommentNum(cmtNum);
				commentList.setMemberId(nickName);
				commentList.setCommentCmt(cmtCmt);
				commentList.setCommentTime(cmtTime);

				list.add(commentList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

//	1.댓글 작성
	public int insertComment(CommentList commentList, int boardNum) {
		int result = 0;
		try {
			conn();
			String sql = "INSERT INTO comment_list(comment_num, member_id, comment_cmt, comment_time)"
					+ " VALUES( SEQ_REPLY.nextval, ?, ?, sysdate)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, commentList.getMemberId());
			pstmt.setString(2, commentList.getCommentCmt());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return result;
	}

}
