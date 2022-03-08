package com.saltlux.botadmin.service;

import com.saltlux.botadmin.entity.NgayNghiPhepEntity;

import java.util.List;

public interface INgayNghiPhepService {
    Double soNgayNghiPhep(Integer userId,Integer month, Integer year);

    List<NgayNghiPhepEntity> chiTietNgayNghiPhep(Integer userId, Integer month, Integer year);

    List<NgayNghiPhepEntity> ngayNghiPhepTrongNam(Integer userId, Integer year);
}
