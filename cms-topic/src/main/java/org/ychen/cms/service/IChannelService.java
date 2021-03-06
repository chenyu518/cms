package org.ychen.cms.service;

import org.ychen.cms.model.Channel;
import org.ychen.cms.model.ChannelTree;

import java.util.List;

/**
 * Created by cy on 16/12/16.
 */
public interface IChannelService {
    /**
     * 添加栏目
     * @param channel
     * @param pid
     */
    public void add(Channel channel,Integer pid);
    /**
     * 更新栏目
     * @param channel
     */
    public void update(Channel channel);
    /**
     * 删除栏目
     * @param id
     */
    public void delete(int id);

    /**
     * 清空栏目中的文章
     * @param id
     */
    public void clearTopic(int id);

    public Channel load(int id);
    /**
     * 根据父亲id加载栏目，该方面首先检查SystemContext中是否存在排序如果没有存在把orders加进去
     * @param pid
     * @return
     */
    public List<Channel> listByParent(Integer pid);

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

    public void updateSort(Integer[] ids);
}
