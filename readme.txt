#Aynsc
This is my github project of springboot async annotation coding

Quick  Start

Configurations:

@SpringBootApplication
@EnableAsync
public class MyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }

}

@Configuration
public class Configurations {
    LongAdder longAdder = new LongAdder();

    @Bean("myCacheThreadPool")
    public ExecutorService threadPool() {
        return Executors.newCachedThreadPool(r -> {
            Thread thread = new Thread(r);
            longAdder.add(1);
            thread.setName("myCacheThread-" + longAdder.sum());
            System.out.println("myCacheThread Start");
            return thread;
        });
    }
}

async task run:

    @Override
    @Async("myCacheThreadPool")
    public void saveUser(User user) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        userMap.put(user.getUserId(), user);
    }

async future task run:

    @Override
    @Async("myCacheThreadPool")
    public Future<User> getUserInfo(Long userId) {
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
        }
    
        return new AsyncResult<>(userMap.get(userId));
    }
    

exception handler(when function return type is void):

public class MyAsyncUncaughtExceptionHandler implements AsyncUncaughtExceptionHandler {

    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... params) {
        System.out.println("Async Exception" + "---" + ex.getStackTrace() + "---" + method.getName());
        // doSomething....
    }
}

@Configuration
public class AsyncConfig implements AsyncConfigurer {

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new MyAsyncUncaughtExceptionHandler();
    }
}
