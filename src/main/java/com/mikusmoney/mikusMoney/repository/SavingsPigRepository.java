package com.mikusmoney.mikusMoney.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mikusmoney.mikusMoney.entity.SavingsPig;

public interface SavingsPigRepository extends JpaRepository<SavingsPig, Long> {
    @Query("SELECT COUNT(sp) FROM SavingsPig sp WHERE sp.miku.id = :mikuId AND sp.broken = false")
    int getNumberOfPigsByOwnerId(@Param("mikuId") Long mikuId);

    @Query("SELECT sp FROM SavingsPig sp WHERE sp.miku.id = :mikuId AND sp.broken = false")
    List<SavingsPig> findPigsByMikuId(@Param("mikuId") Long mikuId);
    
    SavingsPig findByIdAndMikuId(Long id, Long mikuId);
}
