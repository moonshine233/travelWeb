package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.impl.userDaoImpl;
import cn.itcast.travel.dao.userDao;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.userService;
import cn.itcast.travel.util.MailUtils;
import cn.itcast.travel.util.UuidUtil;
import sun.rmi.server.UnicastServerRef;

public class userServiceImpl implements userService {


    private userDao UserDao=new userDaoImpl();
    /**
     * 注册用户
     * @param user
     * @return
     */
    @Override
    public boolean regist(User user) {

        User u = UserDao.findByUsername(user.getUsername());
        if (u!=null){
            return false;
        }

        user.setCode(UuidUtil.getUuid());
        user.setStatus("N");
        UserDao.save(user);

        String content="<a href='http://localhost/travel/activeUserServlet?code="+user.getCode()+"'>点击激活黑马旅游网</a>";
        System.out.println(user.getCode());
        MailUtils.sendMail(user.getEmail(),content,"激活邮件");

        return true;


    }

    /**
     * 激活用户
     * @param code
     * @return
     */
    @Override
    public boolean active(String code) {

        User user=UserDao.findUserByCode(code);
        if (user!=null){
            UserDao.updateStatus(user);
            return true;
        }else{
            return false;
        }
    }

    /**
     * 登陆
     * @param loginUser
     * @return
     */
    @Override
    public User login(User loginUser) {
        User user=UserDao.findUserByUsernameAndPassword(loginUser.getUsername(),loginUser.getPassword());

        return user;
    }
}
