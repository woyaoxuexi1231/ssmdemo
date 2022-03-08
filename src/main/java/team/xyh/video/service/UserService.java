package team.xyh.video.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import team.xyh.video.dto.Result;

import javax.servlet.http.HttpServletRequest;

@Scope("prototype")
@Service
public interface UserService {
    Result login(String userName, String password, String code, HttpServletRequest request);
}
