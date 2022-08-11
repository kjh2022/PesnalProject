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

	//
//   1.공지사항
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
				String nickName = rs.getString(1);
				String title = rs.getString(2);
				String coment = rs.getString(3);
				String boardTime = rs.getString(4);
				int boardNum = rs.getInt(5);

				board.setBoardNum(boardNum);
				board.setBoardTime(boardTime);
				board.setBoardComent(coment);
				board.setBoardTitle(title);
				board.setMemberId(nickName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return board;
	}

//	운영자만, 공지사항 수정 가능
	public void updateNotice(Board board) {
		try {
			conn();
			String sql = "UPDATE BOARD SET BOARD_COMENT = ? " //
					+ " WHERE BOARD_NUM = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getBoardComent());
			pstmt.setInt(2, board.getBoardNum());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}
//	운영자 전체 게시글 조회

	//
//	번호 입력 시 각 게시판의 작성된 전체 글 목록 보이게
//	보이는 순서 - 게시글 번호, 조회수, 제목, 댓글 수, 닉네임, 작성날짜
//	게시판 글 목록 2후기 자유3 4거래
	public List<Board> selectBoard(int div) {
		List<Board> list = new ArrayList<Board>();
		Board board = null;
		try {
			conn();
			String sql = "SELECT b.board_num, b.board_view, b.board_title, " //
					+ "			 b.comment_cnt, m.member_as, b.board_time" //
					+ " FROM board b, member m" //
					+ " WHERE board_div = ?" //
					+ " AND b.member_id = m.member_id"; //
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, div);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				board = new Board();
				int boardNum = rs.getInt(1);
				int boardView = rs.getInt(2);
				String boardTitle = rs.getString(3);
				int comentCnt = rs.getInt(4);
				String nickName = rs.getString(5);
				String boardTime = rs.getString(6);

				board.setBoardNum(boardNum);
				board.setBoardView(boardView);
				board.setBoardTitle(boardTitle);
				board.setCommentCnt(comentCnt);
				board.setMemberId(nickName);
				board.setBoardTime(boardTime);
			}
			list.add(board);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

//	리스트 본 후 게시물 조회할 수 있게
	public Board lookUp(int boardnum) {
		List<Board> list = new ArrayList<Board>();
		Board board = null;
		try {
			conn();
			String sql2 = "UPDATE board SET board_view = board_view + 1 WHERE board_num = ?";
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, boardnum);
			pstmt.executeUpdate();

			String sql = "SELECT b.board_num, b.board_title," //
					+ " m.member_as, b.board_view, b.board_time,"//
//					+ " b.board_coment" //
					+ " FROM member m, board b" //
					+ " WHERE m.member_id = b.member_id" //
					+ "		AND board_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardnum);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				board = new Board();
				int boardNum = rs.getInt(1);
				String boardTitle = rs.getString(2);
				String nickName = rs.getString(3);
				int boardView = rs.getInt(4);
				String boardTime = rs.getString(5);
				String boardComent = rs.getString(6);

				board.setBoardNum(boardNum);
				board.setBoardTitle(boardTitle);
				board.setMemberId(nickName);
				board.setBoardView(boardView);
				board.setBoardTime(boardTime);
//				board.setBoardComent(boardComent);
			}
			list.add(board);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return board;
	}
}