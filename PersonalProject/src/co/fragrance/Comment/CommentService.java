package co.fragrance.Comment;

import java.util.Scanner;

import co.fragrance.member.Member;

public class CommentService {
	public static Comment board = null;
	Scanner sc = new Scanner(System.in);
	
//	1. 공지사항 조회
	public void showNotice() {
		Comment board = new Comment();

		member = CommentDao.getInstance().loginInfo(id);
		if (member.getMemberPw().equals(pw)) {
			memberInfo = member;
		} else {
			System.out.println("로그인에 실패했습니다. ID나 PW를 확인해 주세요");
		}
	}
	
	
	

}
