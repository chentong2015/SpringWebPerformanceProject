package org.example.controller;

import org.example.service.BatchPageableService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BatchPageableController {

    private BatchPageableService batchPageableService;

    public BatchPageableController(BatchPageableService batchPageableService) {
        this.batchPageableService = batchPageableService;
    }

    @GetMapping("/init-batches")
    public String initBatches() {
        this.batchPageableService.initBatches();
        return "Init Batches successfully";
    }
}
