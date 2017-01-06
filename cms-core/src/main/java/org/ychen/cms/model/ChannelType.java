package org.ychen.cms.model;

/**
 * Created by cy on 16/12/16.
 *
 * @author   <a href="mailto:yu.chen@ozstrategy.com">Yu Chen</a>
 * @version  12/16/2016 09:49
 */
public enum ChannelType {
  //~ Enum constants ---------------------------------------------------------------------------------------------------

  NAV_CHANNEL("导航栏目"), TOPIC_LIST("文章列表栏目"), TOPIC_CONTENT("文章内容栏目"), TOPIC_IMG("图片列表栏目");

  //~ Instance fields --------------------------------------------------------------------------------------------------

  private String name;

  //~ Constructors -----------------------------------------------------------------------------------------------------

  private ChannelType(String name) {
    this.name = name;
  }

  //~ Methods ----------------------------------------------------------------------------------------------------------

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
   * setter method for name.
   *
   * @param  name  String
   */
  public void setName(String name) {
    this.name = name;
  }
}
