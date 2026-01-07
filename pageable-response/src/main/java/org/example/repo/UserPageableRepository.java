package org.example.repo;

import org.example.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPageableRepository extends CrudRepository<UserEntity, Long> {

    // TODO. Data JPA自动为分页查询生成数据和Count两个查询 -> 并发性能优化
    // Hibernate: select ue1_0.id,ue1_0.email,ue1_0.name from t_users ue1_0 order by ue1_0.id offset ? rows fetch first ? rows only
    // Hibernate: select count(ue1_0.id) from t_users ue1_0
    Page<UserEntity> findAll(Pageable pageable);

    // 自定义Native Query执行分页查询
    @Query(value = "select * from t_users t where upper(t.name) = :name and t.email = :email order by t.id", nativeQuery = true)
    Page<UserEntity> findAllByNameAndEmail(@Param("name") String name, @Param("email") String email, Pageable pageable);
}
