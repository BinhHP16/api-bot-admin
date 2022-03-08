package com.saltlux.botadmin.service;

import com.saltlux.botadmin.entity.KeHoachDongQuyCongDoanEntity;

import java.util.List;

public interface IKeHoachDongQuyCongDoanService {
    List<KeHoachDongQuyCongDoanEntity> findByUserIdAndYear(Integer userId, Integer year);

    Integer tongThu(Integer year);
}
