package com.saltlux.botadmin.service;

import com.saltlux.botadmin.entity.NgayNghiPhepEntity;
import com.saltlux.botadmin.repository.NgayNghiPhepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NgayNghiPhepService implements INgayNghiPhepService{

    @Autowired
    NgayNghiPhepRepository repository;

    @Override
    public Double soNgayNghiPhep(Integer userId, Integer month, Integer year) {
        return repository.soNgayNghiPhep(userId, month, year);
    }

    @Override
    public List<NgayNghiPhepEntity> chiTietNgayNghiPhep(Integer userId, Integer month, Integer year) {
        return repository.chiTietNgayNghiPhep(userId, month, year);
    }

    @Override
    public List<NgayNghiPhepEntity> ngayNghiPhepTrongNam(Integer userId, Integer year) {
        return repository.ngayNghiPhepTrongNam(userId, year);
    }
}
