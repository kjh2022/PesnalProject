package co.fragrance.Comment;

import java.util.List;
import java.util.Scanner;

public class CommentService {
	public static CommentList commentList = null;
	Scanner sc = new Scanner(System.in);

	public void getCommentList(int boardNum) {
		CommentList commentList = new CommentList();
		commentList.setBoardNum(boardNum);
//		System.out.println("ㅡㅡㅡㅡㅡ작성된 댓글ㅡㅡㅡㅡㅡ");
		while (true) {
//			List<CommentList> list = CommentDao.getInstance().selectCmt(boardNum);
//			System.out.println("글 번호" + "\t" + "작성자");
//			for (CommentList cl : list) {
//				System.out.println(cl.getCommentNum() + "\t" + cl.getMemberId() + "\t" + cl.getCommentCmt() + "\t"
//						+ cl.getCommentTime());
//			}

			System.out.println("1.댓글 작성 | 2.나가기");
			int menu = Integer.parseInt(sc.nextLine());
			if (menu == 1) {
				System.out.println("댓글을 작성해 주세요. 작성이 끝났다면 exit를 입력해 주세요.");
				StringBuilder builder = new StringBuilder();
				while (true) {
					String comment = sc.nextLine();
					if (comment.equals("exit")) {
						break;
					}
					builder.append(System.lineSeparator());
					builder.append(comment);
				}
				commentList.setCommentCmt(builder.toString());
				CommentDao.getInstance().insertComment(commentList);
				System.out.println("댓글 작성이 완료되었습니다.");

			} else if (menu == 2) {
				System.out.println("페이지를 나갑니다.");
				break;
			} else {
				System.out.println("잘못 입력했습니다. 다시 선택해 주세요.");
			}
		}
	}
}
