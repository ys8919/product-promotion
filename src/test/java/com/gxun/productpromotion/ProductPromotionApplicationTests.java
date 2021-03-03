package com.gxun.productpromotion;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;

@SpringBootTest
class ProductPromotionApplicationTests {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Test
    void contextLoads() throws SQLException {

    }

}
