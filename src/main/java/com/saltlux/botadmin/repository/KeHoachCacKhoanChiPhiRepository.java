package com.saltlux.botadmin.repository;

import com.saltlux.botadmin.entity.KeHoachCacKhoanChiPhiEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KeHoachCacKhoanChiPhiRepository extends JpaRepository<KeHoachCacKhoanChiPhiEntity, Integer> {

    @Query(value = " SELECT * FROM data_bot_admin.ke_hoach_cac_khoan_chi_phi where year(thoi_gian)=:year", nativeQuery = true)
    List<KeHoachCacKhoanChiPhiEntity> findByYear(Integer year);

    @Query(value = "SELECT sum(so_tien) FROM data_bot_admin.ke_hoach_cac_khoan_chi_phi where year(thoi_gian)=:year", nativeQuery = true)
    Integer tongChiNam(Integer year);

}
