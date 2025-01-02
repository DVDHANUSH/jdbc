package com.jdbc.ecom.spring_jdbc_ecom.dao;
import com.jdbc.ecom.spring_jdbc_ecom.model.Product;
import org.springframework.stereotype.Repository;
import java.util.List;
//@Repository
public interface ProductDao {
    // save product
    Product create(Product product);
    // update product
    Product update(Product product, int productId);
    // delete product
    void delete(int productId);
    // get all products
    List<Product> getAll();
    // get single product
     Product get(int ProductId);
    // search product
    List<Product> search(String keyword);


    // get all product of category
    List<Product> getAllByCategory(int catId);
}