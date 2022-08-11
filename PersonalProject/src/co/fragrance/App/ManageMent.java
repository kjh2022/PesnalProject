package co.fragrance.App;

import java.util.Scanner;

import co.fragrance.Board.BoardService;

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
				if (menuNo == 1) { // 공지 ok
					bs.getNotice(auth);
				} else if (menuNo == 2) { // 전체 게시글 목록 보이게, (게시글 삭제 권한 주기)
					bs.getAllBoard(auth); //R광고성 글, 불량한 글 바로 지울수있게
				} else if (menuNo == 5) {// 조회 ok
					ms.getMember();
				} else if (menuNo == 6) {// 로그아웃 ok
					ms.logout();
					return;
				} else if (menuNo == 88) { // 강퇴 ok
					ms.getExpMember();
				}
//				여기는 일반 회원들이 보는 메뉴
			} else if (MemberService.memberInfo.getMemberAut() == 1) {

				if (menuNo == 1) { // 1번 공지사항 ok
					bs.getNotice(auth);
				} else if (menuNo == 2) { // 2자유
					bs.getFreeBoard(MemberService.memberInfo.getMemberId());
				} else if (menuNo == 4) {// 4정보 수정 ok
					ms.updateMember();
				} else if (menuNo == 5) { // 5로그아웃 ok
					ms.logout();
					break;
				} else if (menuNo == 99) { // 99탈퇴 ok
					ms.deleteMember();
					break;
				}
			}
		}
	}

	private void menuInfo() {
		if (MemberService.memberInfo.getMemberAut() == 0) {
			System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
			System.out.println("어서오세요 관리자님");
			System.out.println("1.공지사항 | 2.전체 게시글 목록 | 5.회원 조회 | 6.로그아웃 | 88.회원 추방");
		} else if (MemberService.memberInfo.getMemberAut() == 1) {
			System.out.println("1.공지사항 | 2.자유 게시판 | 4.정보 수정 | 5.로그아웃 | 99.회원 탈퇴");
		}
		System.out.println("입력> ");
		menuNo = Integer.parseInt(sc.nextLine());
	}
}
