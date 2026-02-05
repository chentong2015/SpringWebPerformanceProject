package pageable.performance;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pageable.org.example.entity.UserEntity;

@Repository
public interface WebUserRepository extends CrudRepository<UserEntity, Long> {

    Page<UserEntity> findAll(Pageable pageable);

}
