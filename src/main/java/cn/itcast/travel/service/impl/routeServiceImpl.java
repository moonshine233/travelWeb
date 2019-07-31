package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.favoriteDao;
import cn.itcast.travel.dao.impl.favoriteDaoImpl;
import cn.itcast.travel.dao.impl.routeDaoImpl;
import cn.itcast.travel.dao.impl.routeImgDaoImpl;
import cn.itcast.travel.dao.impl.sellerDaoImpl;
import cn.itcast.travel.dao.routeDao;
import cn.itcast.travel.dao.routeImgDao;
import cn.itcast.travel.dao.sellerDao;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.service.routeService;

import java.util.List;

public class routeServiceImpl implements routeService {
    private routeDao routeDao=new routeDaoImpl();
    private routeImgDao routeImgDao =new routeImgDaoImpl();
    private sellerDao sellerDao =new sellerDaoImpl();
    private favoriteDao favoriteDao=new favoriteDaoImpl();

    /**
     * 分页
     * @param cid
     * @param currentPage
     * @param pageSize
     * @param rname
     * @return
     */
    @Override
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize,String rname) {

        PageBean<Route> pb=new PageBean<Route>();
        pb.setCurrentPage(currentPage);
        pb.setPageSize(pageSize);

        int totalCount=routeDao.findTotalCount(cid,rname);
        pb.setTotalCount(totalCount);
        System.out.println(totalCount);

        int start=(currentPage-1)*pageSize;
        List<Route> list=routeDao.findByPage(cid,start,pageSize,rname);
        pb.setList(list);

        int totalPage=totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
        pb.setTotalPage(totalPage);


        return pb;
    }

    /**
     * 详情页
     * @param rid
     * @return
     */
    @Override
    public Route findOne(int rid) {
        Route route = routeDao.findOne(rid);
        List<RouteImg> list=routeImgDao.findOne(rid);
        route.setRouteImgList(list);
        Seller seller=sellerDao.findOne(route.getSid());
        route.setSeller(seller);
        int count=favoriteDao.finCountByRid(rid);
        route.setCount(count);
        return route;
    }
}
