package com.eeerrorcode.member_post.vo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.UUID;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.coobird.thumbnailator.Thumbnailator;

@Getter
@Setter
@ToString
// @EqualsAndHashCode(of = "uuid")
@Builder
@Alias("attach")
@AllArgsConstructor
@NoArgsConstructor
public class Attach {
	private String uuid;
	private String origin;
	private String path;
	private boolean image;
	private Long pno;

	// @Value("${upload.path}")
	private final String UPLOAD_PATH = "c:/upload";

	// MultipartFile 하나 당 Attach 하나씩 매칭
	public Attach(MultipartFile file) {
		this.origin = file.getOriginalFilename();
		int dotIdx = origin.lastIndexOf('.');
		String ext = "";
		if(dotIdx != -1) {
			ext = origin.substring(dotIdx);
		}
		uuid = UUID.randomUUID().toString();
		String realName = uuid = uuid + ext;
		path = getTodayStr();
		File parentPath = new File(UPLOAD_PATH, path);
		
		if(!parentPath.exists()) {
			parentPath.mkdirs();
		}

		try {
			File f = new File(parentPath, realName);
			file.transferTo(f);

			// 마임 타입 체크
			String mime = Files.probeContentType(f.toPath());
			image = mime != null && mime.startsWith("image");

			// thumbnailator
			// new
			if(image) {
				File thumb = new File(parentPath, "t_" + realName);
				Thumbnailator.createThumbnail(f, thumb, 100, 100);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public File toFile() {
		return new File(new File(UPLOAD_PATH, path), uuid);
	}

	public String getTodayStr() {
		return new SimpleDateFormat("yyyy/MM/dd").format(System.currentTimeMillis());
	}

	public static Attach fromFile(File file) {
		return Attach.builder().uuid(file.getName()).build();
	}

	@Override
	public int hashCode() {
		return uuid.hashCode();		
	}

	@Override
	public boolean equals(Object obj) {
		return obj != null && obj instanceof Attach && uuid.equals(((Attach)obj).uuid);
	}

}
