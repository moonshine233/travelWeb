package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.favoriteServlet;
import cn.itcast.travel.service.impl.favoriteServletImpl;
import cn.itcast.travel.service.impl.routeServiceImpl;
import cn.itcast.travel.service.routeService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/route/*")
public class routeServlet extends baseServlet {

    private routeService service=new routeServiceImpl();
    private favoriteServlet favoriteService=new favoriteServletImpl();

    /**
     * 分页查询
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String currentPageStr = request.getParameter("currentPage");//当前页数
        String pageSizeStr = request.getParameter("pageSize");//每页显示数目
        String cidStr = request.getParameter("cid");//导航栏id

        String rname = request.getParameter("rname");//搜索内容
        rname=new String(rname.getBytes("iso-8859-1"),"utf-8");


        int cid=0;
        if (cidStr!=null&&cidStr.length()!=0&&!"null".equals(cidStr)){
            cid = Integer.parseInt(cidStr);
        }
        int currentPage=0;//当前页码
        if (currentPageStr!=null&&currentPageStr.length()!=0){
            currentPage = Integer.parseInt(currentPageStr);
        }else {
            currentPage=1;
        }

        int pageSize=0;//每页显示条数
        if (pageSizeStr!=null&&pageSizeStr.length()!=0){
            pageSize = Integer.parseInt(pageSizeStr);
        }else {
            pageSize=5;
        }
        //调用service 查询Pagebean对象
        PageBean<Route> pb=service.pageQuery(cid, currentPage, pageSize,rname);

        ObjectMapper mapper=new ObjectMapper();
        String json = mapper.writeValueAsString(pb);
        System.out.println(json);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

    /**
     * 详情页
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String ridStr = request.getParameter("rid");

        int rid=0;
        if (ridStr!=null&&ridStr.length()!=0&&!"null".equals(ridStr)){
            rid=Integer.parseInt(ridStr);
        }

        routeService service=new routeServiceImpl();
        Route route=service.findOne(rid);
        ObjectMapper mapper=new ObjectMapper();
        String json = mapper.writeValueAsString(route);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

    /**
     * 判断用户是否收藏过该线路
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */

    public void isFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String rid = request.getParameter("rid");
        User user = (User) request.getSession().getAttribute("user");
        int uid=0;
        if (user==null){

        }else {
            uid=user.getUid();
        }
        boolean flag = favoriteService.isFavorite(rid, uid);

        ObjectMapper mapper=new ObjectMapper();
        String json = mapper.writeValueAsString(flag);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

    public void addFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rid = request.getParameter("rid");
        User user = (User) request.getSession().getAttribute("user");
        int uid;
        if (user==null){

            return;
        }else {
            uid=user.getUid();
        }

        favoriteService.addFavorite(rid,uid);

    }




}
