package org.ychen.cms.dao;

import org.springframework.stereotype.Repository;
import org.ychen.basic.dao.BaseDao;
import org.ychen.cms.model.Role;

import java.util.List;

/**
 * Created by cy on 16/12/2.
 */
@Repository("roleDao")
public class RoleDao extends BaseDao<Role> implements IRoleDao {
    @Override
    public List<Role> listRoles() {
        String hql = "from Role";
        return this.list(hql);
    }

    @Override
    public void deleteRoleUsers(int rid) {
        String hql = "delete UserRole ur where ur.role.id=?";
        this.updateByHql(hql,rid);
    }
}
