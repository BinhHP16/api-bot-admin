package com.saltlux.botadmin.service;

import com.saltlux.botadmin.entity.MauBieuEntity;

import java.util.List;

public interface IMauBieuService {
    MauBieuEntity findByCode(String code);

    List<MauBieuEntity> findAll();
}
