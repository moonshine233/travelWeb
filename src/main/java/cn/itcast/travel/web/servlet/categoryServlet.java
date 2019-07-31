package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.categoryService;
import cn.itcast.travel.service.impl.categoryServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/category/*")
public class categoryServlet extends baseServlet {
    private categoryService service=new categoryServiceImpl();

    /**
     * 导航栏
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findAllCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Category> list = service.findAll();
        ObjectMapper mapper=new ObjectMapper();
        String json = mapper.writeValueAsString(list);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

}
