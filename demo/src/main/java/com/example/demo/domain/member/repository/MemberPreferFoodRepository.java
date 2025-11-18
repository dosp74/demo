package com.example.demo.domain.member.repository;

import com.example.demo.domain.member.entity.mapping.MemberPreferFood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberPreferFoodRepository extends JpaRepository<MemberPreferFood, Long> {
}
