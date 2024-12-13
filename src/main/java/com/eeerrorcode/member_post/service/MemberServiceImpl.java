package com.eeerrorcode.member_post.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.eeerrorcode.member_post.mapper.MemberMapper;
import com.eeerrorcode.member_post.utils.MybatisInit;
import com.eeerrorcode.member_post.vo.Member;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {
	private MemberMapper mapper;

	@Override
	public int register(Member member) {
		try(SqlSession session = MybatisInit.getInstance().sqSessionFactory().openSession(true)) {
			MemberMapper mapper = session.getMapper(MemberMapper.class);
			return mapper.insert(member);
		}
	}

	@Override
	public Member findBy(String id) {
		return mapper.selectOne(id);
	}

	@Override
	public boolean login(String id, String pw) {
		Member m = findBy(id);
		return m != null && m.getPw().equals(pw);
	}

	@Override
	public List<Member> list() {
		try(SqlSession session = MybatisInit.getInstance().sqSessionFactory().openSession(true)) {
			MemberMapper mapper = session.getMapper(MemberMapper.class);
			return mapper.selectList();
		}
	}

	@Override
	public boolean remove(String id) {
		
		return false;
	}

	@Override
	public boolean modify(Member member) {
		
		return false;
	}
}
