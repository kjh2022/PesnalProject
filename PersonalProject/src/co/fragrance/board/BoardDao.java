package co.fragrance.board;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter //작성자만 글, 댓글을 삭제할 수 있는 기능
public class BoardDao {
	private int boardNum; //게시글 번호 pk
	private int boardView; //조회수
	private String memberId; //회원아이디 fk
	private String boardTitle; //제목
	private String boardComent; //글 내용
	private int boardDiv; //게시글 구분 공지1, 자유2, 후기3, 거래4
	private String boardTime; //작성일시
	private String boardUpdate; //수정일시

}
