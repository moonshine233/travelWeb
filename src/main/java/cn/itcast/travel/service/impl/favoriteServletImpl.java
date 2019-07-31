package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.favoriteDao;
import cn.itcast.travel.dao.impl.favoriteDaoImpl;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.service.favoriteServlet;

public class favoriteServletImpl implements favoriteServlet {
    private favoriteDao favoriteDao=new favoriteDaoImpl();
    @Override
    public boolean isFavorite(String rid, int uid) {

        Favorite favorite = favoriteDao.findByRidAndUid(Integer.parseInt(rid),uid);
        boolean flag=false;
        if (favorite!=null){
            flag=true;
        }
        return flag;
    }

    @Override
    public void addFavorite(String rid, int uid) {
        favoriteDao.addFavorite(Integer.parseInt(rid),uid);
    }
}
