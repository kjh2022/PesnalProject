package co.fragrance.Board;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import co.fragrance.common.DbManagement;

public class ServiceLogic extends DbManagement {
	public static Board board = null;
	Scanner sc = new Scanner(System.in);
	Scanner sc2 = new Scanner(System.in);

	
//	2.자유게시판 글 목록
	public List<Board> selectBoard(){
		List<Board> list = new ArrayList<>();
		Board board = null;
		try {
			conn();
			String sql = "SELECT board_num, board_title, board_";
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
	}
//	2.자유게시판
//	Dao
	public Board selectFreeBoard() {
		Board board = null;
		try {
			conn();
			String sql = "";
			pstmt = conn.prepareStatement(sql);
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return board;
	}
//	public void get

}
