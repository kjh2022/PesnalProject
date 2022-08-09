package co.fragrance.App;

import java.util.Scanner;

import co.fragrance.member.MemberService;

public class ManageMent {
	Scanner sc = new Scanner(System.in);
	int menuNo = 0;
	MemberService ms = new MemberService();

	public ManageMent() {
		cafe24();
	}

	private void cafe24() {
		while (true) {
			menuInfo();

			if (MemberService.memberInfo.getMemberAut() == 1) {
				if (menuNo == 1) {

				} else if (menuNo == 2) {

				} else if (menuNo == 3) {

				} else if (menuNo == 4) {

				} else if (menuNo == 5) {

				} else if (menuNo == 6) {

				}
			}
		}
	}

	private void menuInfo() {
//		memberAut == 0 관리자 메뉴
		if (MemberService.memberInfo.getMemberAut() == 0) {
			System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
			System.out.println("관리자로 로그인 하셨습니다.");
			System.out.println("1.공지사항 | 2.향수 후기 게시판 | 3.자유게시판 | 4.거래 게시판 | 5.회원 조회 | 6.회원 추방");
//		memberAut == 1 일반 회원 메뉴
		} else if (MemberService.memberInfo.getMemberAut() == 1) {
			System.out.println("1.공지사항 | 2.향수 후기 게시판 | 3.자유게시판 | 4.거래 게시판");
		}
		System.out.println("입력> ");
		menuNo = Integer.parseInt(sc.nextLine());
	}
}
