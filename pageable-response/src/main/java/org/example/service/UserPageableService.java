package org.example.service;

import org.example.entity.UserEntity;
import org.example.repo.UserPageableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserPageableService {

    private UserPageableRepository repository;

    @Autowired
    public UserPageableService(UserPageableRepository repository) {
        this.repository = repository;
    }

    public void initUsers() {
        for (int index = 1; index < 500; index++) {
             UserEntity user = new UserEntity("email " + index, "name " + index);
             this.repository.save(user);
        }
    }

    // 自定义查询返回的Page分页空结果
    public Page<UserEntity> findAllUsers(Pageable pageable) {
        Page<UserEntity> users = this.repository.findAll(pageable);
        if (users.isEmpty()) {
            return new PageImpl<>(Collections.emptyList(), pageable, 0);
        }
        return users;
    }

    public Page<UserEntity> findAllByNameAndEmail(Pageable pageable) {
        return repository.findAllByNameAndEmail("name1", "test@test.com", pageable);
    }
}
