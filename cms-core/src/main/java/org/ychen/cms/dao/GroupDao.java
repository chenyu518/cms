package org.ychen.cms.dao;

import org.springframework.stereotype.Repository;
import org.ychen.basic.dao.BaseDao;
import org.ychen.basic.model.Pager;
import org.ychen.cms.model.Channel;
import org.ychen.cms.model.ChannelTree;
import org.ychen.cms.model.Group;
import org.ychen.cms.model.GroupChannel;

import java.util.List;

/**
 * Created by cy on 16/12/2.
 */
@Repository("groupDao")
public class GroupDao extends BaseDao<Group> implements IGroupDao {
    @Override
    public List<Group> listGroup() {
        String hql = "from Group";
        return this.list(hql);
    }

    @Override
    public Pager<Group> findGroup() {
        return this.find("from Group");
    }

    @Override
    public void deleteGroupUsers(int gid) {
        String hql = "delete UserGroup ug where ug.group.id=?";
        this.updateByHql(hql,gid);
    }

    @Override
    public void addGroupChannel(Group group, Channel channel) {
        GroupChannel gc = this.loadGroupChannel(group.getId(), channel.getId());
        if (gc!=null) return;
        gc = new GroupChannel();
        gc.setGroup(group);
        gc.setChannel(channel);
        this.getSession().save(gc);
    }

    @Override
    public GroupChannel loadGroupChannel(int gid, int cid) {
        return (GroupChannel) this.queryObject("from GroupChannel where group.id=? and channel.id=?",new Object[]{gid,cid});
    }

    @Override
    public void deleteGroupChannel(int gid, int cid) {
        this.updateByHql("delete GroupChannel gc where gc.group.id=? and gc.channel.id=?", new Object[]{gid, cid});
    }

    @Override
    public void clearGroupChannel(int gid) {
        this.updateByHql("delete GroupChannel gc where gc.group.id=?",gid);
    }

    @Override
    public List<Integer> listGroupChannelIds(int gid) {
        String hql = "select gc.channel.id from GroupChannel gc where gc.group.id=?";

        return this.getSession().createQuery(hql).setParameter(0,gid).list();
    }

    @Override
    public List<ChannelTree> generateGroupChannelTree(int gid) {
        String sql = "select c.id as id,c.name as name,c.pid as pid from" +
                " t_group_channel gc left join t_channel c on gc.c_id=c.id where gc.g_id=?";

        List<ChannelTree> cts = this.listBySql(sql,gid,ChannelTree.class,false);
        ChannelDao.initTreeNode(cts);
        return cts;
    }

    @Override
    public List<ChannelTree> generateUserChannelTree(int uid) {
        String sql = "select distinct c.id as id,c.name as name,c.pid as pid" +
                " from t_group_channel gc left join t_channel c on gc.c_id=c.id " +
                "left join t_user_group ug on gc.g_id=ug.g_id where ug.u_id=?";
        List<ChannelTree> cts = this.listBySql(sql,uid,ChannelTree.class,false);
        ChannelDao.initTreeNode(cts);
        return cts;
    }
}
