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
		while(true) {
			menuInfo();
		}
	}

	
	
	
	
	
	
	
	
	private void menuInfo() {
//		memberAut == 0 관리자 메뉴
		if(MemberService.memberInfo.getMemberAut()==0) {
			System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
			System.out.println("관리자로 로그인 하셨습니다.");
			System.out.println();
		}
	}
}
