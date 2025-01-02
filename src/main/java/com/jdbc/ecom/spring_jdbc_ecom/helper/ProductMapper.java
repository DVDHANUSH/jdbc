package com.jdbc.ecom.spring_jdbc_ecom.helper;
import com.jdbc.ecom.spring_jdbc_ecom.model.Product;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
public class ProductMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException{
        Product product = new Product();
        product.setId(rs.getInt("id"));
        product.setPrice(rs.getInt("price"));
        product.setDescription(rs.getString("description"));
        product.setTitle(rs.getString("title"));
        return product;
}
}