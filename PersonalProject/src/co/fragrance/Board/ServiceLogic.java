package co.fragrance.Board;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//import co.fragrance.Comment.CommentList;
import co.fragrance.common.DbManagement;

public class ServiceLogic extends DbManagement {
	public static Board board = null;
	Scanner sc = new Scanner(System.in);
	Scanner sc2 = new Scanner(System.in);

	public void getReviewBoard(int div) {
		while (true) {
			List<Board> list = BoardDao.getInstance().selectBoard(div);

			System.out.println("후기 게시판입니다.");
			System.out.println("글번호 \t조회수 \t 제목 \t       댓글\t작성자    작성일자");
			for (Board board : list) {
				System.out.print(board.getBoardNum() //
						+ "\t" + board.getBoardView() //
						+ "\t" + board.getBoardTitle() //
						+ "\t" + board.getCommentCnt() //
						+ "\t" + board.getMemberId() //
						+ "\t" + board.getBoardTime() + "\n");
			}
			System.out.println("1.게시글 조회 | 2.게시글 작성 | 3.나가기");
			int menu = Integer.parseInt(sc.nextLine());
			if (menu == 1) {
				System.out.println("조회할 게시물 번호를 입력하세요>");
				int boardNum = Integer.parseInt(sc.nextLine());
//				boardNum = 
			} else if (menu == 2) {

			} else if (menu == 3) {
				System.out.println("나가기");
				break;
			} else {
				System.out.println("잘못 입력했습니다. 다시 선택해 주세요.");
			}

		}
	}

//	게시글 조회 가능한 쿼리문 작성
//	ex 보고싶은 게시물 번호 입력해서 들어가면
	// 제목 //
	// 작성자 + 조회수 작성일자// 조회와 동시에 조회수 +1되게
	// 내용//
	public Board lookUp(int boardnum) {
		List<Board> list = new ArrayList<Board>();
		Board board = null;
//		CommentList cl = null;
		try {
			conn();
//			int bdn = rs.getInt("boardView");
//			board.setBoardView(bdn + board.getBoardView());

			String sql2 = "UPDATE board SET board_view = board_view + 1 WHERE board_num = ?";
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, boardnum);
			pstmt.executeUpdate();

			String sql = "SELECT b.board_num, b.board_title," //
					+ " m.member_as, b.board_view, b.board_time,"//
					+ " b.board_coment" //
					+ " FROM member m, board b" //
					+ " WHERE m.member_id = b.member_id" //
					+ "		AND board_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardnum);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				board = new Board();
//				cl = new CommentList();
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
//				cl.setBoardNum(boardNum);
//				List<CommentList> clist = new ArrayList<>() ;
//				clist.add(cl);
			}
			list.add(board);
//			board.setCmts(clist);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return board;
	}
}
// 게시물 조회 시 댓글 (댓글번호) 닉네임 : 댓글내용 같이나오게//

//String sql2 = "SELECT c.comment_num, m.member_as, c.comment_cmt, c.comment_time" //
//+ " FROM comment_list c, member m" //
//+ " WHERE c.member_id = m.member_id" //
//+ "    AND c.board_num = ? " //
//+ " ORDER BY c.comment_num";
//pstmt = conn.prepareStatement(sql2);
//pstmt.setString(1, sql2);
//
//rs = pstmt.executeQuery();
//if (rs.next()) {
//CommentList = new CommentList();
//
//int commentNum = rs.getInt(1);
//String memberAs = rs.getString(2);
//String commentCmt = rs.getString(3);
//String commentTime = rs.getString(4);
//
//}