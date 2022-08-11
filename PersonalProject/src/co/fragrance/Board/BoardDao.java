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
//	번호 입력 시 각 게시판에 작성된 전체 글 목록 보이게
//	보이는 순서 - 게시글 번호, 조회수, 제목, 댓글 수, 닉네임, 작성날짜
	public List<Board> selectBoard(int div) {
		List<Board> list = new ArrayList<Board>();
		try {
			conn();
			String sql = "SELECT b.board_num, b.board_view, b.board_title, " //
					+ "	(SELECT COUNT(1) FROM comment_list WHERE board_num = b.board_num) AS comment_cnt, m.member_as, b.board_time" //
					+ " FROM board b, member m" //
					+ " WHERE b.member_id = m.member_id " //
					+ " AND b.board_div = ?"; //
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, div);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Board board = new Board();
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
				
				list.add(board);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

//	리스트 본 후 게시물 번호 입력해서 글 내용 조회할 수 있게
	public Board lookUp(int boardnum) {
		Board board = null;
		try {
			conn();
			
			String sql2 = "UPDATE board SET board_view = board_view + 1 WHERE board_num = ?";
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, boardnum);
			pstmt.executeUpdate();
			
//			게시글 내용출력하는곳
			String sql = "SELECT b.board_num, b.board_title," //
					+ " m.member_as, b.board_view, b.board_time"//
					+ " b.board_coment" //
					+ " FROM member m, board b" //
					+ " WHERE m.member_id = b.member_id" //
					+ "	AND b.board_num = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardnum);
			rs = pstmt.executeQuery();

			while (rs.next()) {
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
				board.setBoardComent(boardComent);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return board;
	}

//	2.자유게시판에 글 작성
public int insertReview(Board board) {
	int result = 0;
	try {
		conn();
		String sql = "INSERT INTO BOARD(board_num, "
				+ "board_view, member_id, board_title, board_coment, board_div,"
				+ "board_time)"
				+ " VALUES (SEQ_BOARD.nextval, ?, ?, ?, ?, ?, sysdate)"; //
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, 0);
		pstmt.setString(2, board.getMemberId());
		pstmt.setString(3, board.getBoardTitle());
		pstmt.setString(4, board.getBoardComent());
		pstmt.setInt(5, 2);

		result = pstmt.executeUpdate();

	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		disconnect();
	}
	return result;
}
	
}