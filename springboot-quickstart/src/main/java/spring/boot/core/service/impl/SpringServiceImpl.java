package spring.boot.core.service.impl;

import org.springframework.stereotype.Service;
import spring.boot.core.service.SpringService;

@Service(value = "SpringServiceImpl")
public class SpringServiceImpl implements SpringService {
    @Override
    public String hello(String hello) {
        return hello;
    }
}
