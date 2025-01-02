package com.jdbc.ecom.spring_jdbc_ecom.dao.Impl;
import com.jdbc.ecom.spring_jdbc_ecom.dao.ProductDao;
import com.jdbc.ecom.spring_jdbc_ecom.helper.ProductMapper;
import com.jdbc.ecom.spring_jdbc_ecom.model.Product;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Component
public class ProductDaoImpl implements ProductDao {
    private JdbcTemplate jdbcTemplate;
    // by default : constructor injection
    public ProductDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        String createQuery = "CREATE TABLE IF NOT EXISTS products (\n" +
                "    id INT PRIMARY KEY, \n" +
                "    title VARCHAR(200) NOT NULL, \n" +
                "    description VARCHAR(500) NOT NULL, \n" +
                "    price INT NOT NULL, \n" +
                "    catId INT, \n" +
                "    FOREIGN KEY (catId) REFERENCES categories(id)\n" +
                ");\n";
        jdbcTemplate.update(createQuery);
    }
    @Override
    public Product create(Product product) {
        String query = "insert into products(id, title, description, price, catId) values(?, ?, ?, ?, ?)";
        int update = jdbcTemplate.update(query, product.getId(), product.getDescription(), product.getTitle(), product.getPrice(), product.getCatId());
        System.out.println(update +"rows effected");
        return product;
    }

    @Override
    public Product update(Product product, int productId) {
        String query = "update products set title = ?, description = ?, price = ?, where id = ?";
       int updatedRows = jdbcTemplate.update(query, product.getTitle(), product.getDescription(), product.getPrice(), productId);
       System.out.println(updatedRows + " updated ");
       product.setId(productId);
       return product;
    }
    @Override
    public void delete(int productId) {
        String query = "delete from products where id =?";
        int delete = jdbcTemplate.update(
                query,
                productId);
        System.out.println(delete + "rows effected");
    }
    @Override
    public List<Product> getAll() {
        String query = "SELECT * FROM PRODUCTS";
//        jdbcTemplate.queryForList();
//        jdbcTemplate.queryForMap();
//        jdbcTemplate.queryForObject();
//        jdbcTemplate.queryForList(query,Product.class); - this method is not allowing row mappers, hence check the "query"
        // what does row mapper do ??
        // it maps the field value that comes to resultset with product class dao.
//        List<Product> products = jdbcTemplate.query(query, new RowMapper<Product>() {
//            @Override
//            public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
//                Product product = new Product();
//                product.setId(rs.getInt("id"));
//                product.setPrice(rs.getInt("price"));
//                product.setDescription(rs.getString("description"));
//                product.setTitle(rs.getString("title"));
//                // set the values of the product
//                return product;
//            }
//        List<Product> products = jdbcTemplate.query(query, (rs, rowNum) -> {
//                Product product = new Product();
//                product.setId(rs.getInt("id"));
//                product.setPrice(rs.getInt("price"));
//                product.setDescription(rs.getString("description"));
//                product.setTitle(rs.getString("title"));
//                // set the values of the product
//                return product;
//            });
        List<Product> products = jdbcTemplate.query(query, new ProductMapper());
        return products;
    }
    @Override
    public Product get(int ProductId) {
        return jdbcTemplate.queryForObject("SELECT * FROM PRODUCTS WHERE ID = ?", new ProductMapper(), ProductId);
    }
    @Override
    public List<Product> search(String keyword) {
        return null;
    }
    @Override
    public List<Product> getAllByCategory(int catId) {
        return null;
    }
}