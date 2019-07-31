package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.categoryDao;
import cn.itcast.travel.dao.impl.categoryDaoImpl;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.categoryService;
import cn.itcast.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

public class categoryServiceImpl implements categoryService {
    private categoryDao CategoryDao=new categoryDaoImpl();

    /**
     * 获取导航栏信息
     * @return
     */
    @Override
    public List<Category> findAll() {

        Jedis jedis= JedisUtil.getJedis();
        //Set<String> categorys = jedis.zrange("category", 0, -1);
        Set<Tuple> categorys = jedis.zrangeWithScores("category", 0, -1);

        List<Category> cs =null;
        if (categorys==null||categorys.size()==0){
            cs= CategoryDao.findAll();
            for (int i = 0; i < cs.size(); i++) {
                jedis.zadd("category",cs.get(i).getCid(),cs.get(i).getCname());
            }
        }else{
            cs=new ArrayList<Category>();
            for (Tuple tuple : categorys) {
                Category category=new Category();
                category.setCname(tuple.getElement());
                category.setCid((int)tuple.getScore());
                cs.add(category);
            }
        }
        return cs;
    }
}
