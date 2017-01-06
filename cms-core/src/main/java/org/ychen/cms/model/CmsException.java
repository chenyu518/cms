package org.ychen.cms.model;

/**
 * Created by cy on 16/12/5.
 *
 * @author   <a href="mailto:yu.chen@ozstrategy.com">Yu Chen</a>
 * @version  12/05/2016 16:43
 */
public class CmsException extends RuntimeException {
  //~ Constructors -----------------------------------------------------------------------------------------------------

  /**
   * Creates a new CmsException object.
   */
  public CmsException() {
    super();
  }

  /**
   * Creates a new CmsException object.
   *
   * @param  s  String
   */
  public CmsException(String s) {
    super(s);
  }

  /**
   * Creates a new CmsException object.
   *
   * @param  throwable  Throwable
   */
  public CmsException(Throwable throwable) {
    super(throwable);
  }

  /**
   * Creates a new CmsException object.
   *
   * @param  s          String
   * @param  throwable  Throwable
   */
  public CmsException(String s, Throwable throwable) {
    super(s, throwable);
  }
} // end class CmsException
