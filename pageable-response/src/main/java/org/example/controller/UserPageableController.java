package org.example.controller;

import org.example.entity.UserEntity;
import org.example.service.UserPageableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserPageableController {

    private final UserPageableService userPageableService;

    @Autowired
    public UserPageableController(UserPageableService userPageableService) {
        this.userPageableService = userPageableService;
    }

    @GetMapping("init-users")
    public String initUsers() {
        this.userPageableService.initUsers();
        return "Users init successfully";
    }

    // TODO. 定义PageableDefault默认分页查询参数
    @GetMapping("/list/users")
    public ResponseEntity<List<UserEntity>> findAllByNameAndEmail(
            @PageableDefault(page = 2, size = 20, sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable) {
        Page<UserEntity> userEntities = this.userPageableService.findAllUsers(pageable);
        return ResponseEntity.ok().body(userEntities.getContent());
    }
}