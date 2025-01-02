package com.jdbc.ecom.spring_jdbc_ecom.dao.Impl;
import com.jdbc.ecom.spring_jdbc_ecom.dao.CategoryDao;
import com.jdbc.ecom.spring_jdbc_ecom.model.Category;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDaoImpl implements CategoryDao {
    private JdbcTemplate jdbcTemplate;
    public CategoryDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
        String createQuery = "CREATE TABLE IF NOT EXISTS categories (id int primary key, title varchar(200) not null, description varchar(500) not null)";

        jdbcTemplate.update(createQuery);
    }

    @Override
    public Category create(Category category){
       int update =  jdbcTemplate.update("insert into categories(id, title, description) values (?, ?, ?)", category.getId(), category.getTitle(), category.getDescription());
        System.out.println(update + "category added");
        return category;
    }
}
