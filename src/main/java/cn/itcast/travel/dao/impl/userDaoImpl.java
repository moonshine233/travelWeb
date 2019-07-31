package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.userDao;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class userDaoImpl implements userDao {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public User findByUsername(String username) {
        User user=null;
        try{
            String sql="select * from tab_user where username=?";

            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);

        }catch (Exception e){
            return null;
        }

        return user;
    }

    @Override
    public void save(User user) {
        String sql="insert into tab_user(username,password,name,birthday,sex,telephone,email,status,code) values(?,?,?,?,?,?,?,?,?)";

        template.update(sql,user.getUsername(),user.getPassword(),user.getName(),user.getBirthday(),user.getSex(),user.getTelephone(),user.getEmail(),user.getStatus(),user.getCode());
    }

    @Override
    public User findUserByCode(String code) {
        try{
            String sql="select * from tab_user where code=?";
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), code);
            return user;
        }catch (Exception e){

        }
        return null;
    }

    @Override
    public void updateStatus(User user) {

        String sql="update tab_user set status='Y' where uid=?";
        template.update(sql,user.getUid());

    }

    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        User user=null;
        try{
            String sql="select * from tab_user where username=? and password=?";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username, password);
        }catch (Exception e){

        }
        return user;
    }
}
