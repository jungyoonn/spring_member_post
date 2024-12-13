package com.eeerrorcode.member_post.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@Alias("post")
public class Post {
	private final Long pno;
	private final String title;
	private final String writer;
	private final String content;
	private final Long viewCount;
	private final Date regdate;
	private final Date updatedate;
	private final int cno;
	private final boolean attachFlag;
	private List<Attach> attachs = new ArrayList<Attach>();
	
	public Post(Long pno, String title, String writer, String content, Long viewCount, Date regdate, Date updatedate,
			int cno, boolean attachFlag) {
		super();
		this.pno = pno;
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.viewCount = viewCount;
		this.regdate = regdate;
		this.updatedate = updatedate;
		this.cno = cno;
		this.attachFlag = attachFlag;
	}
	
}
