package pageable.performance;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import pageable.org.example.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.*;

@Service
public class WebEntityManagerService {

    private final EntityManager entityManager;

    private final String queryCount = "select count(u.id) from UserEntity u";
    private final String queryData = "select u from UserEntity u order by u.id offset :offset rows fetch first :page_size rows only";

    private WebEntityManagerService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // TODO. 异步执行Count统计: 统计结果不会被Lazy加载，保证使用安全
    public Page<UserEntity> findAllUsersPerf(Pageable pageable) throws Exception {
        EntityManagerFactory emf = entityManager.getEntityManagerFactory();

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Long> countFuture = executor.submit(() -> {
            try (EntityManager em = emf.createEntityManager()) {
                return em.createQuery(queryCount, Long.class).getSingleResult();
            }
        });

        List<UserEntity> userEntityList = entityManager.createQuery(queryData, UserEntity.class)
                .setParameter("offset", pageable.getOffset())
                .setParameter("page_size", pageable.getPageSize())
                .getResultList();

        Long total = countFuture.get(10, TimeUnit.SECONDS);
        return new PageImpl<>(userEntityList, pageable, total);
    }
}