package co.fragrance.Board;

import java.util.List;

import co.fragrance.Comment.CommentList;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Board {
	/*
	 * 필요한 정보 아이디, 비밀번호, 이메일, 닉네임, 권한, 회원가입일
	 */
	private int boardNum; //게시글 번호
	private int boardView; //조회수  
	private String memberId; //작성자 아이디 fk
	private String boardTitle; //제목 
	private String boardComent; //내용
	private int boardDiv; //게시글 구분
	private String boardTime; //작성일
	private String boardUpdate; //수정한 날짜
	private int commentCnt; // 조회수
	private List<CommentList> cmts;

}
