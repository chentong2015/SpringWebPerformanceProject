package org.springboot.async;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AsyncTaskService {

    // TODO. 检测线程池的注入和配置信息
    public AsyncTaskService(@Qualifier("import") ThreadPoolTaskExecutor threadPoolTaskExecutor) {
        System.out.println("Pool Size: " + threadPoolTaskExecutor.getPoolSize());
        System.out.println("Core Pool Size: " + threadPoolTaskExecutor.getCorePoolSize());
        System.out.println("Max Pool Size: " + threadPoolTaskExecutor.getMaxPoolSize());
        System.out.println("Active Count: " + threadPoolTaskExecutor.getActiveCount());
        System.out.println("Queue Capacity: " + threadPoolTaskExecutor.getQueueCapacity());
    }

    // TODO. 使用特定线程池中线程 !!
    @Async("import")
    public void doImport(String calledName) throws InterruptedException {
        String threadName = Thread.currentThread().getName();
        System.out.println(calledName + ": Run AsyncTask by: " + threadName);

        int duration = new Random().nextInt(10, 30);
        Thread.sleep(duration * 1000L);

        System.out.println(calledName + ": Finish AsyncTask by: " + threadName);
    }
}
