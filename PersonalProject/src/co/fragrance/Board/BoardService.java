package co.fragrance.Board;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy;

public class BoardService {
	public static Board board = null;
	Scanner sc = new Scanner(System.in);
	Scanner sc2 = new Scanner(System.in);
	
// 게시판 분류 : 공지 0(읽기전용), 자유 1, 후기 2, 거래 3	
//	1. 공지사항 조회
	public void getNotice(int auth) {
		while(true) {
			Board board = new Board();
			//게시글 조회
			board = BoardDao.getInstance().selectNotice();
			
			System.out.println(board.getBoardTitle());
			System.out.println("");
			
			System.out.println(board.getBoardComent());
			
			
			System.out.println("작성일자 [" +board.getBoardTime() + "]");
			
			System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
			if(auth == 0) {
				System.out.println("1.수정하기 | 2.나가기");
				int endPoint = Integer.parseInt(sc.nextLine());
				if(endPoint == 1) {
					StringBuilder builder = new StringBuilder();
					System.out.println("수정할 내용을 입력해주세요, 내용입력을 끝마치고 싶다면 exit 라고 입력해주세요");
					while(true) {
						String comment = sc.nextLine();
						if(comment.equals("exit")) {
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
			} else {
				System.out.println("1.나가기");
				
				int endPoint = Integer.parseInt(sc.nextLine());
				if(endPoint == 1) {
					System.out.println("메인메뉴로 돌아갑니다.");
					break;
				}  else {
					System.out.println("잘못 입력하였습니다 다시 선택해주세요.");
					continue;
				}
			}
		}
	}

	
	
}
