package org.example.service;

import org.example.entity.BatchEntity;
import org.example.repo.BatchPageableRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    // TODO. 自定义构建PageRequest分页查询对象
    public Page<BatchEntity> getPageableBatches() {
        Pageable pageable = PageRequest.of(1, 20, Sort.by("id"));
        return this.batchRepository.findAll(pageable);
    }
}