package org.ychen.cms.dao;

import org.ychen.basic.dao.IBaseDao;
import org.ychen.cms.model.Role;

import java.util.List;

/**
 * Created by cy on 16/12/2.
 */
public interface IRoleDao extends IBaseDao<Role> {

    List<Role> listRoles();
    void deleteRoleUsers(int rid);
}
