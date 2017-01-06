package org.ychen.cms.dto;

/**
 * Created by cy on 16/12/26.
 *
 * @author   <a href="mailto:yu.chen@ozstrategy.com">Yu Chen</a>
 * @version  12/26/2016 10:43
 */
public class AjaxObj {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  /** 提示消息. */
  private String msg;

  /** 附加对象, 用来存储一些特定的返回信息. */
  private Object object;

  /** 0:success 1:failure. */
  private int result;

  //~ Constructors -----------------------------------------------------------------------------------------------------

  /**
   * Creates a new AjaxObj object.
   */
  public AjaxObj() { }


  /**
   * Creates a new AjaxObj object.
   *
   * @param  result  int
   */
  public AjaxObj(int result) {
    this.result = result;
  }

  /**
   * Creates a new AjaxObj object.
   *
   * @param  result  int
   * @param  msg     String
   */


  public AjaxObj(int result, String msg) {
    this.result = result;
    this.msg    = msg;
  }

  /**
   * Creates a new AjaxObj object.
   *
   * @param  msg     String
   * @param  object  Object
   * @param  result  int
   */
  public AjaxObj(String msg, Object object, int result) {
    this.msg    = msg;
    this.object = object;
    this.result = result;
  }

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * getter method for msg.
   *
   * @return  String
   */
  public String getMsg() {
    return msg;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for result.
   *
   * @return  int
   */
  public int getResult() {
    return result;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for msg.
   *
   * @param  msg  String
   */
  public void setMsg(String msg) {
    this.msg = msg;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for result.
   *
   * @param  result  int
   */
  public void setResult(int result) {
    this.result = result;
  }
} // end class AjaxObj
