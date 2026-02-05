package pageable.org.example.controller;

import pageable.org.example.entity.BatchEntity;
import pageable.org.example.service.BatchPageableService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BatchPageableController {

    private final BatchPageableService batchPageableService;

    public BatchPageableController(BatchPageableService batchPageableService) {
        this.batchPageableService = batchPageableService;
    }

    @GetMapping("/init-batches")
    public String initBatches() {
        this.batchPageableService.initBatches();
        return "Init Batches successfully";
    }

    @GetMapping("/page/batches")
    public ResponseEntity<List<BatchEntity>> getPageableBatches() {
        Page<BatchEntity> batchEntityPage = this.batchPageableService.getPageableBatches();
        return ResponseEntity.ok().body(batchEntityPage.getContent());
    }
}
