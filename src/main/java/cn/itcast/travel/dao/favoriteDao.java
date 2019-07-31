package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Favorite;

public interface favoriteDao {
    public Favorite findByRidAndUid(int rid, int uid);

    int finCountByRid(int rid);

    void addFavorite(int rid, int uid);
}
