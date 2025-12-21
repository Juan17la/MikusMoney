package com.mikusmoney.mikusMoney.repository;

import com.mikusmoney.mikusMoney.entity.Miku;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MikuRepository extends JpaRepository<Miku, Long> {
    
    Optional<Miku> findByPublicCode(String publicCode);
    
    boolean existsByPublicCode(String publicCode);
}
