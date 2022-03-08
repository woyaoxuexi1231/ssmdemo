package team.xyh.video.util;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.MultimediaInfo;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

//专门解析视频或者音频
public class VideoUtils {

    public static MultimediaInfo analysis(String videoName,HttpServletRequest request){
        //获取到需要解析视频的路径
        String realPath = request.getSession().getServletContext().getRealPath("/video/");
       //获取视频的文件对象
        File file = new File(realPath+videoName);
        //获取解析视频的对象  （jave）
        //视频解析
        Encoder encoder = new Encoder();
        try {
            //info 是视频解析以后的对象
            MultimediaInfo info = encoder.getInfo(file);
            return info;
        } catch (EncoderException e) {
            e.printStackTrace();
            return null;
        }
    }




}
