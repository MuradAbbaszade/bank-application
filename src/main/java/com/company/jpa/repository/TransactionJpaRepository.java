package com.company.jpa.repository;

import com.company.jpa.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionJpaRepository extends JpaRepository<Transaction,Long> {
}
