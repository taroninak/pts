package com.pts.helper;

import org.springframework.jdbc.core.PreparedStatementCreator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by taronpetrosyan on 8/17/16.
 */
public class SimplePreparedStatmentCreator implements PreparedStatementCreator {
    private String sql;

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public SimplePreparedStatmentCreator() {
        this.sql = "";
    }

    public SimplePreparedStatmentCreator(String sql) {
        this.sql = sql;
    }

    @Override
    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
        return ps;
    }
}
