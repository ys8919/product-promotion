package com.gxun.productpromotion;

import com.gxun.entity.Commodity;
import com.gxun.entity.Users;
import com.gxun.services.CommodityServices;
import com.gxun.services.UserServices;
import com.gxun.util.RandIdUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.HashMap;

@SpringBootTest
class ProductPromotionApplicationTests {

    @Autowired
    CommodityServices commodityServices;
    @Test
    void contextLoads() throws SQLException {
       /* Commodity commodity=new Commodity();
        commodity.setPhoto("https://yx9819-1258077645.cos.ap-guangzhou.myqcloud.com/product-promotion/79631615358958589.png");
        commodityServices.deleteCommodity(commodity);*/
    }

}
