package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.routeDao;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class routeDaoImpl implements routeDao {
    JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public int findTotalCount(int cid,String rname) {
        //String sql="select count(*) from tab_route where cid=?";
        String sql="select count(*) from tab_route where 1=1 ";
        StringBuilder sb=new StringBuilder(sql);

        List parms=new ArrayList();
        if (cid!=0){
            sb.append(" and cid=? ");
            parms.add(cid);
        }
        if (rname!=null&&rname.length()!=0&&!"null".equals(rname)){
            sb.append(" and rname like ? ");
            parms.add("%"+rname+"%");
        }
        sql=sb.toString();

        return template.queryForObject(sql,Integer.class,parms.toArray());
    }

    @Override
    public List<Route> findByPage(int cid, int start, int pageSize,String rname) {
        //String sql="select * from tab_route where cid=? limit ?,?";

        String sql="select * from tab_route where 1=1 ";

        StringBuilder sb=new StringBuilder(sql);
        List parms=new ArrayList();
        if (cid!=0){
            sb.append(" and cid=? ");
            parms.add(cid);
        }
        if (rname!=null&&rname.length()!=0&&!"null".equals(rname)){
            sb.append(" and rname like ? ");
            parms.add("%"+rname+"%");
        }

        sb.append(" limit ?,? ");
        parms.add(start);
        parms.add(pageSize);
        sql=sb.toString();

        return template.query(sql,new BeanPropertyRowMapper<Route>(Route.class),parms.toArray());
    }

    @Override
    public Route findOne(int rid) {

        String sql="select * from tab_route where rid=?";
        Route route = template.queryForObject(sql, new BeanPropertyRowMapper<Route>(Route.class), rid);
        return route;
    }
}
