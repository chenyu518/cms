package org.ychen.cms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.ychen.cms.model.Role;

import java.util.List;

/**
 * Created by cy on 16/12/13.
 */
public interface IRoleService {

    void add(Role role);
    void delete(int id);
    void update(Role role);
    Role load(int id);

    List<Role> listRole();
    void deleteRoleUsers(int rid);
}
