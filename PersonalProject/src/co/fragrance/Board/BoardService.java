package co.fragrance.Board;

import java.util.List;
import java.util.Scanner;

import co.fragrance.Comment.CommentDao;
import co.fragrance.Comment.CommentList;

public class BoardService {
	public static Board board = null;
	Scanner sc = new Scanner(System.in);
	Scanner sc2 = new Scanner(System.in);

// 게시판 분류 : 공지 0 자유 2
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
//				일반회원은 확인만 가능
			else {
				System.out.println("1.나가기");
				int endPoint = Integer.parseInt(sc.nextLine());
				if (endPoint == 1) {
					System.out.println("메인메뉴로 돌아갑니다.");
					break;
				} else {
					System.out.println("잘못 입력하였습니다 다시 선택해주세요.");
					continue;
				}
			}
		}
	}

	//
//	2.자유게시판 게시글 목록 보이게 글번호, 조회수, 제목, [댓글수], 작성자, 작성일자 순서대로
	public void getFreeBoard(String memberId, int auth) {
		Board board = new Board();
		board.setMemberId(memberId);

		System.out.println("자유 게시판입니다.");
		while (true) {
			List<Board> list = BoardDao.getInstance().selectBoard(2);
			for (Board bd : list) {
				System.out.printf("글 번호 : %-2d 조회수 : %-2d 제목 : %-20s 댓글 : [%d] 작성자 : %-8s 작성일자 : %s \n", //
						bd.getBoardNum(), bd.getBoardView(), bd.getBoardTitle(), bd.getCommentCnt(), bd.getMemberId(),
						bd.getBoardTime());
			}
			if (auth == 0) {
				System.out.println("1.게시글 삭제 | 2.나가기");
				int endPoint = Integer.parseInt(sc.nextLine());
				if (endPoint == 1) {
					System.out.println("삭제할 게시글 번호를 입력해 주세요.");
					int bdN = Integer.parseInt(sc.nextLine());

					int result = BoardDao.getInstance().deleteBoard(bdN);

					if (result == 1) {
						System.out.println("게시글 삭제 완료");
					} else {
						System.out.println("게시글 삭제 실패");
					}
				} else if (endPoint == 2) {
					System.out.println("메인 메뉴로 돌아갑니다.");
					break;
				}
			} else {

				System.out.println("1.게시글 조회 | 2.게시글 작성 | 3.나가기");
				int menu = Integer.parseInt(sc.nextLine());
				if (menu == 1) {
					System.out.println("조회할 게시물 번호를 입력하세요>");
					int boardnum = Integer.parseInt(sc.nextLine());
					Board bd = BoardDao.getInstance().lookUp(boardnum);
					if (bd != null) {

						System.out.println("제목 : " + bd.getBoardTitle() + "   글번호 : " + bd.getBoardNum());
						System.out.println("작성자 : " + bd.getMemberId() + ", 조회수 : " + bd.getBoardView());
						System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ내용ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
						System.out.println(bd.getBoardComent());
						System.out.println("작성일 : " + bd.getBoardTime());

//					댓글 리스트 뽑기
						List<CommentList> list2 = CommentDao.getInstance().selectCmt(boardnum);
						System.out.println("ㅡㅡㅡㅡㅡ작성된 댓글ㅡㅡㅡㅡㅡ");
						System.out.println("글 번호" + "\t" + "작성자\n");
						for (CommentList cmtlist : list2) {
							System.out.println(cmtlist.getCommentNum() + "\t" + cmtlist.getMemberId() + "\t"
									+ cmtlist.getCommentCmt() + "\t" + cmtlist.getCommentTime());
						}

					} else if (bd == null) {
						System.out.println("없는 게시글 번호 입니다.");
					}
					return;
				} else if (menu == 2) {
					System.out.println("-----------------------------");
					System.out.println("자유게시판 글 작성입니다.");
					System.out.println("-----------------------------");

					System.out.println("제목을 입력해 주세요");
					String subject = sc.nextLine();
					board.setBoardTitle(subject);

					System.out.println("작성할 내용을 입력해주세요. 내용입력을 끝마치고 글을 등록하고 싶다면 exit를 입력해주세요.");
					StringBuilder builder = new StringBuilder();
					while (true) {
						String comment = sc.nextLine();
						if (comment.equals("exit")) {
							break;
						}
						builder.append(System.lineSeparator());
						builder.append(comment);
					}
					board.setBoardComent(builder.toString());
					BoardDao.getInstance().insertReview(board);

					System.out.println("글작성이 완료되었습니다.");
				} else if (menu == 3) {
					System.out.println("메인메뉴로 돌아갑니다.");
					break;
				} else {
					System.out.println("잘못 입력했습니다. 다시 선택해 주세요.");
				}
			}
		}
	}

}
