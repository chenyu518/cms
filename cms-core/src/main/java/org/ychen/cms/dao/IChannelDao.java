package org.ychen.cms.dao;

import org.ychen.basic.dao.IBaseDao;
import org.ychen.cms.model.Channel;
import org.ychen.cms.model.ChannelTree;

import java.util.List;

/**
 * Created by cy on 16/12/16.
 */
public interface IChannelDao extends IBaseDao<Channel> {

    /**
     * 根据父id获取所有的子栏目
     * @param pid
     * @return
     */
    public List<Channel> listByParent(Integer pid);
    /**
     * 获取子栏目的最大的排序号
     * @param pid
     * @return
     */
    public int getMaxOrderByParent(Integer pid);
    /**
     * 把所有的栏目获取并生成一颗完整的树
     * @return
     */
    public List<ChannelTree> generateTree();
    /**
     * 根据父类对象获取子类栏目，并且生成树列表
     * @param pid
     * @return
     */
    public List<ChannelTree> generateTreeByParent(Integer pid);
    /**
     * 通过一个数组来完成排序
     * @param ids
     */
    public void updateSort(Integer[] ids);
}
