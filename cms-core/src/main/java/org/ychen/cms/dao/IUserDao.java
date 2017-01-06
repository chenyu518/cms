package org.ychen.cms.dao;

import java.util.List;

import org.ychen.basic.dao.IBaseDao;

import org.ychen.basic.model.Pager;
import org.ychen.cms.model.*;


/**
 * Created by cy on 16/12/2.
 *
 * @author   <a href="mailto:yu.chen@ozstrategy.com">Yu Chen</a>
 * @version  12/02/2016 15:29
 */
public interface IUserDao extends IBaseDao<User> {
  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * 获取某个组中的用户对象.
   *
   * @param   groupId  $param.type$
   *
   * @return  获取某个组中的用户对象.
   */
  List<User> listGroupUsers(int groupId);

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * 根据角色id获取用户列表.
   *
   * @param   roleId  $param.type$
   *
   * @return  根据角色id获取用户列表.
   */
  List<User> listRoleUsers(int roleId);

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * 根据角色类型获取用户列表.
   *
   * @param   roleType  $param.type$
   *
   * @return  根据角色类型获取用户列表.
   */
  List<User> listRoleUsers(RoleType roleType);

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * 获取用户所有组信息.
   *
   * @param   userId  $param.type$
   *
   * @return  获取用户所有组信息.
   */
  List<Group> listUserGroups(int userId);

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * 获取用户的所有组id.
   *
   * @param   userId  $param.type$
   *
   * @return  获取用户的所有组id.
   */
  List<Integer> listUserGroupsIds(int userId);

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * 获取用户所有角色信息.
   *
   * @param   userId  $param.type$
   *
   * @return  获取用户所有角色信息.
   */
  List<Role> listUserRoles(int userId);

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * 获取用户的所有角色id.
   *
   * @param   userId  $param.type$
   *
   * @return  获取用户的所有角色id.
   */
  List<Integer> listUserRolesIds(int userId);

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * 根据用户名获取用户对象.
   *
   * @param   username  String
   *
   * @return  User
   */
  User loadByUsername(String username);

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * 根据用户和组获取用户组的关联对象.
   *
   * @param   userId   int
   * @param   groupId  int
   *
   * @return  UserRole
   */
  UserGroup loadUserGroup(int userId, int groupId);

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * 根据用户和角色获取用户角色的关联对象.
   *
   * @param   userId  int
   * @param   roleId  int
   *
   * @return  UserRole
   */
  UserRole loadUserRole(int userId, int roleId);

  /**
   * 添加用户角色对象
   * @param user
   * @param role
   */
  void addUserRole(User user, Role role);

  /**
   * 添加用户组对象
   * @param user
   * @param group
   */
  void addUserGroup(User user, Group group);

  void deleteUserRoles(int userId);

  void deleteUserGroups(int userId);

  public Pager<User> findUser();

  public void deleteUserRole(int uid, int rid);
  public void deleteUserGroup(int uid, int gid);
} // end interface IUserDao
