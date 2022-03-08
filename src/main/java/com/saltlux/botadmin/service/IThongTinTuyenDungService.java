package com.saltlux.botadmin.service;

import com.saltlux.botadmin.entity.CategoryTuyenDungEntity;
import com.saltlux.botadmin.entity.ThongTinTuyenDungEntity;

import java.util.List;

public interface IThongTinTuyenDungService {


    List<ThongTinTuyenDungEntity> findByCode(String code);


    List<CategoryTuyenDungEntity> getAll();
}
