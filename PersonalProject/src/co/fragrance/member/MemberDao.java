package co.fragrance.member;

import java.util.ArrayList;
import java.util.List;

import co.fragrance.common.DbManagement;

public class MemberDao extends DbManagement {
	// 싱글톤
	private static MemberDao md = null;

	public static MemberDao getInstance() {
		if (md == null) {
			md = new MemberDao();
		}
		return md;
	}

	// 생성자
	private MemberDao() {
	}

//   1.로그인 메소드
	public Member loginInfo(String id, String pw) {
		Member member = null;
		try {
			conn();
			String sql = "SELECT member_id, member_pw, member_as, member_aut FROM member WHERE member_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();
			String idpw = "";
			if (rs.next()) {
				idpw = rs.getString("member_pw");

				if (idpw.equals(pw)) {
					member = new Member();
					member.setMemberId(rs.getString("member_id"));
					member.setMemberPw(rs.getString("member_pw"));
					member.setMemberAs(rs.getString("member_as"));
					member.setMemberAut(rs.getInt("member_aut"));
				} else {
					System.out.println("로그인에 실패했습니다. ID와 PW를 확인해 주세요");
				}
			} else {
				System.out.println("로그인에 실패했습니다. ID와 PW를 확인해 주세요");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return member;
	}

//   1-2. 회원 가입 시 id 중복 확인
	public boolean duplicateId(String id) {
		boolean result = false;
		try {
			conn();
			String sql = "SELECT count(1) age FROM member WHERE member_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int a = rs.getInt("age");
				if (a > 0)
					result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				disconnect();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

//  1-3. 회원 가입 시 이메일 중복 확인
	public boolean duplicateEm(String em) {
		boolean result = false;
		try {
			conn();
			String sql = "SELECT count(1) age FROM member WHERE email = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, em);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int a = rs.getInt("age");
				if (a > 0)
					result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				disconnect();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

//	회원가입 시 닉네임 중복 확인
	public boolean duplicateAs(String as) {
		boolean result = false;
		try {
			conn();
			String sql = "SELECT count(1) age FROM member WHERE member_as = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, as);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int a = rs.getInt("age");
				if (a > 0)
					result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				disconnect();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

//2.회원가입
	public int memberJoin(Member member) {
		int result = 0;
		try {
			conn(); // DB Connection
			String sql = "INSERT INTO member(member_id, member_pw, email, member_as, member_aut, member_date) VALUES(?, ?, ?, ?, ?, TO_CHAR(sysdate, 'yyyy-mm-dd'))";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPw());
			pstmt.setString(3, member.getEmail());
			pstmt.setString(4, member.getMemberAs());
			pstmt.setInt(5, member.getMemberAut());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				disconnect();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	// 관리자 업무
//	5.회원 조회
//	5-1전체 회원 조회 아이디, 닉네임, 이메일, 가입일자만 보이게
	public List<Member> selectMember() {
		List<Member> list = new ArrayList<>();
		Member member = null;
		try {
			conn();
			String sql = "SELECT member_id, member_as, email, member_date FROM member ORDER BY member_id ";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				member = new Member();
				member.setMemberId(rs.getString("member_id"));
				member.setMemberAs(rs.getString("member_as"));
				member.setEmail(rs.getString("email"));
				member.setMemberDate(rs.getString("member_date"));
				list.add(member);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

//	id검색으로 특정 회원 조회
	public List<Member> selectDetailMember(String memberId) {
		List<Member> list = new ArrayList<>();
		Member member = null;
		try {
			conn();
			String sql = "SELECT member_id, member_as, email," //
					+ "  (Select count(*) from board where member_id = ? ) as posts," //
					+ "  (Select count(*) from comment_list where member_id = ? ) as comments,"//
					+ "   member_date" //
					+ "   FROM member" //
					+ "   WHERE member_id = ? ";//
			pstmt = conn.prepareStatement(sql);
			for (int i = 1; i < 4; i++) {
				pstmt.setString(i, memberId);
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				member = new Member();
				member.setMemberId(rs.getString("member_id"));
				member.setMemberAs(rs.getString("member_as"));
				member.setEmail(rs.getString("email"));
				member.setBoardCnt(rs.getInt("posts"));
				member.setCommentCnt(rs.getInt("comments"));
				member.setMemberDate(rs.getString("member_date"));
				list.add(member);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

//	88.회원 추방(삭제)
	public int expulsionMember(String memberId) {
		int result = 0;
		try {
			conn();
			String sql = "DELETE FROM member WHERE member_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return result;
	}

//	일반 회원
//	5.정보 수정
//	5-1닉네임 변경
	public int updateMemberAs(Member member) {
		int result = 0;
		try {
			conn();
			String sql = "UPDATE member SET member_as = ? WHERE member_id = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberAs());
			pstmt.setString(2, member.getMemberId());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return result;
	}

//	5-2이메일 변경ㅇ
	public int updateMemberEm(Member member) {
		int result = 0;
		try {
			conn();
			String sql = "UPDATE member SET email = ? WHERE member_id = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getEmail());
			pstmt.setString(2, member.getMemberId());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return result;
	}

//	5-3비밀번호 변경
	public int updateMemberPw(Member member) {
		int result = 0;
		try {
			conn();
			String sql = "UPDATE member SET member_pw = ? WHERE member_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberPw());
			pstmt.setString(2, member.getMemberId());
			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return result;
	}

//	99.회원 탈퇴
	public int delMember(String memberId, String memberPw) {
		int result = 0;
		try {
			conn();
			String sql2 = "SELECT member_pw FROM member where member_id = ?";
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String a = rs.getString("member_pw");
				if (a.equals(memberPw)) {
					String sql = "DELETE FROM member WHERE member_id = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, memberId);
					result = pstmt.executeUpdate();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return result;
	}
}