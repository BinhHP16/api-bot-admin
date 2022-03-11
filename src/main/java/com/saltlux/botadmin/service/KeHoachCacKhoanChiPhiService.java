package com.saltlux.botadmin.service;

import com.saltlux.botadmin.dto.congdoan.CacKhoanChiPhiDto;
import com.saltlux.botadmin.entity.KeHoachCacKhoanChiPhiEntity;
import com.saltlux.botadmin.repository.KeHoachCacKhoanChiPhiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class KeHoachCacKhoanChiPhiService implements IKeHoachCacKhoanChiPhiService {

    @Autowired
    KeHoachCacKhoanChiPhiRepository repository;

    @Override
    public List<CacKhoanChiPhiDto> chiQuy(Integer year) {
        List<KeHoachCacKhoanChiPhiEntity> list=repository.findByYear(year);
        List<CacKhoanChiPhiDto> dtos=new ArrayList<>();
        int tongChi=0;
        for (KeHoachCacKhoanChiPhiEntity chiPhi:list) {
            tongChi+=chiPhi.getSoTien();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM");
            String strDate = formatter.format(chiPhi.getThoiGian());
            CacKhoanChiPhiDto dto=new CacKhoanChiPhiDto(chiPhi.getId(),chiPhi.getNoiDung(),chiPhi.getSoTien(),strDate,chiPhi.getGhiChu());
            dtos.add(dto);


        }
        return dtos;
    }

    @Override
    public Integer tongChi(Integer year) {
        List<KeHoachCacKhoanChiPhiEntity> list=repository.findByYear(year);
        List<CacKhoanChiPhiDto> dtos=new ArrayList<>();
        int tongChi=0;
//        for (KeHoachCacKhoanChiPhiEntity chiPhi:list) {
//            tongChi+=chiPhi.getSoTien();
//
//            CacKhoanChiPhiDto dto=new CacKhoanChiPhiDto(chiPhi.getId(),chiPhi.getNoiDung(),chiPhi.getSoTien(),chiPhi.getThoiGian(),chiPhi.getGhiChu());
//            dtos.add(dto);
//
//
//        }
        return tongChi;
    }
}
