package com.eeerrorcode.member_post.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.eeerrorcode.member_post.dto.Criteria;
import com.eeerrorcode.member_post.vo.Post;

@Mapper
public interface PostMapper {
	int insert(Post post);

	Post selectOne(Long pno);

	List<Post> selectList(Criteria cri);
	
	int getCount(Criteria cri);
	
	int update(Post post);
	
	int increaseViewCount(Long pno);
	
	int delete(Long pno);
}
