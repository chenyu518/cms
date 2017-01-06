package org.ychen.cms.service;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.stereotype.Service;
import org.ychen.basic.model.Pager;
import org.ychen.cms.dao.IGroupDao;
import org.ychen.cms.dao.IRoleDao;
import org.ychen.cms.dao.IUserDao;
import org.ychen.cms.model.CmsException;
import org.ychen.cms.model.Group;
import org.ychen.cms.model.Role;
import org.ychen.cms.model.User;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

/**
 * Created by cy on 16/12/5.
 */
@Service
public class UserService implements IUserService {

    @Inject
    private IUserDao userDao;
    @Inject
    private IRoleDao roleDao;
    @Inject
    private IGroupDao groupDao;

    public IUserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    public IRoleDao getRoleDao() {
        return roleDao;
    }

    public void setRoleDao(IRoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public IGroupDao getGroupDao() {
        return groupDao;
    }

    public void setGroupDao(IGroupDao groupDao) {
        this.groupDao = groupDao;
    }

    private void addUserRole(User user, int rid){
        Role role = roleDao.load(rid);
        if(role==null){
            throw new CmsException("要添加的用户角色不存在");
        }
        userDao.addUserRole(user,role);
    }

    private void addUserGroup(User user, int gid){
        Group group = groupDao.load(gid);
        if(group==null){
            throw new CmsException("要添加的用户组不存在");
        }
        userDao.addUserGroup(user,group);
    }

    @Override
    public void add(User user, Integer[] roleIds, Integer[] groupIds) {
        User u = userDao.loadByUsername(user.getUsername());
        if(u!=null){
           throw  new CmsException("添加的用户已存在");
        }
        user.setCreateDate(new Date());
        userDao.add(user);
        for (Integer rid : roleIds){
            this.addUserRole(user,rid);
        }
        for (Integer gid : groupIds){
            this.addUserGroup(user,gid);
        }
    }

    @Override
    public void delete(int userId) {
        //TODO 需要判断用户是否有文章存在
        userDao.deleteUserGroups(userId);
        userDao.deleteUserRoles(userId);
        userDao.delete(userId);
    }

    @Override
    public void update(User user, Integer[] roleIds, Integer[] groupIds) {
        List<Integer> rIds =  userDao.listUserRolesIds(user.getId());
        List<Integer> gIds = userDao.listUserGroupsIds(user.getId());
        for (Integer rid : roleIds){
            if (!rIds.contains(rid)){
                this.addUserRole(user,rid);
            }
        }
        for (Integer gid : groupIds){
            if(!gIds.contains(gid)){
                this.addUserGroup(user,gid);
            }
        }
        //删除
        for (Integer rid : rIds){
            if (!ArrayUtils.contains(roleIds,rid)){
                userDao.deleteUserRole(user.getId(),rid);
            }
        }
        for (Integer gid : gIds){
            if (!ArrayUtils.contains(groupIds,gid)){
                userDao.deleteUserGroup(user.getId(),gid);
            }
        }

    }

    @Override
    public void updateStatus(int id) {
        User u = userDao.load(id);
        if(u==null) throw new CmsException("修改状态的用户不存在");
        if (u.getStatus()==1){
            u.setStatus(0);
        }else {
            u.setStatus(1);
        }
        userDao.update(u);
    }

    @Override
    public Pager<User> findUser() {
        return userDao.findUser();
    }

    @Override
    public User load(int id) {
        return userDao.load(id);
    }

    @Override
    public List<Role> listUserRoles(int id) {
        return userDao.listUserRoles(id);
    }

    @Override
    public List<Group> listUserGroups(int id) {
        return userDao.listUserGroups(id);
    }

    @Override
    public List<Integer> listUserRoleIds(int uid) {
        return userDao.listUserRolesIds(uid);
    }

    @Override
    public List<Integer> listUserGroupIds(int uid) {
        return userDao.listUserGroupsIds(uid);
    }

    @Override
    public List<User> listGroupUsers(int gid) {
        return userDao.listGroupUsers(gid);
    }

    @Override
    public List<User> listRoleUsers(int rid) {
        return userDao.listRoleUsers(rid);
    }
}
