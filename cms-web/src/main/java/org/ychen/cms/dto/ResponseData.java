package org.ychen.cms.dto;

import java.util.List;


/**
 * Created by cy on 17/1/10.
 *
 * @author   <a href="mailto:yu.chen@ozstrategy.com">Yu Chen</a>
 * @version  01/11/2017 11:46
 */
public class ResponseData<T> {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  private List<T> data;

  private String msg;

  private Object sdata;

  private boolean success;

  //~ Constructors -----------------------------------------------------------------------------------------------------

    public ResponseData(Object sdata, boolean success) {
        this.sdata = sdata;
        this.success = success;
    }

    /**
   * Creates a new ResponseData object.
   */
  public ResponseData() { }

  /**
   * Creates a new ResponseData object.
   *
   * @param  data  List
   */
  public ResponseData(List<T> data) {
    this.data = data;
  }

  /**
   * Creates a new ResponseData object.
   *
   * @param  data     List
   * @param  success  boolean
   */
  public ResponseData(List<T> data, boolean success) {
    this.data    = data;
    this.success = success;
  }

  /**
   * Creates a new ResponseData object.
   *
   * @param  success  boolean
   * @param  msg      String
   */
  public ResponseData(boolean success, String msg) {
    this.success = success;
    this.msg     = msg;
  }

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * getter method for data.
   *
   * @return  List
   */
  public List<T> getData() {
    return data;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

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
   * getter method for sdata.
   *
   * @return  Object
   */
  public Object getSdata() {
    return sdata;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for success.
   *
   * @return  boolean
   */
  public boolean isSuccess() {
    return success;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for data.
   *
   * @param  data  List
   */
  public void setData(List<T> data) {
    this.data = data;
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
   * setter method for sdata.
   *
   * @param  sdata  Object
   */
  public void setSdata(Object sdata) {
    this.sdata = sdata;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for success.
   *
   * @param  success  boolean
   */
  public void setSuccess(boolean success) {
    this.success = success;
  }
} // end class ResponseData
