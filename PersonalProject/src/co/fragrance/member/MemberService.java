package co.fragrance.member;

import java.util.List;
import java.util.Scanner;

public class MemberService {
	public static Member memberInfo = null;
	Scanner sc = new Scanner(System.in);

//	1.로그인 서비스 
	public void doLogin() {
		Member member = new Member();

		System.out.println("ID 입력>");
		String id = sc.nextLine();
		System.out.println("PW 입력>");
		String pw = sc.nextLine();

		member = MemberDao.getInstance().loginInfo(id);
		if (member.getMemberPw().equals(pw)) {
			memberInfo = member;
		} else {
			System.out.println("로그인에 실패했습니다. ID나 PW를 확인해 주세요");
		}
	}

	//
//	2.회원가입 // 아이디, 비밀번호, 이메일, 닉네임, 권한, 일반회원은 1
	public void memberJoin() {
		Member member = new Member();
//		id 생성 시 중복체크
		while (true) {
			System.out.println("가입할 아이디 입력(20글자까지 입력가능)> ");
			String id = sc.nextLine();
			boolean dupId = MemberDao.getInstance().duplicateId(id);
			if (dupId) {
				System.out.println("중복된 ID입니다. 다른 ID로 다시 시도해 주세요.");
				continue;
			}
			member.setMemberId(id);
			break;
		}
		System.out.println("로그인에 사용할 비밀번호 입력(20글자까지 입력가능)> ");
		String pw = sc.nextLine();
		member.setMemberPw(pw);
//		이메일 생성 시 중복체크
		while (true) {
			System.out.println("이메일 입력> ");
			String em = sc.nextLine();
			boolean dupEm = MemberDao.getInstance().duplicateEm(em);
			if (dupEm) {
				System.out.println("이미 가입된 사용자의 이메일입니다. 다른 이메일 주소를 입력해 주세요.");
				continue;
			}
			member.setEmail(em);
			break;
		}
//		닉네임 중복체크
		while (true) {
			System.out.println("활동시 사용할 닉네임 입력(10글자까지 입력가능)> ");
			String as = sc.nextLine();
			boolean dupAs = MemberDao.getInstance().duplicateAs(as);
			if (dupAs) {
				System.out.println("이미 사용중인 닉네임입니다. 다시 입력해 주세요.");
				continue;
			}
			member.setMemberAs(as);
			break;
		}
//		일반회원은 1번 고정
		member.setMemberAut(1);

		int result = MemberDao.getInstance().memberJoin(member);

		if (result == 1) {
			System.out.println("고객 정보 등록 완료");
		} else {
			System.out.println("고객 정보 등록 실패");
		}
	}

	//
//	5.회원 조회
//	5-1전체 회원 조회
	public void getMember() {
		List<Member> list = MemberDao.getInstance().selectMember();
		System.out.println("1.전체 회원 조회 | 2.상세 검색으로 회원 조회");
		int menuNo = Integer.parseInt(sc.nextLine());
		if (menuNo == 1) {

			System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
			for (Member member : list) {
				System.out.println("[회원정보] 아이디 : " + member.getMemberId() + ", 닉네임 : " + member.getMemberAs() //
						+ ", 이메일 : " + member.getEmail() + ", 가입일 : " + member.getMemberDate());
			}
			System.out.println("전체 회원 목록 조회 완료");
		}
//		5-2회원 단건 조회
		else if (menuNo == 2) {
			System.out.println("회원 아이디 입력>");
			String id = sc.nextLine();
			list = MemberDao.getInstance().selectDetailMember(id);
			for (Member member : list) {
				System.out.println("==========================================");
				System.out.println("[회원 조회] 아이디 : " + member.getMemberId() + ", 닉네임 : " + member.getMemberAs() + //
						", 이메일 : " + member.getEmail() + ", 작성한 글 : " + member.getBoardCnt() + //
						", 작성한 댓글 : " + member.getCommentCnt() + ", 가입일 : " + member.getMemberDate());
				System.out.println("==========================================");
			}
		} else {
			System.out.println("잘못 입력하셨습니다. 다시 선택해 주세요.");
		}
	}

	//
//	6.로그아웃
	public void logout() {
		if (memberInfo != null)
			memberInfo = null;
		System.out.println("로그아웃 되었습니다.");
	}

	//
//	88.회원 추방 기능
	public void getExpMember() {
		System.out.println("카페에서 탈퇴 시킬 회원 아이디 입력");
		String id = sc.nextLine();

		int result = MemberDao.getInstance().expulsionMember(id);

		if (result == 1) {
			System.out.println("회원 추방 완료");
		} else {
			System.out.println("회원이 추방되지 않았습니다. 아이디를 다시 확인해 주세요.");
		}
	}

	//
//	회원 메뉴
//	5.회원 정보 수정
	public void updateMember() {
		Member member = new Member();
		int menuNo = 0;
		System.out.println("1.닉네임 변경 | 2.이메일 변경 | 3.비밀번호 변경 | 4.돌아가기");
		menuNo = Integer.parseInt(sc.nextLine());
		while (true) {
			if (menuNo == 1) {
//			5-1 닉네임 변경 + 중복체크
				System.out.println("아이디 확인이 필요합니다. 회원 아이디를 입력하세요>");
				String id = sc.nextLine();
				System.out.println("변경할 닉네임 입력");
				String as = sc.nextLine();

				boolean dupAs = MemberDao.getInstance().duplicateAs(as);
				if (dupAs) {
					System.out.println("중복된 닉네임입니다. 다시 시도해 주세요.");
					continue;
				}
				member.setMemberId(id);
				member.setMemberAs(as);
				int result = MemberDao.getInstance().updateMemberAs(member);
				checkResult(result);
				return;
			} else if (menuNo == 2) {
//			5-2이메일 변경
				System.out.println("아이디 확인이 필요합니다. 회원 아이디를 입력하세요>");
				String id = sc.nextLine();
				System.out.println("변경할 이메일 입력");
				String em = sc.nextLine();
				boolean dupEm = MemberDao.getInstance().duplicateEm(em);
				if (dupEm) {
					System.out.println("이미 사용중인 이메일입니다. 다시 시도해 주세요.");
					continue;
				}
				member.setMemberId(id);
				member.setEmail(em);
				int restul = MemberDao.getInstance().updateMemberEm(member);
				checkResult(restul);
			} else if (menuNo == 3) {
//			5-3비밀번호 변경
				System.out.println("아이디 확인이 필요합니다. 회원 아이디를 입력하세요>");
				String id = sc.nextLine();
				System.out.println("변경할 비밀번호 입력");
				String pw = sc.nextLine();
				System.out.println("비밀번호 재확인");
				String pwr = sc.nextLine();
				if (pw.equals(pwr)) {
					member.setMemberId(id);
					member.setMemberPw(pwr);
					int result = MemberDao.getInstance().updateMemberPw(member);
					checkResult(result);
				} else {
					System.out.println("비밀번호가 일치하지 않습니다. 다시 입력해 주세요.");
					continue;
				}
			} else if (menuNo == 4) {
				System.out.println("메인메뉴로 돌아갑니다.");
				break;
			} else {
				System.out.println("잘못 입력하셨습니다. 다시 입력해 주세요.");
			}
			break;
		}
	}

	//
//	99.회원 탈퇴
	public void deleteMember() {
		System.out.println("회원 아이디와 비밀번호를 확인해야 합니다.");
		System.out.println("삭제할 회원 아이디를 입력해 주세요.");
		String id = sc.nextLine();
		System.out.println("비밀번호를 입력해 주세요.");
		String pw = sc.nextLine();
		int result = MemberDao.getInstance().delMember(id, pw);
		if (result == 1) {
			System.out.println("회원 탈퇴 완료");
		} else {
			System.out.println("회원 탈퇴 실패. 아이다와 비밀번호를 확인해 주세요.");
		}
	}

	//
//	결과 체크 메소드
	public void checkResult(int value) {
		if (value == 1) {
			System.out.println("변경 완료");
		} else {
			System.out.println("변경 실패");
		}
	}

}
