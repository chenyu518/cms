package org.ychen.core.dao;

import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.orm.hibernate4.SessionHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.ychen.cms.dao.IChannelDao;
import org.ychen.cms.dao.IGroupDao;
import org.ychen.cms.dao.IUserDao;
import org.ychen.cms.model.Channel;
import org.ychen.cms.model.ChannelTree;
import org.ychen.cms.model.Group;
import org.ychen.core.util.AbstractDbUnitTestCase;
import org.ychen.core.util.EntitiesHelper;
import org.ychen.core.util.TestUtil;

import javax.inject.Inject;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by cy on 16/12/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans.xml")
public class TestGroupDao extends AbstractDbUnitTestCase {
    @Inject
    private SessionFactory sessionFactory;
    @Inject
    private IUserDao userDao;
    @Inject
    private IGroupDao groupDao;
    @Inject
    private IChannelDao channelDao;

    @Before
    public void setUp() throws SQLException, IOException, DatabaseUnitException {
        Session s = sessionFactory.openSession();
        TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(s));
        this.backupAllTable();
        IDataSet ds = createDateSet("topic");
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon,ds);
    }



    @Test
    public void testListGroup() {
        List<Group> actuals = Arrays.asList(new Group(1,"Finance"),new Group(2,"Computer"),new Group(3,"Propaganda"));
        List<Group> expects = groupDao.listGroup();
        EntitiesHelper.assertGroups(expects, actuals);
    }

    @Test
    public void testGenerateGroupChannelTree() throws Exception{
        int gid = 1;
        List<ChannelTree> actuals = Arrays.asList
                (new ChannelTree(Channel.ROOT_ID, Channel.ROOT_NAME, -1),
                        new ChannelTree(1, "User Management Module", 0),
                        new ChannelTree(2, "User Management1", 1),
                        new ChannelTree(3, "User Management2", 1),
                        new ChannelTree(6, "Article Management Module", 0),
                        new ChannelTree(8, "Article Management2", 6));
        List<ChannelTree> cts = groupDao.generateGroupChannelTree(gid);
        TestUtil.assertListByClz(cts, actuals, ChannelTree.class, null);
    }


    @After
    public void tearDown() throws Exception {
        SessionHolder holder = (SessionHolder) TransactionSynchronizationManager.getResource(sessionFactory);
        Session s = holder.getSession();
        s.flush();
        TransactionSynchronizationManager.unbindResource(sessionFactory);
        this.resumeTable();
    }




}
