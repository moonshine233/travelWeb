package cn.itcast.travel.service;

public interface favoriteServlet {

    public boolean isFavorite(String rid,int uid);

    void addFavorite(String rid, int uid);
}
