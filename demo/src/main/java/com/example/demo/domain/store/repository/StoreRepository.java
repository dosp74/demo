package com.example.demo.domain.store.repository;

import com.example.demo.domain.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {
    // 중복 검사 메서드
    boolean existsByName(String name);
    boolean existsByBossNumber(Long bossNumber);

    Optional<Store> findByName(String name);
}
