package org.ychen.core.dao;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.DatabaseDataSet;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlProducer;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Test;
import org.xml.sax.InputSource;
import org.ychen.core.util.DbUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by cy on 16/12/19.
 */
public class TestMain {

    @Test
    public void initData(){
        try {
            IDatabaseConnection con = new DatabaseConnection(DbUtil.getConnection());
            IDataSet ds = new FlatXmlDataSet(new FlatXmlProducer(new InputSource(this.getClass().getClassLoader().getResourceAsStream("t_user.xml"))));
            DatabaseOperation.CLEAN_INSERT.execute(con,ds);
        } catch (DatabaseUnitException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
