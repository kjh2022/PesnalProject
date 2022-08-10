package co.fragrance.comment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Comment {
	private int commentNum;
	private String memberId;
	private int boardNum;
	private String commentCmt;
	private String commentTime;
	private String commentUpdate;
}
