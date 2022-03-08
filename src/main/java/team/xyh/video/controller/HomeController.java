package team.xyh.video.controller;

import cn.dsna.util.images.ValidateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import team.xyh.video.dto.Result;
import team.xyh.video.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired

    private UserService userService;

    @RequestMapping("/main")
    public String main(){
        return "index";
    }

    @RequestMapping("/echarts")
    public String echarts(){
        return "echarts";
    }

    @RequestMapping("/main1")
    public String main1(){
        return "main";
    }

    @RequestMapping("formdemo")
    public String formdemo(){
        return "formdemo";
    }

    @RequestMapping("upload")
    public String upload(){
        return "upload";
    }

    @RequestMapping("getCode")
    public void getCode(HttpServletRequest request, HttpServletResponse response){
        //生成验证码
        ValidateCode validateCode = new ValidateCode(160,40,4,20);
        //放入session中
        String code = validateCode.getCode();
        request.getSession().setAttribute("code",code);
        try {
            validateCode.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/login")
    @ResponseBody
    public Result login(String userName,String password,String code,HttpServletRequest request){
        return userService.login(userName,password,code,request);
    }


}
