package org.ychen.cms.service;

import org.ychen.basic.model.Pager;
import org.ychen.cms.model.Group;
import org.ychen.cms.model.Role;
import org.ychen.cms.model.User;
import org.ychen.cms.model.UserRole;

import java.util.List;

/**
 * Created by cy on 16/12/5.
 */
public interface IUserService {

    /**
     * 添加用户, 需要判断用户是否存在, 如果存在抛出异常
     * @param user
     * @param roleIds
     * @param groupIds
     */
    void add(User user, Integer[] roleIds, Integer[] groupIds);

    /**
     * 删除用户,注意需要把用户和角色,组的对应关系删除
     * 如果用户存在相应的文章不能删除
     * @param userId
     */
    void delete(int userId);

    /**
     * 用户更新, 如果roleIds在用户中存在,就不做操作
     * 如果roleIds在用户中不存在,就要添加,如果用户中的角色不存在与roleIds中则要删除
     * 对于group同样的操作
     * @param user
     * @param roleIds
     * @param groupIds
     */
    void update(User user, Integer[] roleIds, Integer[] groupIds);

    /**
     * 更新状态
     * @param id
     */
    void updateStatus(int id);

    /**
     * 列表用户
     */
    Pager<User> findUser();

    /**
     * 获取用户信息
     * @param id
     * @return
     */
    User load(int id);

    /**
     * 获取用户的所有角色信息
     * @param id
     * @return
     */
    List<Role> listUserRoles(int id);

    /**
     * 获取用户的所有组信息
     * @param id
     * @return
     */
    List<Group> listUserGroups(int id);

    List<Integer> listUserRoleIds(int uid);
    List<Integer> listUserGroupIds(int uid);
    List<User> listGroupUsers(int gid);
    List<User> listRoleUsers(int rid);
}
