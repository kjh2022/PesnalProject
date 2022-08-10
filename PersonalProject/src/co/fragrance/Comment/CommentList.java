package co.fragrance.Comment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentList {
	private int commentNum;
	private String memberId;
	private int boardNum;
	private String commentCmt;
	private String commentTime;
	private String commentUpdate;
}
