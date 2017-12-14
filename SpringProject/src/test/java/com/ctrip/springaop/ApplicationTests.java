package com.ctrip.springaop;


import com.ctrip.springaop.cglib.RealService;
import com.ctrip.springaop.domain.Product;
import com.ctrip.springaop.security.CurrentUserHolder;
import com.ctrip.springaop.service.sub.ProductService;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
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
//        CurrentUserHolder.set("admin");
        productService.insert(new Product());
    }

    @Test
    public void adminInsertTest() {
//        CurrentUserHolder.set("user");
        productService.insert(new Product());
    }

    @Test
    public void cgilbTest() {
        // cglib  利用asm操作字节码生成子类(final修饰类不可生成)
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(RealService.class);
        enhancer.setCallback((MethodInterceptor) (obj, method, args, proxy) -> {
            System.out.println("before sayHello run...");
            Object result = proxy.invokeSuper(obj, args);
            System.out.println("after sayHello run...");
            return result;
        });
        // 真实主题(被代理主题切面)
        RealService realService = (RealService) enhancer.create();
        realService.sayHello();
    }

}
