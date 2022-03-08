package team.xyh.video.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import team.xyh.video.dao.UserMapper;
import team.xyh.video.dto.Result;
import team.xyh.video.pojo.User;
import team.xyh.video.service.UserService;
import team.xyh.video.util.Md5Util;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Result login(String userName, String password, String code, HttpServletRequest request) {
        System.out.println(userName+password+code);
        //验证
        Result result = new Result();
        String code1 = (String)request.getSession().getAttribute("code");
        System.out.println("code1:"+code1);
       if(!code1.equalsIgnoreCase(code)){
            result.setCode(400);
            result.setMsg("验证码错误");
            return result;
        }
        //验证用户
        User user = userMapper.findUserByName(userName);
        if(user!=null&&user.getPassword().equals(Md5Util.md5(userName,password))){
            //判断密码
            request.getSession().setAttribute("user",user);
            result.setCode(0);
            return  result;
        }else {
            result.setCode(400);
            result.setMsg("用户名或密码错误");
            return result;
        }
    }
}
