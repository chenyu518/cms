package org.ychen.cms.model;

/**
 * Created by cy on 16/12/2.
 */

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 用户组对象，使用该对象来获取可以发布文章的栏目信息.
 *
 * @author   Administrator
 * @version  12/02/2016 14:45
 */
@Entity
@Table(name = "t_group")
public class Group {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  /** 组描述信息. */
  private String descr;

  /** 组id. */
  @GeneratedValue @Id private int id;

  /** 组名称. */
  private String name;

  //~ Constructors -----------------------------------------------------------------------------------------------------

  /**
   * Creates a new Group object.
   */
  public Group() { }


  /**
   * Creates a new Group object.
   *
   * @param  id    int
   * @param  name  String
   */
  public Group(int id, String name) {
    super();
    this.id   = id;
    this.name = name;
  }

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * getter method for descr.
   *
   * @return  String
   */
  public String getDescr() {
    return descr;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

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
   * setter method for descr.
   *
   * @param  descr  String
   */
  public void setDescr(String descr) {
    this.descr = descr;
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


} // end class Group
