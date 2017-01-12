package org.ychen.cms.dto;

import org.ychen.cms.model.RoleType;

/**
 * Created by cy on 17/1/10.
 */
public class RoleCommand {
    private String roleName;
    private RoleType info;

    public RoleCommand() {
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public RoleType getInfo() {
        return info;
    }

    public void setInfo(RoleType info) {
        this.info = info;
    }
}
