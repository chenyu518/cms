package org.ychen.core.dao;

import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.orm.hibernate4.SessionHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.ychen.cms.dao.IChannelDao;
import org.ychen.cms.model.Channel;
import org.ychen.cms.model.ChannelTree;
import org.ychen.core.util.AbstractDbUnitTestCase;

import javax.inject.Inject;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by cy on 16/12/16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans.xml")
public class TestChannelDao extends AbstractDbUnitTestCase {

    @Inject
    private SessionFactory sessionFactory;
    @Inject
    private IChannelDao channelDao;

    @Before
    public void setUp() throws SQLException, IOException, DatabaseUnitException {
        //此时最好不要使用Spring的Transactional来管理，因为dbunit是通过jdbc来处理connection，再使用spring在一些编辑操作中会造成事务shisu
        Session s = sessionFactory.openSession();
        TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(s));
        this.backupAllTable();
        IDataSet ds = createDateSet("topic");
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon,ds);
    }


    @Test
    public void testListByParent() {
        List<Channel> cs = channelDao.listByParent(null);
        Assert.assertNotNull(cs);
        Assert.assertEquals(cs.size(),4);
    }

    @Test
    public void testGetMaxOrderByParent() {
        Integer max = channelDao.getMaxOrderByParent(1);
        Assert.assertEquals(max, new Integer(4));
        max = channelDao.getMaxOrderByParent(2);
        Assert.assertEquals(max, new Integer(0));
    }

    @Test
    public void testGenerateTree() {
        List<ChannelTree> cts = channelDao.generateTree();
        Assert.assertNotNull(cts);
        Assert.assertEquals(cts.size(), 21);
    }

    @Test
    public void testGenerateTreeByParent() {
        List<ChannelTree> cts = channelDao.generateTreeByParent(null);
        Assert.assertNotNull(cts);
        Assert.assertEquals(cts.size(), 4);
    }

    @After
    public void tearDown() throws DatabaseUnitException, SQLException, IOException {
        SessionHolder holder = (SessionHolder) TransactionSynchronizationManager.getResource(sessionFactory);
        Session s = holder.getSession();
        s.flush();
        TransactionSynchronizationManager.unbindResource(sessionFactory);
//        this.resumeTable();
    }

}
