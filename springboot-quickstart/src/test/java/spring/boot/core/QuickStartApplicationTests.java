package spring.boot.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import spring.boot.core.service.SpringService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuickStartApplicationTests {

    @Autowired
    @Qualifier("SpringServiceImpl")
    private SpringService springService;

    @Test
    public void contextLoads() {
        System.out.println(springService.hello("sprignboot junit test"));
    }
}
