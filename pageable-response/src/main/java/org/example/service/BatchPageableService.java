package org.example.service;

import org.example.entity.BatchEntity;
import org.example.repo.BatchPageableRepository;
import org.springframework.stereotype.Service;

@Service
public class BatchPageableService {

    private final BatchPageableRepository batchRepository;

    public BatchPageableService(BatchPageableRepository batchRepository) {
        this.batchRepository = batchRepository;
    }

    public void initBatches() {
        for (int index = 1; index < 500; index++) {
            BatchEntity batchEntity = new BatchEntity(index,  "name " + index, "label " + index);
            this.batchRepository.save(batchEntity);
        }
    }
}