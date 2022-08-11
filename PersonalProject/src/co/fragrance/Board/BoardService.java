package co.fragrance.Board;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy;

public class BoardService {
	public static Board board = null;
	Scanner sc = new Scanner(System.in);
	Scanner sc2 = new Scanner(System.in);

// 게시판 분류 : 공지 0, 후기 2, 자유 3, 거래 4
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
	//admin
//	전체 게시글 조회
	public void getAllBoard(int atuh) {
		System.out.println("전체 게시글 조회입니다.");
		while(true) {
			
		}
	}

	//
//	자유게시판 게시글 목록 보이게 글번호, 조회수, 제목, [댓글수], 작성자, 작성일자 순서대로
//	2후기
	public void getReviewBoard(int div) {
		System.out.println("후기 게시판입니다.");
		while (true) {
			List<Board> list = BoardDao.getInstance().selectBoard(div);
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
				int boardnum = Integer.parseInt(sc.nextLine());
				BoardDao.getInstance().lookUp(boardnum);

			} else if (menu == 2) {

			} else if (menu == 3) {
				System.out.println("메인메뉴로 돌아갑니다.");
				break;
			} else {
				System.out.println("잘못 입력했습니다. 다시 선택해 주세요.");
			}
		}

	}

//	3자유
	public void getFreeBoard(int div) {
		System.out.println("자유 게시판입니다.");
		while (true) {
			List<Board> list = BoardDao.getInstance().selectBoard(div);
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
				int boardnum = Integer.parseInt(sc.nextLine());
				BoardDao.getInstance().lookUp(boardnum);

			} else if (menu == 2) {

			} else if (menu == 3) {
				System.out.println("메인메뉴로 돌아갑니다.");
				break;
			} else {
				System.out.println("잘못 입력했습니다. 다시 선택해 주세요.");
			}
		}

	}

	public void getTradeBoard(int div) {
		System.out.println("거래 게시판입니다.");
		while (true) {
			List<Board> list = BoardDao.getInstance().selectBoard(div);
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
				int boardnum = Integer.parseInt(sc.nextLine());
				BoardDao.getInstance().lookUp(boardnum);

			} else if (menu == 2) {

			} else if (menu == 3) {
				System.out.println("메인메뉴로 돌아갑니다.");
				break;
			} else {
				System.out.println("잘못 입력했습니다. 다시 선택해 주세요.");
			}
		}

	}
}
