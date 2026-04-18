package org.example._3_transactions_spring;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRowMapper implements RowMapper<Account> {

    @Override
    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
        Account acc = new  Account();

        acc.setId(rs.getInt("id"));
        acc.setName(rs.getString("name"));
        acc.setAmount(rs.getBigDecimal("amount"));

        return acc;
    }
}
