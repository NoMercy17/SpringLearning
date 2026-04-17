package org.example._2_using_data_sources_spring;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PurchaseRepository {

    private final JdbcTemplate jdbc;

    public PurchaseRepository(JdbcTemplate jdbcTemplate) {
        this.jdbc = jdbcTemplate;
    }


    public void storePurchase(Purchase purchase) {
        String sql = "INSERT INTO purchase (product, price) VALUES (?, ?)";

        // the product and price replaces the "?"
        jdbc.update(sql, purchase.getProduct(), purchase.getPrice());
    }

    public List<Purchase> findAllPurchases() {
        String sql = "SELECT * FROM purchase";

        //We set the data into a Purchase instance. JdbcTemplate will use this logic for each row in the result set.
        RowMapper<Purchase> purchaseRowMapper = (r, i) ->{
            Purchase rowObject = new Purchase();
            rowObject.setId(r.getInt("id"));
            rowObject.setProduct(r.getString("product"));
            rowObject.setPrice(r.getBigDecimal("price"));
            return rowObject;
        };

        //JdbcTemplate to know how to transform the data it gets in Purchase objects
        return jdbc.query(sql, purchaseRowMapper);
    }

}
