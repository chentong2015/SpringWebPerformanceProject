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

    // 默认根据Pageable参数值查询分页数据
    Page<UserEntity> findAll(Pageable pageable);

    // 自定义分页查询的Native Query, 传递Pageable参数
    @Query(value = "select * from t_users t where upper(t.name) = :name and t.email = :email order by t.id", nativeQuery = true)
    Page<UserEntity> findAllByNameAndEmail(@Param("name") String name, @Param("email") String email, Pageable pageable);
}
