package com.eeerrorcode.member_post.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.eeerrorcode.member_post.dto.ReplyCri;
import com.eeerrorcode.member_post.vo.Reply;

@Mapper
public interface ReplyMapper {
	int insert (Reply reply);
	int update (Reply reply);
	int delete (Long rno);
	int deleteAll (Long pno);
	
	Reply selectOne(Long rno);
	List<Reply> selectList(@Param("pno") Long pno, @Param("cri")ReplyCri cri);
	List<Reply> selectListByMe(Reply reply);
}
