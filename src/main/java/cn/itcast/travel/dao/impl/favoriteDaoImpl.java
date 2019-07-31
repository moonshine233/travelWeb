package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.favoriteDao;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;

public class favoriteDaoImpl implements favoriteDao {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public Favorite findByRidAndUid(int rid, int uid) {
        try{
            String sql="select * from tab_favorite where rid=? and uid=?";
            Favorite favorite = template.queryForObject(sql, new BeanPropertyRowMapper<Favorite>(Favorite.class), rid, uid);
            return favorite;
        }catch (Exception e){

        }
        return null;
    }

    @Override
    public int finCountByRid(int rid) {
        String sql="select count(*) from tab_favorite where rid=?";
        return template.queryForObject(sql, int.class, rid);
    }

    @Override
    public void addFavorite(int rid, int uid) {
        String sql="insert into tab_favorite values(?,?,?)";
        template.update(sql,rid,new Date(),uid);
    }
}
