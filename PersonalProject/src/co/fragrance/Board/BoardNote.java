package co.fragrance.Board;

public class BoardNote {
	//sql문 
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

//	public Board weiteBoard() {
//		Board board = null;
//	}
	
//	1. 공지사항 조회
	public void getNotice(int auth) {
		while (true) {
			Board board = new Board();
			board = BoardDao.getInstance().selectNotice();

			System.out.println(board.getBoardTitle());
			System.out.println("");
			System.out.println(board.getBoardComent());
			System.out.println("작성일자 [" + board.getBoardTime() + "]");
			System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
//			관리자 공지사항 수정
			if (auth == 0) {
				System.out.println("1.수정하기 | 2.나가기");
				int endPoint = Integer.parseInt(sc.nextLine());
				if (endPoint == 1) {
					StringBuilder builder = new StringBuilder();
					System.out.println("수정할 내용을 입력해주세요, 내용입력을 끝마치고 싶다면 exit 라고 입력해주세요");
					while (true) {
						String comment = sc.nextLine();
						if (comment.equals("exit")) {
							break;
						}
						builder.append(System.lineSeparator());
						builder.append(comment);
					}
					board.setBoardComent(builder.toString());
					BoardDao.getInstance().updateNotice(board);
					System.out.println("수정이 완료되었습니다.");
					continue;
				} else if (endPoint == 2) {
					System.out.println("메인메뉴로 돌아갑니다.");
					break;
				} else {
					System.out.println("잘못 입력하였습니다 다시 선택해주세요.");
					continue;
				}
			}
}
