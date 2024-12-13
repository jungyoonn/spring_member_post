package com.eeerrorcode.member_post.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.eeerrorcode.member_post.vo.Member;

@Mapper
public interface MemberMapper {
	int insert (Member member);
	
	Member selectOne(String id);
	
	List<Member> selectList();
	
	int delete(String id);
}
