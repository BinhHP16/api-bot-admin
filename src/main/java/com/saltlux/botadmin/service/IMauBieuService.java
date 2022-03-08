package com.saltlux.botadmin.service;

import com.saltlux.botadmin.entity.MauBieuEntity;

public interface IMauBieuService {
    MauBieuEntity findByCode(String code);
}
