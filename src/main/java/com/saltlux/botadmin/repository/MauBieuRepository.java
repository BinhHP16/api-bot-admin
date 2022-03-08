package com.saltlux.botadmin.repository;

import com.saltlux.botadmin.entity.MauBieuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MauBieuRepository extends JpaRepository<MauBieuEntity, Integer> {

    @Query(value = "SELECT * FROM data_bot_admin.mau_bieu where code=:code",nativeQuery = true)
    MauBieuEntity findByCode(String code);
}
