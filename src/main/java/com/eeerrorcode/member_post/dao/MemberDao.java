package com.eeerrorcode.member_post.dao;

import java.sql.*;

import com.eeerrorcode.member_post.utils.DBConn;
import com.eeerrorcode.member_post.vo.Member;

public class MemberDao {
	// CRUD
	public int insert(Member member) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName("org.mariadb.jdbc.Driver");
//			String sql = "INSERT INTO tbl_member(id, pw, name) VALUES ('" + member.getId() + "', '" + member.getPw() + "', '" + member.getName() + "')";
			
			// 위에처럼 뉘역뉘역 쓰지 않고 전처리를 한다
			String sql = "INSERT INTO tbl_member(id, pw, name, email, road_addr, detail_addr) VALUES (?, ?, ?, ?, ?, ?)";
			
			// 1. connection 객체 취득
			conn = DriverManager.getConnection("jdbc:mariadb://43.203.219.241:3306/post", "sample", "1234");

			// 2. 문장 생성, 파라미터 지정
//			Statement stmt = conn.createStatement();
			pstmt = conn.prepareStatement(sql);
			
			int idx = 1;
			pstmt.setString(idx++, member.getId());
			pstmt.setString(idx++, member.getPw());
			pstmt.setString(idx++, member.getName());
			pstmt.setString(idx++, member.getEmail());
			pstmt.setString(idx++, member.getRoadAddr());
			pstmt.setString(idx++, member.getDetailAddr());
			
			// 3. 문장 실행
//			return stmt.executeUpdate(sql);
			return pstmt.executeUpdate();
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException ignore) { }
		}
		
		return 0;
	}
	
	public Member selectOne(String id) {
		Member member = null;
		String sql = "select * from tbl_member where id = ?";
		try (Connection conn = DBConn.getConnection(); PreparedStatement pstmt =conn.prepareStatement(sql)){
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				member = Member.builder()
						.id(rs.getString("id"))
						.pw(rs.getString("pw"))
						.name(rs.getString("name"))
						.email(rs.getString("email"))
						.roadAddr(rs.getString("road_addr"))
						.detailAddr(rs.getString("detail_addr"))
						.regdate(rs.getDate("regdate"))
						.build();
			}
			rs.close();
		} catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} 
		return member;
	}
	
	public static void main(String[] args) {
		MemberDao dao = new MemberDao();
//		int result = dao.insert(Member.builder()
//				.id("ffff")
//				.pw("1234567")
//				.name("이름")
//				.email("abc@naver.com")
//				.roadAddr("대륭")
//				.detailAddr("4층").build());
//		System.out.println(result);
		
		dao.selectOne("sophia");
		System.out.println(dao.selectOne("sophia"));
	}

	// 싱글톤 객체 생성
	private static final MemberDao dao = new MemberDao();
	public static MemberDao getInstance() {
		return dao;
	}
	private MemberDao() {}
}
