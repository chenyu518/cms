package org.ychen.basic.dao;

import org.springframework.stereotype.Repository;
import org.ychen.basic.model.Pager;
import org.ychen.basic.model.User;

import java.util.List;
import java.util.Map;

/**
 * Created by cy on 16/12/1.
 */
@Repository(value = "userDao")
public class UserDao extends BaseDao<User> implements IUserDao {
    @Override
    public List<User> listUserBySql(String s, Object[] objects, Class<User> userClass, boolean b) {
        return super.listBySql(s,objects,userClass,b);
    }

    @Override
    public List<User> listUserBySql(String s, Object[] objects, Map<String, Object> alias, Class<User> userClass, boolean b) {
        return super.listBySql(s,objects,alias,userClass,b);
    }

    @Override
    public Pager<User> findUserBySql(String s, Object[] objects, Class<User> userClass, boolean b) {
        return super.findBySql(s,objects,userClass,b);
    }

    @Override
    public Pager<User> findUserBySql(String s, Object[] objects, Map<String, Object> alias, Class<User> userClass, boolean b) {
        return super.findBySql(s,objects,alias,userClass,b);
    }
}
