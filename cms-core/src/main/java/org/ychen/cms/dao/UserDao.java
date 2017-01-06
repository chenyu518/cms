package org.ychen.cms.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import org.ychen.basic.dao.BaseDao;
import org.ychen.basic.model.Pager;

import org.ychen.cms.model.*;


/**
 * Created by cy on 16/12/2.
 *
 * @author   <a href="mailto:yu.chen@ozstrategy.com">Yu Chen</a>
 * @version  12/13/2016 11:11
 */
@Repository("userDao")
public class UserDao extends BaseDao<User> implements IUserDao {
  //~ Methods ----------------------------------------------------------------------------------------------------------


  /**
   * @see  org.ychen.cms.dao.IUserDao#addUserGroup(org.ychen.cms.model.User, org.ychen.cms.model.Group)
   */
  @Override public void addUserGroup(User user, Group group) {
    UserGroup userGroup = this.loadUserGroup(user.getId(), group.getId());

    if (userGroup != null) {
      return;
    }

    userGroup = new UserGroup();
    userGroup.setUser(user);
    userGroup.setGroup(group);
    this.getSession().save(userGroup);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * @see  org.ychen.cms.dao.IUserDao#addUserRole(org.ychen.cms.model.User, org.ychen.cms.model.Role)
   */
  @Override public void addUserRole(User user, Role role) {
    UserRole userRole = this.loadUserRole(user.getId(), role.getId());

    if (userRole != null) {
      return;
    }

    userRole = new UserRole();
    userRole.setUser(user);
    userRole.setRole(role);
    this.getSession().save(userRole);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * @see  org.ychen.cms.dao.IUserDao#deleteUserGroup(int, int)
   */
  @Override public void deleteUserGroup(int uid, int gid) {
    String hql = "delete UserGroup ug where ug.user.id=? and ug.group.id=?";
    this.updateByHql(hql, new Object[] { uid, gid });
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * @see  org.ychen.cms.dao.IUserDao#deleteUserGroups(int)
   */
  @Override public void deleteUserGroups(int userId) {
    String hql = "delete UserGroup ug where ug.user.id=?";
    this.updateByHql(hql, userId);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * @see  org.ychen.cms.dao.IUserDao#deleteUserRole(int, int)
   */
  @Override public void deleteUserRole(int uid, int rid) {
    String hql = "delete UserRole ur where ur.user.id=? and ur.role.id=?";
    this.updateByHql(hql, new Object[] { uid, rid });
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * @see  org.ychen.cms.dao.IUserDao#deleteUserRoles(int)
   */
  @Override public void deleteUserRoles(int userId) {
    String hql = "delete  UserRole ur where ur.user.id=?";
    this.updateByHql(hql, userId);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * @see  org.ychen.cms.dao.IUserDao#findUser()
   */
  @Override public Pager<User> findUser() {
    String hql = "from User";

    return this.find(hql);
  }

  //~ ------------------------------------------------------------------------------------------------------------------


  /**
   * @see  org.ychen.cms.dao.IUserDao#listGroupUsers(int)
   */
  @Override public List<User> listGroupUsers(int groupId) {
    String hql = "select ug.user from UserGroup ug where ug.group.id=?";

    return this.list(hql, groupId);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * @see  org.ychen.cms.dao.IUserDao#listRoleUsers(int)
   */
  @Override public List<User> listRoleUsers(int roleId) {
    String hql = "select ur.user from UserRole ur where ur.role.id=?";

    return this.list(hql, roleId);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * @see  org.ychen.cms.dao.IUserDao#listRoleUsers(org.ychen.cms.model.RoleType)
   */
  @Override public List<User> listRoleUsers(RoleType roleType) {
    String hql = "select ur.user from UserRole ur where ur.role.roleType=?";

    return this.list(hql, roleType);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * @see  org.ychen.cms.dao.IUserDao#listUserGroups(int)
   */
  @Override public List<Group> listUserGroups(int userId) {
    String hql = "select ug.group from UserGroup ug where ug.user.id=?";

    return this.getSession().createQuery(hql).setParameter(0, userId).list();
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * @see  org.ychen.cms.dao.IUserDao#listUserGroupsIds(int)
   */
  @Override public List<Integer> listUserGroupsIds(int userId) {
    String hql = "select ug.group.id from UserGroup ug where ug.user.id=?";

    return this.getSession().createQuery(hql).setParameter(0, userId).list();
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * @see  org.ychen.cms.dao.IUserDao#listUserRoles(int)
   */
  @Override public List<Role> listUserRoles(int userId) {
    String hql = "select ur.role from UserRole ur where ur.user.id=?";

    return this.getSession().createQuery(hql).setParameter(0, userId).list();
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * @see  org.ychen.cms.dao.IUserDao#listUserRolesIds(int)
   */
  @Override public List<Integer> listUserRolesIds(int userId) {
    String hql = "select ur.role.id from UserRole ur where ur.user.id=?";

    return this.getSession().createQuery(hql).setParameter(0, userId).list();
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * @see  org.ychen.cms.dao.IUserDao#loadByUsername(java.lang.String)
   */
  @Override public User loadByUsername(String username) {
    String hql = "from User where username=?";

    return (User) this.queryObject(hql, username);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * @see  org.ychen.cms.dao.IUserDao#loadUserGroup(int, int)
   */
  @Override public UserGroup loadUserGroup(int userId, int groupId) {
    String hql =
      "select ug from UserGroup ug left join fetch ug.user u left join fetch ug.group g where u.id=? and g.id=?";

    return (UserGroup) this.getSession().createQuery(hql).setParameter(0, userId).setParameter(1, groupId)
      .uniqueResult();
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * @see  org.ychen.cms.dao.IUserDao#loadUserRole(int, int)
   */
  @Override public UserRole loadUserRole(int userId, int roleId) {
    String hql =
      "select ur from UserRole ur left join fetch ur.user u left join fetch ur.role r where u.id=? and r.id=?";

    return (UserRole) this.getSession().createQuery(hql).setParameter(0, userId).setParameter(1, roleId).uniqueResult();
  }
} // end class UserDao
