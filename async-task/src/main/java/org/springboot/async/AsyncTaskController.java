package org.springboot.async;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AsyncTaskController {

    private final AsyncTaskService asyncTaskService;

    public AsyncTaskController(AsyncTaskService asyncTaskService) {
        this.asyncTaskService = asyncTaskService;
    }

    // TODO. 异步线程处理耗时请求，立即返回Response
    @GetMapping(value = "ping/async")
    public ResponseEntity<Map<String, String>> async() throws InterruptedException {
        this.asyncTaskService.doImport("Ping Async Endpoints");

        Map<String, String> response = new HashMap<>();
        response.put("message", "Request is under process");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // TODO. 线程池处理并发请求，复用线程
    @GetMapping("import/async")
    public ResponseEntity<String> importList() throws InterruptedException {
        System.out.println(Runtime.getRuntime().availableProcessors());
        System.out.println("Start importing tasks");

        for (int i = 0; i < 50; i++) {
            this.asyncTaskService.doImport("task patch " + i);
        }
        System.out.println("End importing tasks");
        return ResponseEntity.ok("DONE");
    }
}
