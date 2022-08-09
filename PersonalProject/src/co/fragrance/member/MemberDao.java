package co.fragrance.member;

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
	public Member loginInfo(String id) {
		Member member = null;
		try {
			conn();
			String sql = "SELECT member_id, member_pw, member_as, member_aut FROM member WHERE member_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				member = new Member();
				member.setMemberId(rs.getString("member_id"));
				member.setMemberPw(rs.getString("member_pw"));
				member.setMemberAs(rs.getString("member_as"));
				member.setMemberAut(rs.getInt("member_aut"));
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
				int b = rs.getInt("age");
				if (b > 0)
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

			String sql = "INSERT INTO member(member_id, member_pw, email, member_as, member_aut, member_date) VALUES(?, ?, ?, ?, ?, TO_CHAR(sysdate, 'yyyy/mm/dd'))";

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

}