package com.pts.helper;

import org.springframework.jdbc.core.PreparedStatementCreator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by taronpetrosyan on 8/17/16.
 */
public class SimplePreparedStatementCreator implements PreparedStatementCreator {
    private String sql;

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public SimplePreparedStatementCreator() {
        this.sql = "";
    }

    public SimplePreparedStatementCreator(String sql) {
        this.sql = sql;
    }

    @Override
    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
        return ps;
    }
}
