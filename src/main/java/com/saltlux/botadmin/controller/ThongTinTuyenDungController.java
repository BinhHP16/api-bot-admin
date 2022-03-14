package com.saltlux.botadmin.controller;

import com.saltlux.botadmin.dto.tuyendung.ThongTinTuyenDungDto;
import com.saltlux.botadmin.dto.tuyendung.TuyenDungDto;
import com.saltlux.botadmin.entity.CategoryTuyenDungEntity;
import com.saltlux.botadmin.entity.ThongTinTuyenDungEntity;
import com.saltlux.botadmin.service.IThongTinTuyenDungService;
import io.swagger.annotations.Api;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Api(tags = "Tuyển dụng")
@RestController
@RequestMapping("/api/tuyen_dung")
public class ThongTinTuyenDungController {


    @Autowired
    private IThongTinTuyenDungService service;

    @GetMapping("/")
    public List<TuyenDungDto> getCategory() {
        List<TuyenDungDto> dtos=new ArrayList<>();

       List<CategoryTuyenDungEntity> list= service.getAll();
       for (CategoryTuyenDungEntity entity : list) {
           List<ThongTinTuyenDungDto> dtosChiTiet = new ArrayList<>();
           List<ThongTinTuyenDungEntity> entities = service.findByCode(entity.getCode());
           for (ThongTinTuyenDungEntity entityChiTiet : entities) {
               ThongTinTuyenDungDto dto = new ThongTinTuyenDungDto(entityChiTiet.getId(), entityChiTiet.getTieuDe(), entityChiTiet.getNoiDung());
               dtosChiTiet.add(dto);
           }
           String strHTML= Jsoup.parse(dtosChiTiet.get(0).getNoiDung()).wholeText();
           TuyenDungDto dtoTuyenDung=new TuyenDungDto(entity.getId(),entity.getViTri(),entity.getCode(),entity.getThoiGian(),entity.getDiaDiem(),entity.getMucLuong(),strHTML);
           dtos.add(dtoTuyenDung);
       }

        return dtos;
    }

    @GetMapping("/chi_tiet")
    public List<ThongTinTuyenDungDto> findByCode(@RequestParam String code) {

        List<ThongTinTuyenDungDto> dtos = new ArrayList<>();
        List<ThongTinTuyenDungEntity> entities = service.findByCode(code);
        for (ThongTinTuyenDungEntity entity : entities) {
            ThongTinTuyenDungDto dto = new ThongTinTuyenDungDto(entity.getId(), entity.getTieuDe(), entity.getNoiDung());
            dtos.add(dto);
        }

        return dtos;

    }

}
