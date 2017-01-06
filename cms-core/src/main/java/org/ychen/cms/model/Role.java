package org.ychen.cms.model;

import javax.persistence.*;


/**
 * Created by cy on 16/12/2.
 *
 * @author   <a href="mailto:yu.chen@ozstrategy.com">Yu Chen</a>
 * @version  12/13/2016 11:25
 */
@Entity
@Table(name = "t_role")
public class Role {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  /** 角色id. */
  @GeneratedValue @Id private int id;

  /** 角色的名称，中文. */
  private String name;

  /** 角色的编号，枚举类型. */
  @Column(name = "role_type")
  @Enumerated(EnumType.STRING)
  private RoleType roleType;

  //~ Constructors -----------------------------------------------------------------------------------------------------

  /**
   * Creates a new Role object.
   */
  public Role() { }

  /**
   * Creates a new Role object.
   *
   * @param  id        int
   * @param  name      String
   * @param  roleType  RoleType
   */
  public Role(int id, String name, RoleType roleType) {
    this.id       = id;
    this.name     = name;
    this.roleType = roleType;
  }

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * getter method for id.
   *
   * @return  int
   */
  public int getId() {
    return id;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for name.
   *
   * @return  String
   */
  public String getName() {
    return name;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for role type.
   *
   * @return  RoleType
   */
  public RoleType getRoleType() {
    return roleType;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for id.
   *
   * @param  id  int
   */
  public void setId(int id) {
    this.id = id;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for name.
   *
   * @param  name  String
   */
  public void setName(String name) {
    this.name = name;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for role type.
   *
   * @param  roleType  RoleType
   */
  public void setRoleType(RoleType roleType) {
    this.roleType = roleType;
  }
} // end class Role
