package cn.itcast.travel.web.servlet;

import cn.itcast.travel.service.impl.userServiceImpl;
import cn.itcast.travel.service.userService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/activeUserServlet")
public class activeUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /**
         * 点击邮箱的链接激活账号
         */
        String code = request.getParameter("code");
        if (code!=null){

            userService service=new userServiceImpl();
            boolean flag=service.active(code);

            String msg=null;
            if (flag){
                msg="激活成功，请<a href='login.html'>登录</a>";
            }else {
                msg="激活失败！";
            }
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(msg);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}


