package com.yh.jdbcexercise.student.repository;

import com.yh.jdbcexercise.student.domain.User;
import java.util.Optional;

public interface UserRepository {
     Optional<User> findByUserIdAndUserPassword(String userId, String userPassword);
     Optional<User> findById(String userId);
     int save(User user);
     int updateUserPasswordByUserId(String userId, String userPassword);
     int deleteByUserId(String userId);

}

