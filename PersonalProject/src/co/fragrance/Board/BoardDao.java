package co.fragrance.Board;

import java.util.ArrayList;
import java.util.List;

import co.fragrance.common.DbManagement;

public class BoardDao extends DbManagement {
	// 싱글톤
	private static BoardDao bd = null;

	// 생성자
	private BoardDao() {
	}

	public static BoardDao getInstance() {
		if (bd == null) {
			bd = new BoardDao();
		}
		return bd;
	}

//   1.로그인 메소드
	public Board selectNotice() {
		Board board = null;
		try {
			conn();
			String sql = "SELECT M.member_as, B.board_title, B.board_coment, " //
					+ " TO_CHAR(B.board_time, 'yyyy-MM-dd HH24:mm:ss') as board_time, b.board_num" //
					+ " FROM BOARD B LEFT JOIN MEMBER M ON B.member_id = M.member_id" //
					+ " WHERE b.board_div = 0";

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				board = new Board();
				String nicname = rs.getString(1);
				String title = rs.getString(2);
				String coment = rs.getString(3);
				String boardTime = rs.getString(4);
				int boardNum = rs.getInt(5);

				board.setBoardNum(boardNum);
				board.setBoardTime(boardTime);
				board.setBoardComent(coment);
				board.setBoardTitle(title);
				board.setMemberId(nicname);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return board;
	}
	

	public void updateNotice(Board b) {
		try {
			conn();
			String sql = "UPDATE BOARD SET BOARD_COMENT = ? " //
					+ " WHERE BOARD_NUM = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b.getBoardComent());
			pstmt.setInt(2, b.getBoardNum());

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}
}