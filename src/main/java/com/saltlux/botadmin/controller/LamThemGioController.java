package com.saltlux.botadmin.controller;

import com.saltlux.botadmin.dto.lamthemgio.TinhThuNhapLamThemGioDto;
import com.saltlux.botadmin.dto.lamthemgio.UserLamThemGioDto;
import com.saltlux.botadmin.service.ILamThemGioService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Làm thêm giờ")
@RestController
@RequestMapping("/api/lam_them_gio")
public class LamThemGioController {

    @Autowired
    ILamThemGioService service;

    @GetMapping("/{userId}")
    public UserLamThemGioDto lamThemGio(@PathVariable Integer userId){
       return service.lamThemGio(userId);
    }

@GetMapping("/tinh_thu_nhap_lam_them_gio")
public TinhThuNhapLamThemGioDto tinhThuNhapLamThemGio(@RequestParam Double soGiolamThemNgayThuong, @RequestParam Double soGiolamThemNgayThu7Tuan1,
                                                      @RequestParam Double soGiolamThemNgayThu7Tuan2, @RequestParam Double soGiolamThemNgayThu7Tuan3,
                                                      @RequestParam Double soGiolamThemNgayThu7Tuan4, @RequestParam Double soGiolamThemNgayCN,
                                                      @RequestParam Double soGiolamThemNgayLe, @RequestParam Integer tienCong1Gio){


    double tongSoGioLamThemThu7=soGiolamThemNgayThu7Tuan1+soGiolamThemNgayThu7Tuan2+soGiolamThemNgayThu7Tuan3+
            soGiolamThemNgayThu7Tuan4;
    int B=0;
    if(tongSoGioLamThemThu7 < 4){
        B=20;
    }
    if(tongSoGioLamThemThu7>=4 && tongSoGioLamThemThu7<6){
        B=30;
    }
    if(tongSoGioLamThemThu7>=6 && tongSoGioLamThemThu7<8){
        B=40;
    }
    if(tongSoGioLamThemThu7>=8){
        B=50;
    }

    int tongTienLamThemThu7=B*23000;

    double lamThemGioMienThue=tienCong1Gio*(soGiolamThemNgayThuong*0.5+soGiolamThemNgayCN+soGiolamThemNgayLe*2);
    double lamThemGioChiuThue=tienCong1Gio*(soGiolamThemNgayThuong+soGiolamThemNgayCN+soGiolamThemNgayLe) + tongTienLamThemThu7;
    TinhThuNhapLamThemGioDto dto =new TinhThuNhapLamThemGioDto(lamThemGioMienThue,lamThemGioChiuThue);

    return dto;
}


}
