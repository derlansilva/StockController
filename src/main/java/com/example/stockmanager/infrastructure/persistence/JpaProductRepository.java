package com.example.stockmanager.infrastructure.persistence;

import com.example.stockmanager.application.dto.ProductWithStockDto;
import com.example.stockmanager.application.service.StockServiceImpl;
import com.example.stockmanager.domain.model.Product;
import com.example.stockmanager.domain.model.Stock;
import com.example.stockmanager.domain.service.ProductService;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
import java.sql.Statement;


import java.util.List;
import java.util.Optional;
@Repository
public class JpaProductRepository implements ProductService {

    private final JdbcTemplate jdbcTemplate;


    private final StockServiceImpl stockService;

    public JpaProductRepository(JdbcTemplate jdbcTemplate, StockServiceImpl stockService) {
        this.jdbcTemplate = jdbcTemplate;

        this.stockService = stockService;
    }

    @Override
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

        System.out.println("verificando o produto "  + product.getId());
        stockService.createNewStock(product);

        return product;
    }

    @Override
    public Optional<Product> findBySku(String sku) {
        String sql = "SELECT id, sku, description, price FROM products WHERE sku = ?";

        try{
            Product product = jdbcTemplate.queryForObject(sql, productRowMapper , sku);
            return Optional.ofNullable(product);
        }catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    public ProductWithStockDto findProductAndStockBySku(String sku ){
        Product product = findBySku(sku).orElseThrow(() -> new IllegalArgumentException("Product not found"));

        Stock stock = stockService.findByProductId(product.getId()).orElseThrow(()-> new IllegalArgumentException("Stock not found"));

        return new ProductWithStockDto(product.getId() , product.getSku() , product.getDescription() , product.getPrice() , stock );
    }


    public List<Product> findAll() {
        try {
            String sql = "SELECT * FROM products";

            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Product>(Product.class));
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateProduct(Product product) {

    }

    @Override
    public void deleteProduct(Product product) {

    }



    private final RowMapper<Product> productRowMapper = (rs, rowNum) -> {
        Product product = new Product();
        product.setId(rs.getLong("id"));
        product.setSku(rs.getString("sku"));
        product.setDescription(rs.getString("description"));
        product.setPrice(rs.getString("price"));
        return product;
    };

}
