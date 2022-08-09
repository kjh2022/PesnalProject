package co.fragrance.member;

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

//	2.회원가입 // 아이디, 비밀번호, 이메일, 닉네임, 권한, 일반회원은 1
	public void memberJoin() {
		Member member = new Member();

		while (true) {
			System.out.println("가입할 아이디 입력> ");
			String id = sc.nextLine();
//		id 중복체크
			boolean dupId = MemberDao.getInstance().duplicateId(id);
			if (dupId) {
				System.out.println("중복된 ID입니다. 다른 ID로 다시 시도해 주세요.");
				continue;
			}
			member.setMemberId(id);
			break;
		}

		System.out.println("로그인에 사용할 비밀번호 입력> ");
		String pw = sc.nextLine();

		member.setMemberPw(pw);
		
		while (true) {
			System.out.println("이메일 입력> ");
			String em = sc.nextLine();
//			이메일 중복체크
			boolean dupEm = MemberDao.getInstance().duplicateEm(em);
			if (dupEm) {
				System.out.println("이미 가입된 사용자의 이메일입니다. 다른 이메일 주소를 입력해 주세요.");
				continue;
			}
			member.setEmail(em);
			break;
		}
		while (true) {
			System.out.println("활동시 사용할 닉네임 입력> ");
			String as = sc.nextLine();
//		닉네임 중복체크
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

//	9.로그아웃
	public void logout() {
		if (memberInfo != null)
			memberInfo = null;
		System.out.println("로그아웃 되었습니다.");
	}

}
