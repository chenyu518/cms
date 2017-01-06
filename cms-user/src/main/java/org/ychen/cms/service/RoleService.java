package org.ychen.cms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ychen.cms.dao.IRoleDao;
import org.ychen.cms.dao.IUserDao;
import org.ychen.cms.model.CmsException;
import org.ychen.cms.model.Role;
import org.ychen.cms.model.User;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by cy on 16/12/13.
 */
@Service
public class RoleService implements IRoleService {

    @Inject
    private IRoleDao roleDao;
    @Inject
    private IUserDao userDao;

    @Override
    public void add(Role role) {
        roleDao.add(role);
    }

    @Override
    public void delete(int id) {
        List<User> users = userDao.listRoleUsers(id);
        if (users.size()>0 && users!=null){
            new CmsException("删除的角色中还有用户");
        }
        roleDao.delete(id);
    }

    @Override
    public void update(Role role) {
        roleDao.update(role);
    }

    @Override
    public Role load(int id) {
        return roleDao.load(id);
    }

    @Override
    public List<Role> listRole() {
        return roleDao.listRoles();
    }

    @Override
    public void deleteRoleUsers(int rid) {
        roleDao.deleteRoleUsers(rid);
    }
}
