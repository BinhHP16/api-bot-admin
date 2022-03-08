package com.saltlux.botadmin.service;

import com.saltlux.botadmin.entity.MauBieuEntity;
import com.saltlux.botadmin.repository.MauBieuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MauBieuService implements  IMauBieuService{

    @Autowired
    MauBieuRepository repository;

    @Override
    public MauBieuEntity findByCode(String code) {
        return repository.findByCode(code);
    }

    @Override
    public List<MauBieuEntity> findAll() {
        return repository.findAll();
    }
}
