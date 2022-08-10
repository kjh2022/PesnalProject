package co.fragrance.App;

import java.util.Scanner;

import co.fragrance.member.MemberService;

public class Application {
	Scanner sc = new Scanner(System.in);
	int menuNo = 0;
	MemberService ms = new MemberService();

	public Application() {
		start();
	}

	private void start() {
		while (true) {
			System.out.println("1.로그인 | 2.회원 가입 | 3.종료");
			int menuNo = Integer.parseInt(sc.nextLine());

			if (menuNo == 1) {
				ms.doLogin();
				if (MemberService.memberInfo != null) {
					new ManageMent();
				}
			} else if (menuNo == 2) {
				ms.memberJoin();
			} else if (menuNo == 3) {
				System.out.println("프로그램을 종료합니다.");
				break;
			} else {
				System.out.println("잘못 입력했습니다. 다시 선택해 주세요.");
			}
		}
		sc.close();
	}
}
