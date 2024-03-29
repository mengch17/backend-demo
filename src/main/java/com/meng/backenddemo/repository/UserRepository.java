package com.meng.backenddemo.repository;

import com.meng.backenddemo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * DAO == Repository
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    UserEntity findByUserUuid(String userUuid);
}
