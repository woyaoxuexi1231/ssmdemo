package team.xyh.video.util;

import org.apache.shiro.crypto.hash.Md5Hash;

public class Md5Util {
    public static String md5(String username,String password){
        Md5Hash md5Hash= new Md5Hash(password,username,100);
        System.out.println(md5Hash.toString());
        return md5Hash.toString();
    }
}
