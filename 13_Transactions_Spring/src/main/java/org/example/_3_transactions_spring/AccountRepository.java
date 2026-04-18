package org.example._3_transactions_spring;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class AccountRepository {

    private final JdbcTemplate jdbc;

    public AccountRepository(JdbcTemplate jdbcTemplate) {
        this.jdbc = jdbcTemplate;
    }

    public Account findAccountById(long id) {
        String sql = "select * from account where id = ?";

        // since we use the RowMapper more than once we created the class separated
        return jdbc.queryForObject(sql, new AccountRowMapper(), id);
    }

    public void changeAmount(long id, BigDecimal amount) {
        String sql = "UPDATE account SET amount = ? WHERE id = ?";

        jdbc.update(sql, amount, id);
    }

    public List<Account> findAllAccounts() {
        String sql = "SELECT * FROM account";

        return jdbc.query(sql, new AccountRowMapper());
    }
}
