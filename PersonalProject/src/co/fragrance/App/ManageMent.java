package co.fragrance.App;

import java.util.Scanner;

import co.fragrance.Board.BoardService;
import co.fragrance.Comment.CommentService;
import co.fragrance.member.MemberService;

public class ManageMent {
	Scanner sc = new Scanner(System.in);
	int menuNo = 0;
	MemberService ms = new MemberService();
	BoardService bs = new BoardService();
	CommentService cs = new CommentService();

	public ManageMent() {
		cafe24();
	}

	private void cafe24() {
		while (true) {
			menuInfo();
			int auth = MemberService.memberInfo.getMemberAut();
			if (auth == 0) {
//				밑에있는 관리자 메뉴
				if (menuNo == 1) { // 공지사항, 관리자 내용 수정가능
					bs.getNotice(auth);
				} else if (menuNo == 2) { // 전체 게시글 목록 보이게, (게시글 삭제 권한 주기)
					bs.getFreeBoard(MemberService.memberInfo.getMemberId(), auth);
				} else if (menuNo == 3) {// 회원 조회
					ms.getMember();
				} else if (menuNo == 4) {// 로그아웃
					ms.logout();
					return;
				} else if (menuNo == 88) { // 회원 추방(정보 삭제)
					ms.getExpMember();
				}
//				여기는 일반 회원들이 보는 메뉴
			} else if (MemberService.memberInfo.getMemberAut() == 1) {

				if (menuNo == 1) { // 1번 공지사항
					bs.getNotice(auth);
				} else if (menuNo == 2) { // 게시판
					bs.getFreeBoard(MemberService.memberInfo.getMemberId(), auth);
					cs.getCommentList(MemberService.memberInfo.getBoardCnt());
				} else if (menuNo == 3) {// 정보 수정(닉네임, 이메일, 비밀번호)
					ms.updateMember();
				} else if (menuNo == 4) { // 로그아웃
					ms.logout();
					break;
				} else if (menuNo == 99) { // 회원 탈퇴
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
			System.out.println("1.공지사항 | 2.전체 게시글 목록 | 3.회원 조회 | 4.로그아웃 | 88.회원 추방");
		} else if (MemberService.memberInfo.getMemberAut() == 1) {
			System.out.println("1.공지사항 | 2.자유 게시판 | 3.정보 수정 | 4.로그아웃 | 99.회원 탈퇴");
		}
		System.out.println("입력> ");
		menuNo = Integer.parseInt(sc.nextLine());
	}
}
