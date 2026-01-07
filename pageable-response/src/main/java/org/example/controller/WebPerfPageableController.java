package org.example.controller;

import java.util.List;
import org.example.entity.UserEntity;
import org.example.service.WebPerfPageableService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebPerfPageableController {

    private final WebPerfPageableService webPerfPageableService;

    public WebPerfPageableController(WebPerfPageableService webPerfPageableService) {
        this.webPerfPageableService = webPerfPageableService;
    }

    @GetMapping("/page/perf")
    public ResponseEntity<List<UserEntity>> getPageableUserPerf(
            @PageableDefault(page = 2, size = 20, sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable) {
        try {
            Page<UserEntity> userEntityPage = this.webPerfPageableService.findAllUsersPerf(pageable);
            return ResponseEntity.ok().body(userEntityPage.getContent());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
