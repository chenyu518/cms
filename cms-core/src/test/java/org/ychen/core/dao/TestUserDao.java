package org.ychen.core.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ReplacementDataSet;
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
import org.ychen.basic.model.Pager;
import org.ychen.cms.dao.IGroupDao;
import org.ychen.cms.dao.IRoleDao;
import org.ychen.cms.dao.IUserDao;
import org.ychen.cms.model.*;
import org.ychen.core.util.AbstractDbUnitTestCase;
import org.ychen.core.util.EntitiesHelper;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans.xml")
public class TestUserDao extends AbstractDbUnitTestCase {
	@Inject
	private SessionFactory sessionFactory;
	@Inject
	private IUserDao userDao;
	@Inject
	private IRoleDao roleDao;
	@Inject
	private IGroupDao groupDao;
	
	@Before
	public void setUp() throws SQLException, IOException, DatabaseUnitException {
		Session s = sessionFactory.openSession();
		TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(s));
		this.backupAllTable();
		IDataSet ds = createDateSet("t_user");
		DatabaseOperation.CLEAN_INSERT.execute(dbunitCon,ds);

	}
	
	@Test
	public void testListUserRoles() throws DatabaseUnitException, SQLException, IOException {
		IDataSet ds = createDateSet("t_user");
		DatabaseOperation.CLEAN_INSERT.execute(dbunitCon,ds);
//		List<Role> actuals = Arrays.asList(new Role(2,"文章发布人员", RoleType.ROLE_PUBLISH),new Role(3,"文章审核人员",RoleType.ROLE_AUDIT));
		List<Role> roles = userDao.listUserRoles(2);

		for (Role role : roles){
			System.out.println(role.getName());
		}

//		EntitiesHelper.assertRoles(roles, actuals);
	}

	@Test
	public void testListGroupUsers() throws DatabaseUnitException, SQLException, IOException {
		IDataSet ds = createDateSet("t_user");
		DatabaseOperation.CLEAN_INSERT.execute(dbunitCon,ds);
		List<User> realDate = userDao.listGroupUsers(1);
		List<User> aus = Arrays.asList(new User(2, "admin2", "123", "admin2", "admin2@admin.com", "110", 1),
				new User(3, "admin3", "123", "admin3", "admin3@admin.com", "110", 1));
		EntitiesHelper.assertUsers(realDate, aus);
	}

	@Test
	public void testListRoleUsers(){

		List<User> users = userDao.listRoleUsers(2);
		List<User> aus = Arrays.asList(new User(2, "admin2", "123", "admin2", "admin2@admin.com", "110", 1),
				new User(3, "admin3", "123", "admin3", "admin3@admin.com", "110", 1));
		EntitiesHelper.assertUsers(users,aus);
	}

	@Test
	public void testListRoleUsersByRoleType(){

		List<User> users = userDao.listRoleUsers(RoleType.ROLE_PUBLISH);
		List<User> aus = Arrays.asList(new User(2, "admin2", "123", "admin2", "admin2@admin.com", "110", 1),
				new User(3, "admin3", "123", "admin3", "admin3@admin.com", "110", 1));
		EntitiesHelper.assertUsers(users,aus);
	}

	@Test
	public void testListUserGroups(){

		List<Group> groups = userDao.listUserGroups(3);
//		List<Group> aus = Arrays.asList(new Group(1,"财务处"),new Group(3,"宣传部"));
//		EntitiesHelper.assertGroups(groups,aus);
	}
	@Test
	public void testListUserGroupsIds(){

		List<Integer> groupIds = userDao.listUserGroupsIds(3);
		List<Integer> aus = Arrays.asList(1,3);
		EntitiesHelper.assertObjects(groupIds, aus);
	}
	@Test
	public void testListUserRolesIds(){

		List<Integer> rolesIds = userDao.listUserRolesIds(2);
		List<Integer> aus = Arrays.asList(2,3);
		EntitiesHelper.assertObjects(rolesIds, aus);
	}
	@Test
	public void testLoadByUsername(){

		User user = userDao.loadByUsername("admin1");
		User au = EntitiesHelper.getBaseUser();
		EntitiesHelper.assertUser(user, au);
	}

	@Test
	public void testloadUserGroup(){

		UserGroup userGroup = userDao.loadUserGroup(2,1);
		User au = new User(2,"admin2","123","admin2","admin2@admin.com","110",1);
		Group ag = new Group(1,"财务处");

		EntitiesHelper.assertUser(userGroup.getUser(), au);
		EntitiesHelper.assertGroup(userGroup.getGroup(), ag);
	}
	@Test
	public void testloadUserRole(){

		UserRole userRole = userDao.loadUserRole(1, 1);
		User au = new User(1,"admin1","123","admin1","admin1@admin.com","110",1);
		Role ag = new Role(1,"管理员",RoleType.ROLE_ADMIN);

		EntitiesHelper.assertUser(userRole.getUser(), au);
		EntitiesHelper.assertRole(userRole.getRole(), ag);
	}

	@Test
	public void testAddUserGroup(){
		Group group = groupDao.load(1);
		User u = userDao.load(1);
		userDao.addUserGroup(u, group);
		UserGroup ug = userDao.loadUserGroup(1, 1);
		assertNotNull(ug);
		assertEquals(ug.getGroup().getId(),1);
		assertEquals(ug.getUser().getId(),1);
	}

	@Test
	public void testAddUserRole(){
		Role role = roleDao.load(1);
		User u = userDao.load(1);
		userDao.addUserRole(u,role);
		UserRole ur = userDao.loadUserRole(1,1);
		assertNotNull(ur);
		assertEquals(ur.getRole().getId(), 1);
		assertEquals(ur.getUser().getId(),1);
	}

	@Test
	public void testDeleteUserRoles(){
		int uid = 2;
		userDao.deleteUserRoles(uid);
		List<Role> roles = userDao.listUserRoles(uid);
		assertTrue(roles.size()<=0);
	}
	@Test
	public void testDeleteUserGroups(){
		int uid = 2;
		userDao.deleteUserGroups(uid);
		List<Group> groups = userDao.listUserGroups(uid);
		assertTrue(groups.size()<=0);
	}

	@Test
	public void findUser(){
		List<User> actuals = Arrays.asList(new User(1, "admin1", "123", "admin1", "admin1@admin.com", "110", 1),
				new User(2, "admin2", "123", "admin2", "admin2@admin.com", "110", 1),
				new User(3, "admin3", "123", "admin3", "admin3@admin.com", "110", 1));

		Pager<User> userPager = userDao.findUser();
		assertNotNull(userPager);
		EntitiesHelper.assertUsers(userPager.getDatas(),actuals);
	}

	@Test
	public void testDeleteUserRole(){
		int uid = 1;
		int rid = 1;
		userDao.deleteUserRole(uid,rid);
		assertNull(userDao.loadUserRole(uid,rid));
	}

	@Test
	public void testDeleteUserGroup(){
		int uid = 1;
		int gid = 2;
		userDao.deleteUserGroup(uid,gid);
		assertNull(userDao.loadUserGroup(uid,gid));
	}

	

	
	@After
	public void tearDown() throws IOException, DatabaseUnitException, SQLException {
		SessionHolder holder = (SessionHolder) TransactionSynchronizationManager.getResource(sessionFactory);
		Session s = holder.getSession(); 
		s.flush();
		TransactionSynchronizationManager.unbindResource(sessionFactory);
//		this.resumeTable();
	}
}
