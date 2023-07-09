package com.company.jpa.repository;

import com.company.jpa.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardJpaRepository extends JpaRepository<Card,Long> {
}
