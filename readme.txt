#Aynsc
This is my github project of springboot async annotation coding & Spring EventSource Coing

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


Event Source：

@Component
public class EventPublisher implements ApplicationEventPublisherAware {
    private static final Logger logger = LoggerFactory.getLogger("EventPublisher");
    public ApplicationEventPublisher publisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    public void publish(ApplicationEvent event) {
        logger.info("publish event:" + event.toString());
        publisher.publishEvent(event);
    }
}

@Component
public class UserEventListener implements ApplicationListener<UserEvent> {
    private static final Logger logger = LoggerFactory.getLogger("UserEventListener");

    @Override
    @Async("myCacheThreadPool")
    public void onApplicationEvent(UserEvent userEvent) {
        logger.info("------------ApplicationListener监听方式处理事件：{}", userEvent.getMsg());
        try {
            Thread.sleep(5 * 1000L);
            logger.info("ApplicationListener监听方式事件处理完成,新增用户一条");
        } catch (InterruptedException e) {
        }
    }
}


@Component
public class UserEventHandler {
    private static final Logger logger = LoggerFactory.getLogger("UserEventHandler");

    @EventListener
    @Async("myCacheThreadPool")
    public void handleEvent(UserEvent event) {
        logger.info("------------EventListener监听方式处理事件：{}", event.getMsg());
        try {
            Thread.sleep(5 * 1000L);
            logger.info("EventListener监听方式事件处理完成,新增用户一条");
        } catch (InterruptedException e) {
        }
    }

}

public class UserEvent extends ApplicationEvent {
    private String msg;

    public UserEvent(Object source, String msg) {
        super(source);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
