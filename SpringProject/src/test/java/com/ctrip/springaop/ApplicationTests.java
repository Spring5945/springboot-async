package com.ctrip.springaop;


import com.ctrip.springaop.domain.Product;
import com.ctrip.springaop.security.CurrentUserHolder;
import com.ctrip.springaop.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Autowired
    private ProductService productService;

    @Test(expected = Exception.class)
    public void userInsertTest() {
        CurrentUserHolder.set("admin");
        productService.insert(new Product());
    }


    @Test
    public void adminInsertTest() {
        CurrentUserHolder.set("admin");
        productService.insert(new Product());
    }

}
