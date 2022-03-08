package com.saltlux.botadmin.repository;

import com.saltlux.botadmin.entity.NgayLeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NgayLeRepository extends JpaRepository<NgayLeEntity, Integer> {

    @Query(value = "SELECT * FROM data_bot_admin.ngay_le where nam=:year",nativeQuery = true)
    List<NgayLeEntity> finByYear(int year);
}
