package pageable.performance;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pageable.example.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Service
public class WebRepoConcurrencyService {

    private final WebUserRepository webUserRepository;

    public WebRepoConcurrencyService(WebUserRepository webUserRepository) {
        this.webUserRepository = webUserRepository;
    }

    // TODO. 并发不同的Repo分页查询，提高查询性能
    public Page<UserEntity> findAllUsersPerf(Pageable pageable) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<Page<UserEntity>> userPageFuture1 = executor.submit(() -> this.webUserRepository.findAll(pageable));
        Future<Page<UserEntity>> userPageFuture2 = executor.submit(() -> this.webUserRepository.findAll(pageable));

        // 整合不同分页查询结果
        Page<UserEntity> userPage1 = userPageFuture1.get(10, TimeUnit.SECONDS);
        Page<UserEntity> userPage2 = userPageFuture2.get(10, TimeUnit.SECONDS);
        List<UserEntity> userEntityList1 = new ArrayList<>(userPage1.getContent());
        List<UserEntity> userEntityList2 = new ArrayList<>(userPage2.getContent());
        userEntityList1.addAll(userEntityList2);

        return new PageImpl<>(userEntityList1, pageable, userEntityList1.size());
    }
}
