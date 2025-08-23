package com.example.stockmanager.infrastructure.persistence;

import com.example.stockmanager.application.dto.ProductWithStockDto;
import com.example.stockmanager.application.service.ProductServiceImpl;
import com.example.stockmanager.domain.model.MovementType;
import com.example.stockmanager.domain.model.Product;
import com.example.stockmanager.domain.model.Stock;
import com.example.stockmanager.domain.repository.ProductRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

@Repository
public class JpaStockRepository {
    private final JdbcTemplate jdbcTemplate;

    private ProductServiceImpl productService;

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
        stock.setAvailableQuantity(0);
        stock.setReservedQuantity(0);
        stock.setLostQuantity(0);

        return stock;
    }

    public Optional<Stock> findbyProductId(Long productId ){
        String sql = "SELECT * FROM stocks WHERE product_id = ?";

        try {
            Stock stock = jdbcTemplate.queryForObject(sql , new StockRowMapper() , productId );
            return Optional.of(stock);
        }catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Transactional
    public void stockEntry(Product product , long quantity ){
        String sql= "UPDATE stocks SET available_quantity = available_quantity + ? WHERE product_id = ?";

        int affectedRows = jdbcTemplate.update(sql , quantity , product.getId() );


        if(affectedRows == 0){
            throw new IllegalArgumentException("Nenhum Registro de estoque encontrado");
        }
        System.out.println("Estoque do produto ID " + product.getId() + " atualizado. Linhas afetadas: " + affectedRows);

    }



    private static class StockRowMapper implements RowMapper<Stock>{

        @Override
        public Stock mapRow(ResultSet rs, int rowNum) throws SQLException {
            Stock stock = new Stock();

            stock.setId(rs.getLong("id"));
            stock.setAvailableQuantity(rs.getLong("available_quantity"));
            stock.setReservedQuantity(rs.getLong("reserved_quantity"));
            stock.setLostQuantity(rs.getLong("lost_quantity"));

            return stock;
        }
    }

    public void transferStock(Product product , long quantity , MovementType fromStockType , MovementType toStockType){
        if(product.getId() == null || fromStockType == null || toStockType ==null){
            throw new IllegalArgumentException("Ids e tipos de estoque não podem ser nulos");
        }

        String fromColumn = getColumnNameForMovementType(fromStockType);
        String toColumn = getColumnNameForMovementType(toStockType);

        if (fromColumn == null || toColumn == null) {
            throw new IllegalArgumentException("Tipo de estoque inválido para transferência.");
        }

        String sql = String.format(
                "UPDATE stocks SET %s = %s - ? , %s = %s+ ? WHERE product_id = ?" ,
                fromColumn , fromColumn , toColumn , toColumn
        );

        int affectedRows = jdbcTemplate.update(sql , quantity , quantity , product.getId());

        if (affectedRows == 0) {
            throw new IllegalArgumentException("Nenhum registro de estoque encontrado ou quantidade insuficiente.");
        }

    }

    private String getColumnNameForMovementType(MovementType type){
        switch (type){
            case DISPONIVEL: return "available_quantity";

            case RESERVA: return "reserved_quantity";

            case PERDA: return "lost_quantity";

            default: return null;
        }
    }

}
