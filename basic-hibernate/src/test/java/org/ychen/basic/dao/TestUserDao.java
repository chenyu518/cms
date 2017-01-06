package org.ychen.basic.dao;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.orm.hibernate4.SessionHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.ychen.basic.model.Pager;
import org.ychen.basic.model.SystemContext;
import org.ychen.basic.model.User;
import org.ychen.basic.util.AbstractDbUnitTestCase;
import org.ychen.basic.util.EntitiesHelper;
import static org.junit.Assert.*;

import javax.inject.Inject;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cy on 16/12/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans.xml")
//@TestExecutionListeners({DbUnitTestExecutionListener.class, DependencyInjectionTestExecutionListener.class})
public class TestUserDao extends AbstractDbUnitTestCase{

    @Inject
    private SessionFactory sessionFactory;

    @Inject
    private IUserDao userDao;

    @Before
    public void setUp() throws SQLException, IOException, DataSetException {
        Session s = sessionFactory.openSession();
        TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(s));
        this.backupAllTable();
    }

    @Test
//    @DatabaseSetup("/t_user.xml")
    public void testLoad() throws DatabaseUnitException, SQLException {
        IDataSet ds = createDateSet("t_user");
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon,ds);
        User u = userDao.load(1);
        EntitiesHelper.assertUser(u);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void testDelete() throws DatabaseUnitException, SQLException {
        IDataSet ds = createDateSet("t_user");
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon,ds);
        userDao.delete(1);
        User u = userDao.load(1);
        System.out.println(u.getUsername());
    }

    @Test
    public void testListByArgs() throws DatabaseUnitException, SQLException {
        IDataSet ds = createDateSet("t_user");
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon,ds);
        SystemContext.setOrder("desc");
        SystemContext.setSort("id");
        List<User> users = userDao.list("from User where id>? and id<?", new Object[]{1,4});
        List<User> actuals = Arrays.asList(new User(3,"admin3"),new User(2,"admin2"));
        for (User u : users){
            System.out.println(u.getId() + " " + u.getUsername());
        }
        EntitiesHelper.assertUsers(users, actuals);
    }

    @Test
    public void testListByArgsAndAlias() throws DatabaseUnitException, SQLException {
        IDataSet ds = createDateSet("t_user");
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon,ds);
        SystemContext.setOrder("desc");
        SystemContext.setSort("id");
        Map<String, Object> alias = new HashMap<String, Object>();
        alias.put("ids", Arrays.asList(1,2,3,4,5));
        List<User> users = userDao.list("from User where id>? and id<? and id in(:ids)", new Object[]{1,4}, alias);
        List<User> actuals = Arrays.asList(new User(3,"admin3"),new User(2,"admin2"));
        for (User u : users){
            System.out.println(u.getId() + " " + u.getUsername());
        }
        EntitiesHelper.assertUsers(users,actuals);
    }

    @Test
    public void testFindByArgs() throws DatabaseUnitException, SQLException {
        IDataSet ds = createDateSet("t_user");
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon,ds);
        SystemContext.setOrder("desc");
        SystemContext.setSort("id");

        SystemContext.setPageOffset(0);
        SystemContext.setPageSize(3);

        Pager<User> pagerUsers = userDao.find("from User where id>? and id<?", new Object[]{1,10});
        List<User> actuals = Arrays.asList(new User(3,"admin3"),new User(2,"admin2"));

        for (User u :pagerUsers.getDatas()){
            System.out.println(u.getUsername());
        }

    }

    @Test
    public void testFindByArgsAndAlias() throws DatabaseUnitException, SQLException {
        IDataSet ds = createDateSet("t_user");
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon,ds);
        SystemContext.setOrder("asc");
        SystemContext.setSort("id");

        SystemContext.setPageOffset(0);
        SystemContext.setPageSize(3);

        Map<String, Object> alias = new HashMap<String, Object>();
        alias.put("ids", Arrays.asList(1,2,4,5,6,7,8,10));

        Pager<User> pagerUsers = userDao.find("from User where id>? and id<? and id in (:ids)", new Object[]{1, 10}, alias);

        for (User u :pagerUsers.getDatas()){
            System.out.println(u.getUsername());
        }

    }

    @Test
    public void testListSQLByArgs() throws DatabaseUnitException, SQLException {
        IDataSet ds = createDateSet("t_user");
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon,ds);
        SystemContext.setOrder("desc");
        SystemContext.setSort("id");
        List<User> expected = userDao.listUserBySql("select * from t_user where id>? and id<?", new Object[]{1,4},User.class,true);
        List<User> actuals = Arrays.asList(new User(3,"admin3"),new User(2,"admin2"));
        assertNotNull(expected);
        assertTrue(expected.size()==2);
        EntitiesHelper.assertUsers(expected, actuals);
    }

    @Test
    public void testListSQLByArgsAndAlias() throws DatabaseUnitException, SQLException {
        IDataSet ds = createDateSet("t_user");
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon,ds);
        SystemContext.setOrder("asc");
        SystemContext.setSort("id");
        Map<String,Object> alias = new HashMap<String,Object>();
        alias.put("ids", Arrays.asList(1,2,3,5,6,7,8,9,10));
        List<User> expected = userDao.listUserBySql("select * from t_user where id>? and id<? and id in(:ids)", new Object[]{1,5},alias,User.class,true);
        List<User> actuals = Arrays.asList(new User(2,"admin2"),new User(3,"admin3"));
        assertNotNull(expected);
        assertTrue(expected.size()==2);
        EntitiesHelper.assertUsers(expected, actuals);
    }

    @Test
    public void testFindSQLByArgs() throws DatabaseUnitException, SQLException {
        IDataSet ds = createDateSet("t_user");
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon,ds);
        SystemContext.setOrder("desc");
        SystemContext.setSort("id");
        SystemContext.setPageSize(3);
        SystemContext.setPageOffset(0);
        Pager<User> expected = userDao.findUserBySql("select * from t_user where id>=? and id<=?", new Object[]{1,10},User.class,true);
        List<User> actuals = Arrays.asList(new User(10,"admin10"),new User(9,"admin9"),new User(8,"admin8"));
        assertNotNull(expected);
        assertTrue(expected.getTotal()==10);
        assertTrue(expected.getOffset()==0);
        assertTrue(expected.getSize()==3);
        EntitiesHelper.assertUsers(expected.getDatas(), actuals);
    }

    @Test
    public void testFindSQLByArgsAndAlias() throws DatabaseUnitException, SQLException {
        IDataSet ds = createDateSet("t_user");
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon,ds);
        SystemContext.removeOrder();
        SystemContext.removeSort();
        SystemContext.setPageSize(3);
        SystemContext.setPageOffset(0);
        Map<String,Object> alias = new HashMap<String,Object>();
        alias.put("ids", Arrays.asList(1,2,4,5,6,7,8,10));
        Pager<User> expected = userDao.findUserBySql("select * from t_user where id>=? and id<=? and id in (:ids)", new Object[]{1,10},alias,User.class,true);
        List<User> actuals = Arrays.asList(new User(1,"admin1"),new User(2,"admin2"),new User(4,"admin4"));
        assertNotNull(expected);
        assertTrue(expected.getTotal()==8);
        assertTrue(expected.getOffset()==0);
        assertTrue(expected.getSize()==3);
        EntitiesHelper.assertUsers(expected.getDatas(), actuals);
    }


    @After
    public void tearDown() throws FileNotFoundException, DatabaseUnitException, SQLException {
        SessionHolder holder = (SessionHolder) TransactionSynchronizationManager.getResource(sessionFactory);
        Session s = holder.getSession();
        s.flush();
        TransactionSynchronizationManager.unbindResource(sessionFactory);
        this.resumeTable();
    }
}