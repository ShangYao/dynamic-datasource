package com.inspur.dynamicdatasource.datatest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DatabaseTest {
    @Autowired
    DataSource defaultDataSource;

    @Test
    public void testCreatDB() throws SQLException {
        Connection con = defaultDataSource.getConnection();
        Statement sta = con.createStatement();
        String createSql = "CREATE TABLE people("
                + "name varchar(10) not null,"
                + "age int(4) not null"
                + ")charset=utf8;";
      //  System.out.println(sta.executeLargeUpdate(createSql));
        con.close();
        sta.close();
    }
}
