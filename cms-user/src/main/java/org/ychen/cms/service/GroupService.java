package org.ychen.cms.service;

import org.springframework.stereotype.Service;
import org.ychen.basic.model.Pager;
import org.ychen.cms.dao.IChannelDao;
import org.ychen.cms.dao.IGroupDao;
import org.ychen.cms.dao.IRoleDao;
import org.ychen.cms.dao.IUserDao;
import org.ychen.cms.model.*;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by cy on 16/12/13.
 */
@Service
public class GroupService implements IGroupService {

    @Inject
    private IUserDao userDao;
    @Inject
    private IGroupDao groupDao;
    @Inject
    private IChannelDao channelDao;

    @Override
    public void add(Group group) {
        groupDao.add(group);
    }

    @Override
    public void delete(int gid) {
        List<User> users = userDao.listGroupUsers(gid);
        if (users.size()>0 && users!=null){
            throw new CmsException("删除的组中还有用户");
        }

        groupDao.delete(gid);

    }

    @Override
    public Group load(int id) {
        return groupDao.load(id);
    }

    @Override
    public void update(Group group) {
        groupDao.update(group);
    }

    @Override
    public List<Group> listGroup() {
        return groupDao.listGroup();
    }

    @Override
    public Pager<Group> findGroup() {
        return groupDao.findGroup();
    }

    @Override
    public void deleteGroupUsers(int gid) {
        groupDao.deleteGroupUsers(gid);
    }

    @Override
    public void addGroupChannel(int gid, int cid) {
        Group g = groupDao.load(gid);
        Channel c = channelDao.load(cid);
        if (c==null || g==null) throw new CmsException("要添加的组频道关联对象不存在");
        groupDao.addGroupChannel(g,c);
    }

    @Override
    public GroupChannel loadGroupChannel(int gid, int cid) {
        return groupDao.loadGroupChannel(gid,cid);
    }

    @Override
    public void deleteGroupChannel(int gid, int cid) {
        groupDao.deleteGroupChannel(gid,cid);
    }

    @Override
    public void clearGroupChannel(int gid) {
        groupDao.clearGroupChannel(gid);
    }

    @Override
    public List<Integer> listGroupChannelIds(int gid) {
        return groupDao.listGroupChannelIds(gid);
    }

    @Override
    public List<ChannelTree> generateGroupChannelTree(int gid) {
        return groupDao.generateGroupChannelTree(gid);
    }

    @Override
    public List<ChannelTree> generateUserChannelTree(int uid) {
        return groupDao.generateUserChannelTree(uid);
    }
}
