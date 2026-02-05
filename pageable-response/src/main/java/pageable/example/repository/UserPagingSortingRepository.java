package pageable.example.repository;

import pageable.example.entity.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

// PagingAndSortingRepository 提供sort和Pageable的API
@Repository
public interface UserPagingSortingRepository extends PagingAndSortingRepository<UserEntity, Long> {

    // Iterable<T> findAll(Sort sort);

    // Page<T> findAll(Pageable pageable);

}
