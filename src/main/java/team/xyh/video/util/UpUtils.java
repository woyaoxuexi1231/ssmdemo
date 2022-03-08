package team.xyh.video.util;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;


public class UpUtils {

	public static String upfile(MultipartFile file, HttpServletRequest request) {

		//获取当前服务器地址
		file.getOriginalFilename().endsWith("");
		String realPath = request.getSession().getServletContext().getRealPath("/video/");
		System.out.println(realPath);
		/*本地*/
		String basePath="/Users/xiyang/IdeaProjects/trill/src/main/webapp/WEB-INF/video/";

		File file1 = new File(realPath);
		if (!file1.exists()) {
			file1.mkdirs();
		}
/*		File file2 = new File(basePath);
		if(!file2.exists()) {
             file2.mkdirs();
		}*/

		String orgName = file.getOriginalFilename();
		try {
			FileOutputStream fos = new FileOutputStream(realPath+orgName,true);
//			FileOutputStream fos1 = new FileOutputStream(basePath+orgName,true);
			fos.write(file.getBytes());
//			fos1.write(file.getBytes());
//			fos1.flush();
			fos.flush();
//			fos1.close();
			fos.close();
			return "200";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "501";

		}
	}

}
