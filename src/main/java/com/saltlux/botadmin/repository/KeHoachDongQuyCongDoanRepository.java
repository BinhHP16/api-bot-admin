package com.saltlux.botadmin.repository;

import com.saltlux.botadmin.entity.DongQuyCongDoanEntity;
import com.saltlux.botadmin.entity.KeHoachDongQuyCongDoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KeHoachDongQuyCongDoanRepository extends JpaRepository<KeHoachDongQuyCongDoanEntity, Integer> {

    @Query(value = "SELECT * FROM data_bot_admin.ke_hoach_dong_quy_cong_doan where user_id=:userId and year(ngay_dong)=:year",nativeQuery = true)
    List<KeHoachDongQuyCongDoanEntity> findByUserIdAndYear(Integer userId, Integer year);
}
