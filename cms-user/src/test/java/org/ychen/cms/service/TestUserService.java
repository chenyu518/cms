package org.ychen.cms.service;

import junit.framework.Assert;
import org.easymock.EasyMock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.ychen.basic.model.Pager;
import org.ychen.cms.dao.IGroupDao;
import org.ychen.cms.dao.IRoleDao;
import org.ychen.cms.dao.IUserDao;
import org.ychen.cms.model.*;
import sun.jvm.hotspot.debugger.Page;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.easymock.EasyMock.*;

/**
 * Created by cy on 16/12/6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-beans.xml")
public class TestUserService {

    @Inject
    private IUserService userService;

    @Inject
    private IRoleDao roleDao;

    @Inject
    private IUserDao userDao;

    @Inject
    private IGroupDao groupDao;

    private User baseUser = new User(2,"admin2","123","admin2","admin2@admin.com", "110", 0);

    @Test
    public void testDelete(){
        reset(userDao);
        int uid = 2;
        userDao.deleteUserGroups(uid);
        expectLastCall();
        userDao.deleteUserRoles(uid);
        expectLastCall();
        userDao.delete(uid);
        expectLastCall();
        replay(userDao);
        userService.delete(uid);
        verify(userDao);
        System.out.print("success");
    }

    @Test
    public void testUpdateStatus(){
        reset(userDao);
        int uid = 2;
        User u = userDao.load(uid);
        expect(u).andReturn(baseUser);
        userDao.update(baseUser);
        expectLastCall();
        replay(userDao);
        userService.updateStatus(uid);
        Assert.assertEquals(baseUser.getStatus(),1);

        verify(userDao);
    }

    @Test(expected=CmsException.class)
    public void testUpdateStatusNoUser() {
        reset(userDao);
        int uid = 2;
        expect(userDao.load(uid)).andReturn(null);
        userDao.update(baseUser);
        expectLastCall();
        replay(userDao);
        userService.updateStatus(uid);
        Assert.assertEquals(baseUser.getStatus(), 1);
        verify(userDao);

    }

    @Test
    public void findUser(){
        reset(userDao);
        expect(userDao.findUser()).andReturn(new Pager<User>());
        expectLastCall();
        replay(userDao);
        userService.findUser();
        verify(userDao);
    }

    @Test
    public void testLoad(){
        int uid = 2;
        reset(userDao);
        expect(userDao.load(uid)).andReturn(new User());
        replay(userDao);
        userService.load(uid);
        verify(userDao);
    }

    @Test
    public void testlistUserRoles(){
        reset(userDao);
        int uid = 2;
        expect(userDao.listUserRoles(uid)).andReturn(new ArrayList<Role>());
        replay(userDao);
        userService.listUserRoles(uid);
        verify(userDao);
    }

    @Test
    public void testlistUserGroups(){
        reset(userDao);
        int uid = 2;
        expect(userDao.listUserGroups(uid)).andReturn(new ArrayList<Group>());
        replay(userDao);
        userService.listUserGroups(uid);
        verify(userDao);
    }

    @Test
    public void testAdd1() {
        reset(userDao,roleDao,groupDao);
        Integer[] rids = {1,2};
        Integer[] gids = {2,3};
        Role r = new Role(1,"管理员",RoleType.ROLE_ADMIN);
        Group g = new Group(2,"财务处");
        expect(userDao.loadByUsername("admin2")).andReturn(null);
        expect(userDao.add(baseUser)).andReturn(baseUser);
        expect(roleDao.load(rids[0])).andReturn(r);
        userDao.addUserRole(baseUser, r);
        expectLastCall();
        r.setId(2);
        expect(roleDao.load(rids[1])).andReturn(r);

        userDao.addUserRole(baseUser, r);
        expectLastCall();

        expect(groupDao.load(gids[0])).andReturn(g);
        userDao.addUserGroup(baseUser, g);
        expectLastCall();

        r.setId(3);
        expect(groupDao.load(gids[1])).andReturn(g);
        userDao.addUserGroup(baseUser, g);
        expectLastCall();

        replay(userDao,roleDao,groupDao);
        userService.add(baseUser, rids, gids);
        verify(userDao,roleDao,groupDao);
    }

    @Test
    public void testAdd2() {
        reset(userDao,roleDao,groupDao);
        Integer[] rids = {1,2,5,6};
        Integer[] gids = {2,3,4};
        Role r = new Role(1,"管理员",RoleType.ROLE_ADMIN);
        Group g = new Group(2,"财务处");
        expect(userDao.loadByUsername("admin2")).andReturn(null);
        //添加role
        expect(userDao.add(baseUser)).andReturn(baseUser);
        //动态参数
        expect(roleDao.load(EasyMock.gt(0))).andReturn(r).times(4);
        userDao.addUserRole(baseUser, r);
        expectLastCall().times(4);
        //添加group
        expect(groupDao.load(EasyMock.gt(0))).andReturn(g).times(3);
        userDao.addUserGroup(baseUser, g);
        expectLastCall().times(3);

        replay(userDao,roleDao,groupDao);
        userService.add(baseUser, rids, gids);
        verify(userDao,roleDao,groupDao);
    }


}
