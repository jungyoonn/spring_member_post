package com.eeerrorcode.member_post.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.eeerrorcode.member_post.dto.Criteria;
import com.eeerrorcode.member_post.utils.DBConn;
import com.eeerrorcode.member_post.vo.Post;

public class PostDao {
	public int insert(Post post) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "insert into tbl_post(title, writer, content, cno) values (?, ?, ?, ?)";
			
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			int idx = 1;
			pstmt.setString(idx++, post.getTitle());
			pstmt.setString(idx++, post.getWriter());
			pstmt.setString(idx++, post.getContent());
			pstmt.setInt(idx++, post.getCno());
			
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
	
	public Post selectOne(Long pno) {
		Post post = null;
		String sql = "select pno, title, writer, content, view_count, regdate, updatedate from tbl_post where pno = ?";
		
		try (Connection conn = DBConn.getConnection(); PreparedStatement pstmt =conn.prepareStatement(sql)){
			pstmt.setLong(1, pno);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				int idx = 1;
				post = Post.builder()
						.pno(rs.getLong(idx++))
						.title(rs.getString(idx++))
						.writer(rs.getString(idx++))
						.content(rs.getString(idx++))
						.viewCount(rs.getLong(idx++))
						.regdate(rs.getDate(idx++))
						.updatedate(rs.getDate(idx++))
						.build();
			}
			rs.close();
		} catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} 
		return post;
	}
	
	public List<Post> selectList(Criteria cri) {
		List<Post> posts = new ArrayList<>();
		String sql = "select pno, title, writer, view_count, regdate, cno \r\n"
				+ "from tbl_post \r\n"
				+ "where cno = ?\r\n"
				+ "order by 1 desc\r\n"
				+ "limit ? offset ?";
		
		try (Connection conn = DBConn.getConnection(); PreparedStatement pstmt =conn.prepareStatement(sql)){
			pstmt.setInt(1, cri.getCategory());
			pstmt.setInt(2, cri.getAmount());
			pstmt.setInt(3, cri.getOffset());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int idx = 1;
				Post post = Post.builder()
						.pno(rs.getLong(idx++))
						.title(rs.getString(idx++))
						.writer(rs.getString(idx++))
						.viewCount(rs.getLong(idx++))
						.regdate(rs.getDate(idx++))
						.cno(rs.getInt(idx++))
						.build();
				posts.add(post);
			}
			rs.close();
		} catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} 
		return posts;
	}
	
	public int getCount(Criteria cri) {
		String sql = "select count(*) as cnt\r\n"
				+ "from tbl_post \r\n"
				+ "where cno = ?\r\n";
		
		try (Connection conn = DBConn.getConnection(); PreparedStatement pstmt =conn.prepareStatement(sql)){
			pstmt.setInt(1, cri.getCategory());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				return rs.getInt(1);
			}
			rs.close();
		} catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} 
		return 0;
	}
	
	public int update(Post post) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "update tbl_post set title= ?, content = ?, updatedate = now() where pno = ?";
			
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			int idx = 1;
			pstmt.setString(idx++, post.getTitle());
			pstmt.setString(idx++, post.getContent());
			pstmt.setLong(idx++, post.getPno());
			
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
	
	public int increaseViewCount(Long pno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "update tbl_post set view_count = view_count + 1 where pno = ?";
			
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			int idx = 1;
			pstmt.setLong(idx++, pno);
			
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
	
	public int delete(Long pno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "delete from tbl_post where pno = ?";
			
			conn =  DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, pno);
			
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
	
	public static void main(String[] args) {
		// PostDao dao = new PostDao();
		
//		for(int i = 0; i < 100; i ++) {
//			dao.insert(Post.builder()
//					.writer("jjj")
//					.title("제목 " + (i + 100))
//					.content("내용 " + (i + 100))
//					.build());
//		}
		
//		dao.selectList().forEach(System.out::println);
//		System.out.println();
//		System.out.println(dao.selectOne(12L));
//		System.out.println();
//		System.out.println(dao.delete(15L));
//		
//		System.out.println("수정 후");
//		
//		Post post = dao.selectOne(12L);
//		post = Post.builder()
//				.pno(post.getPno())
//				.title("수정된 제목")
//				.content("수정된 내용")
//				.build();
//		dao.update(post);
		
//		System.out.println(dao.selectOne(12L));
		
//		Criteria cri = new Criteria(2, 10, 2);
//		dao.selectList(cri).forEach(System.out::println);
//		System.out.println("==갯수==");
//		System.out.println(dao.getCount(cri));
	}
}
