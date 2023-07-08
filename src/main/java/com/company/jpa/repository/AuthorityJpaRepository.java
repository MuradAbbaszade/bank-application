package com.company.jpa.repository;

import com.company.jpa.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityJpaRepository extends JpaRepository<Authority,Long> {
}
