package pageable.example.repository;

import pageable.example.entity.BatchEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BatchPageableRepository extends CrudRepository<BatchEntity, Long>, PagingAndSortingRepository<BatchEntity, Long> {

    // 继承自PagingAndSortingRepository接口
    Page<BatchEntity> findAll(Pageable pageable);

    Page<BatchEntity> findByIdIn(List<Long> ids, Pageable pageable);
}
