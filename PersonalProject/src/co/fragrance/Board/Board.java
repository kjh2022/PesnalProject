package co.fragrance.Board;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Board {
	/*
	 * 필요한 정보 아이디, 비밀번호, 이메일, 닉네임, 권한, 회원가입일
	 */
	private int boardNum;
	private int boardView;
	private String memberId;
	private String boardTitle;
	private String boardComent;
	private String boardDiv;
	private String boardTime;
	private String boardUpdate;

}
