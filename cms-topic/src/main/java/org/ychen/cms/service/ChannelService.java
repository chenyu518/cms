package org.ychen.cms.service;

import org.springframework.stereotype.Service;
import org.ychen.cms.dao.IChannelDao;
import org.ychen.cms.model.Channel;
import org.ychen.cms.model.ChannelTree;
import org.ychen.cms.model.CmsException;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by cy on 16/12/19.
 */
@Service("channelService")
public class ChannelService implements IChannelService {

    private IChannelDao channelDao;

    public IChannelDao getChannelDao() {
        return channelDao;
    }
    @Inject
    public void setChannelDao(IChannelDao channelDao) {
        this.channelDao = channelDao;
    }

    public void add(Channel channel, Integer pid) {
        Integer orders = channelDao.getMaxOrderByParent(pid);
        if(pid!=null&&pid>0) {
            Channel pc = channelDao.load(pid);
            if(pc==null) throw new CmsException("要添加栏目的父类对象不正确!");
            channel.setParent(pc);
        }
        channel.setOrders(orders+1);
        channelDao.add(channel);
    }

    public void update(Channel channel) {
        channelDao.update(channel);
    }

    public void delete(int id) {
        //1、需要判断是否存在子栏目
        List<Channel> cs = channelDao.listByParent(id);
        if(cs!=null&&cs.size()>0) throw new CmsException("要删除的栏目还有子栏目，无法删除");
        //TODO 2、需要判断是否存在文章
        //TODO 3、需要删除和组的关联关系

        channelDao.delete(id);
    }

    public void clearTopic(int id) {
        //TODO 实现了文章模块之后才实现该方法
    }

    public Channel load(int id) {
        return channelDao.load(id);
    }

    public List<Channel> listByParent(Integer pid) {
        return channelDao.listByParent(pid);
    }
    @Override
    public List<ChannelTree> generateTree() {
        return channelDao.generateTree();
    }
    @Override
    public List<ChannelTree> generateTreeByParent(Integer pid) {
        return channelDao.generateTreeByParent(pid);
    }
    @Override
    public void updateSort(Integer[] ids) {
        channelDao.updateSort(ids);
    }


}
