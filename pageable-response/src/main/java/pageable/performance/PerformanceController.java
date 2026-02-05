package pageable.performance;

import java.util.List;
import pageable.example.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PerformanceController {

    private final WebEntityManagerService webEntityManagerService;
    private final WebRepoConcurrencyService repoConcurrencyService;

    public PerformanceController(WebEntityManagerService webEntityManagerService, WebRepoConcurrencyService repoConcurrencyService) {
        this.webEntityManagerService = webEntityManagerService;
        this.repoConcurrencyService = repoConcurrencyService;
    }

    @GetMapping("/page/perf1")
    public ResponseEntity<List<UserEntity>> getUsersByEntityManager(@PageableDefault(page = 2, size = 20, sort = {"id"},
            direction = Sort.Direction.ASC) Pageable pageable) {
        try {
            Page<UserEntity> userEntityPage = this.webEntityManagerService.findAllUsersPerf(pageable);
            return ResponseEntity.ok().body(userEntityPage.getContent());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/page/perf2")
    public ResponseEntity<List<UserEntity>> getUsersByRepos(@PageableDefault(page = 2, size = 20, sort = {"id"},
            direction = Sort.Direction.ASC) Pageable pageable) {
        try {
            Page<UserEntity> userEntityPage = this.repoConcurrencyService.findAllUsersPerf(pageable);
            return ResponseEntity.ok().body(userEntityPage.getContent());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
