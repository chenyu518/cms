package org.ychen.cms.service;

import org.ychen.basic.model.Pager;
import org.ychen.cms.model.Channel;
import org.ychen.cms.model.ChannelTree;
import org.ychen.cms.model.Group;
import org.ychen.cms.model.GroupChannel;

import java.util.List;

/**
 * Created by cy on 16/12/13.
 */
public interface IGroupService {
    public void add(Group group);
    public void delete(int gid);
    public Group load(int id);
    public void update(Group group);

    public List<Group> listGroup();
    public Pager<Group> findGroup();
    void deleteGroupUsers(int gid);

    /**
     * 添加GroupChannel对象
     * @param gid
     * @param cid
     */
    void addGroupChannel(int gid, int cid);

    /**
     * 加载GroupChannel对象
     * @param gid
     * @param cid
     * @return
     */
    GroupChannel loadGroupChannel(int gid, int cid);

    /**
     * 删除用户栏目
     * @param gid
     * @param cid
     */
    void deleteGroupChannel(int gid, int cid);

    /**
     * 清空组所管理的栏目
     * @param gid
     */
    void clearGroupChannel(int gid);

    /**
     * 获取某个组的所有管理栏目的id
     * @param gid
     * @return
     */
    List<Integer> listGroupChannelIds(int gid);

    /**
     * 获取某个组的栏目树
     * @param gid
     * @return
     */
    List<ChannelTree> generateGroupChannelTree(int gid);

    /**
     * 获取某个用户的栏目树
     * @param uid
     * @return
     */
    List<ChannelTree> generateUserChannelTree(int uid);
}
