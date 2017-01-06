package org.ychen.cms.model;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;


/**
 * Created by cy on 16/12/2.
 *
 * @author   <a href="mailto:yu.chen@ozstrategy.com">Yu Chen</a>
 * @version  12/02/2016 14:07
 */
@Entity
@Table(name = "t_user")
public class User {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  @Column(name = "create_date")
  private Date createDate;

  @Email(message = "邮件格式不正确")
  private String                  email;
  @GeneratedValue @Id private int id;
  private String                  nickname;

  @NotNull(message = "密码不能为空")
  private String password;
  private String phone;
  private int    status;

  @NotNull(message = "用户不能为空")
  private String username;

  //~ Constructors -----------------------------------------------------------------------------------------------------

  /**
   * Creates a new User object.
   */
  public User() { }

  /**
   * Creates a new User object.
   *
   * @param  id        int
   * @param  username  String
   * @param  password  String
   * @param  nickname  String
   * @param  email     String
   * @param  phone     String
   * @param  status    int
   */
  public User(int id, String username, String password, String nickname,
    String email, String phone, int status) {
    super();
    this.id       = id;
    this.username = username;
    this.password = password;
    this.nickname = nickname;
    this.email    = email;
    this.phone    = phone;
    this.status   = status;
  }

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * getter method for create date.
   *
   * @return  Date
   */
  public Date getCreateDate() {
    return createDate;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for email.
   *
   * @return  String
   */
  public String getEmail() {
    return email;
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
   * getter method for nickname.
   *
   * @return  String
   */
  public String getNickname() {
    return nickname;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for password.
   *
   * @return  String
   */
  public String getPassword() {
    return password;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for phone.
   *
   * @return  String
   */
  public String getPhone() {
    return phone;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for status.
   *
   * @return  int
   */
  public int getStatus() {
    return status;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for username.
   *
   * @return  String
   */
  public String getUsername() {
    return username;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for create date.
   *
   * @param  createDate  Date
   */
  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for email.
   *
   * @param  email  String
   */
  public void setEmail(String email) {
    this.email = email;
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
   * setter method for nickname.
   *
   * @param  nickname  String
   */
  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for password.
   *
   * @param  password  String
   */
  public void setPassword(String password) {
    this.password = password;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for phone.
   *
   * @param  phone  String
   */
  public void setPhone(String phone) {
    this.phone = phone;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for status.
   *
   * @param  status  int
   */
  public void setStatus(int status) {
    this.status = status;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for username.
   *
   * @param  username  String
   */
  public void setUsername(String username) {
    this.username = username;
  }
} // end class User
