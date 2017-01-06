package org.ychen.basic;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlProducer;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.xml.sax.InputSource;
import org.ychen.basic.util.DbUtil;

import java.sql.SQLException;

/**
 * Created by cy on 16/12/19.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans.xml")
public class TestDB {


    @Test
    public void loaddata(){
        try {
            IDatabaseConnection con = new DatabaseConnection(DbUtil.getConnection());
            IDataSet ds = new FlatXmlDataSet(new FlatXmlProducer(new InputSource(TestDB.class.getClassLoader().getResourceAsStream("t_user.xml"))));
            DatabaseOperation.CLEAN_INSERT.execute(con,ds);
        } catch (DatabaseUnitException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
