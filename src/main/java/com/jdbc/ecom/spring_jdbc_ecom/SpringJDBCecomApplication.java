package com.jdbc.ecom.spring_jdbc_ecom;
import com.jdbc.ecom.spring_jdbc_ecom.dao.CategoryDao;
import com.jdbc.ecom.spring_jdbc_ecom.dao.ProductDao;
import com.jdbc.ecom.spring_jdbc_ecom.model.Category;
import com.jdbc.ecom.spring_jdbc_ecom.model.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import java.util.List;
@SpringBootApplication
public class SpringJDBCecomApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringJDBCecomApplication.class, args);
//        SpringApplication.run(SpringJDBCecomApplication.class, args);
        ProductDao productDao = context.getBean(ProductDao.class);
        CategoryDao categoryDao = context.getBean(CategoryDao.class);
        Category category = new Category();
        category.setDescription("mobiles");
        category.setTitle("mobile phones");
        category.setId(1009);
        categoryDao.create(category);
        Product product1 = new Product();
        product1.setId(119);
        product1.setTitle("Iphone 14");
        product1.setDescription("Best IOS Phone");
        product1.setPrice(124000);

        product1.setCatId(category.getId());
        productDao.create(product1);
//        Product product = productDao.create(product1);
//        System.out.println(product);
//        List<Product> products = productDao.getAll();
//        products.forEach(item -> System.out.println(item.getTitle()));
//        Product product = productDao.get(101);
//        System.out.println("the retrived product : " + product );
    }
}