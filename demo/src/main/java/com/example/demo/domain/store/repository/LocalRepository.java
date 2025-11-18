package com.example.demo.domain.store.repository;

import com.example.demo.domain.store.entity.Local;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalRepository extends JpaRepository<Local, Long> {
}
