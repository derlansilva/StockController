package com.example.stockmanager.infrastructure.persistence;

import com.example.stockmanager.domain.model.Product;
import com.example.stockmanager.domain.model.Stock;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class JpaStockRepository {
    private final JdbcTemplate jdbcTemplate;

    public JpaStockRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Stock createNewStock(Product product) {
        String sql = "INSERT INTO stocks (product_id, available_quantity, reserved_quantity, lost_quantity) VALUES (?, ?, ? , ? )";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update( connection  -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1 , product.getId());
            ps.setLong(2, 0);
            ps.setLong(3, 0);
            ps.setLong(4, 0);

            return ps;
        } , keyHolder );
        Long stockId = null;
        if(keyHolder.getKey() != null) stockId = keyHolder.getKey().longValue();


        Stock stock = new Stock();
        stock.setId(stockId);
        stock.setProduct(product);
        stock.setAvailableQuantity(0);
        stock.setReservedQuantity(0);
        stock.setLostQuantity(0);

        return stock;
    }


}
