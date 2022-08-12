package co.fragrance.Comment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentList {
	private int commentNum; //댓글번호 pk
	private String memberId; //회원id fk
	private int boardNum; //글번호 fk
	private String commentCmt; //글 내용
	private String commentTime; //댓글 입력날짜
	private String commentUpdate;
}
