package co.fragrance.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member {
	/*
	 * 필요한 정보 아이디, 비밀번호, 이메일, 닉네임, 권한, 회원가입일ㄴ
	 */
	private String memberId; // 아이디
	private String memberPw; // 비밀번호
	private String email; // 이메일
	private String memberAs; // 닉네임
	private int memberAut; // 권한부여
	private String memberDate;// 가입날자 표기
	private int boardCnt; // 게시글 작성 개수
	private int commentCnt; // 댓글 작성 개수
}
