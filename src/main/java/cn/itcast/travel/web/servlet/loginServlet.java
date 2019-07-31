package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.impl.userServiceImpl;
import cn.itcast.travel.service.userService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /**
         * 登陆
         */
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User loginUser=new User();

        loginUser.setUsername(username);
        loginUser.setPassword(password);

        userService service=new userServiceImpl();
        User user=service.login(loginUser);

        request.getSession().setAttribute("user",user);

        ResultInfo info=new ResultInfo();

        if (user==null){

            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误！");
        }
        if (user!=null&&!"Y".equals(user.getStatus())){
            info.setFlag(false);
            info.setErrorMsg("未激活");
        }
        if (user!=null&&"Y".equals(user.getStatus())){
            info.setFlag(true);
        }

        ObjectMapper mapper=new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");

        String json = mapper.writeValueAsString(info);
        response.getWriter().write(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
