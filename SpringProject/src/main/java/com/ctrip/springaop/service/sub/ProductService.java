package com.ctrip.springaop.service.sub;

import com.ctrip.springaop.domain.Product;
import org.springframework.stereotype.Service;


@Service
public class ProductService {

//    @Autowired
//    AuthService authService;

//    @AdminOnly
    public void insert(Product product) {
        System.out.println("insert product");
    }

//    @AdminOnly
    public void delete() {
        System.out.println("delete product");
    }

}
