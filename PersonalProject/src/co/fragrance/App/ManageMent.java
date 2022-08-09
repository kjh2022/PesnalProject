package co.fragrance.App;

import java.util.Scanner;

import co.fragrance.Board.BoardService;
import co.fragrance.member.MemberService;

public class ManageMent {
	Scanner sc = new Scanner(System.in);
	int menuNo = 0;
	MemberService ms = new MemberService();
	BoardService bs = new BoardService();
	public ManageMent() {
		cafe24();
	}

	private void cafe24() {
		while (true) {
			menuInfo();
			int auth = MemberService.memberInfo.getMemberAut();
			if (auth == 0) {
//				밑에있는 관리자 메뉴
				if (menuNo == 1) {
					bs.getNotice(auth);
				} else if (menuNo == 2) {

				} else if (menuNo == 3) {

				} else if (menuNo == 4) {

				} else if (menuNo == 5) {

				} else if (menuNo == 6) {

				}
//				여기는 일반 회원들이 보는 메뉴
			} else if(MemberService.memberInfo.getMemberAut() == 1) {
				
			}
		}
	}

	private void menuInfo() {
		if (MemberService.memberInfo.getMemberAut() == 0) {
			System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
			System.out.println("어서오세요 관리자님");
			System.out.println("1.공지사항 | 2.향수 후기 게시판 | 3.자유 게시판 | 4.거래 게시판 | 5.회원 조회 | 6.회원 추방 | 9.로그아웃");
		} else if (MemberService.memberInfo.getMemberAut() == 1) {
			System.out.println("1.공지사항 | 2.향수 후기 게시판 | 3.자유 게시판 | 4.거래 게시판 | 5.회원 탈퇴 | 9.로그아웃");
		}
		System.out.println("입력> ");
		menuNo = Integer.parseInt(sc.nextLine());
	}
}
