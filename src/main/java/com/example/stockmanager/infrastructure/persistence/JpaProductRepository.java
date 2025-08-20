package com.example.stockmanager.infrastructure.persistence;

import com.example.stockmanager.domain.model.Product;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
import java.sql.Statement;


import java.util.List;
import java.util.Optional;
@Repository
public class JpaProductRepository {

    private final JdbcTemplate jdbcTemplate;

    public JpaProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Product save(Product product) {
        String sql = "INSERT INTO products (sku , description , price) VALUES (? ,? , ?) ";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update( connection -> {
            PreparedStatement ps = connection.prepareStatement( sql , Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, product.getSku());
            ps.setString(2, product.getDescription());
            ps.setString(3, product.getPrice());
            return ps;
        } , keyHolder);

        if(keyHolder.getKey() != null) {
            product.setId(keyHolder.getKey().longValue());
        }
        return product;
    }


    public Optional<Product> findBySku(String sku) {
        String sql = "SELECT id, sku, description, price FROM products WHERE sku = ?";

        try{
            Product product = jdbcTemplate.queryForObject(sql, productRowMapper , sql);
            return Optional.ofNullable(product);
        }catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }
/*
    public List<Product> findAll() {}

    public void deleteBySku(String sku) {}*/

    private final RowMapper<Product> productRowMapper = (rs, rowNum) -> {
        Product product = new Product();
        product.setId(rs.getLong("id"));
        product.setSku(rs.getString("sku"));
        product.setDescription(rs.getString("description"));
        product.setPrice(rs.getString("price"));
        return product;
    };

}
