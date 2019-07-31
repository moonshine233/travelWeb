package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.RouteImg;

import java.util.List;

public interface routeImgDao {
    List<RouteImg> findOne(int rid);
}
