package com.saltlux.botadmin.controller;

import com.saltlux.botadmin.dto.congdoan.*;
import com.saltlux.botadmin.entity.DongQuyCongDoanEntity;
import com.saltlux.botadmin.entity.UserEntity;
import com.saltlux.botadmin.exception.DuplicatedColumnsException;
import com.saltlux.botadmin.multiple_thread.SendEmailFeedbackThread;
import com.saltlux.botadmin.payload.HomThuGopYReq;
import com.saltlux.botadmin.service.*;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Log4j2
@RestController
@Api(tags = "Công đoàn")
@RequestMapping("/api/cong_doan")
public class CongDoanController {

    @Value("${email.to}")
    public  String FRIEND_EMAIL ;

    @Value("${email.from}")
    public  String MY_EMAIL ;


    @Value("${email.password}")
    public  String PASSWORD ;


    @Autowired
    IEmailService emailService;

    @Autowired
    IUserService service;

    @Autowired
    IHomThuGopYService homThuGopYService;

    @Autowired
    ICacKhoanChiPhiService chiPhiService;

    @Autowired
    IDongQuyCongDoanService dongQuyCongDoanService;

    @Autowired
    IKeHoachCacKhoanChiPhiService keHoachChiPhiService;

    @Autowired
    IKeHoachDongQuyCongDoanService keHoachDongQuyCongDoanService;

    @GetMapping("/tong_hop_thu")
    public List<TongHopThuQuyCongDoanDto> tongHopThu(Integer year) {
        List<UserEntity> listUser = service.getAll();


        List<TongHopThuQuyCongDoanDto> converts = new ArrayList<>();
        for (UserEntity user : listUser) {
            List<DongQuyCongDoanEntity> list = dongQuyCongDoanService.findByUserIdAndYear(user.getId(), year);
            int tongTienThang1 = 0;
            int tongTienThang2 = 0;
            int tongTienThang3 = 0;
            int tongTienThang4 = 0;
            int tongTienThang5 = 0;
            int tongTienThang6 = 0;
            int tongTienThang7 = 0;
            int tongTienThang8 = 0;
            int tongTienThang9 = 0;
            int tongTienThang10 = 0;
            int tongTienThang11 = 0;
            int tongTienThang12 = 0;
            int sum=0;
            for (DongQuyCongDoanEntity convert : list) {

                Date date = convert.getNgayDong();
                LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                int month = localDate.getMonthValue();
                if (month == 1) {
                    tongTienThang1 += convert.getSoTien();
                }
                if (month == 2) {
                    tongTienThang2 += convert.getSoTien();
                }
                if (month == 3) {
                    tongTienThang3 += convert.getSoTien();
                }
                if (month == 4) {
                    tongTienThang4 += convert.getSoTien();
                }
                if (month == 5) {
                    tongTienThang5 += convert.getSoTien();
                }
                if (month == 6) {
                    tongTienThang6 += convert.getSoTien();
                }
                if (month == 7) {
                    tongTienThang7 += convert.getSoTien();
                }
                if (month == 8) {
                    tongTienThang8 += convert.getSoTien();
                }
                if (month == 9) {
                    tongTienThang9 += convert.getSoTien();
                }
                if (month == 10) {
                    tongTienThang10 += convert.getSoTien();
                }
                if (month == 11) {
                    tongTienThang11 += convert.getSoTien();
                }
                if (month == 12) {
                    tongTienThang12 += convert.getSoTien();
                }
                sum=tongTienThang1+tongTienThang2+tongTienThang3+tongTienThang4+tongTienThang5+tongTienThang6+tongTienThang7+tongTienThang8+tongTienThang9+
                        tongTienThang10+tongTienThang11+tongTienThang12;

            }
            TongHopThuQuyCongDoanDto dto = new TongHopThuQuyCongDoanDto(user.getHoTen(), tongTienThang1, tongTienThang2, tongTienThang3,
                    tongTienThang4, tongTienThang5, tongTienThang6, tongTienThang7, tongTienThang8, tongTienThang9,
                    tongTienThang10, tongTienThang11, tongTienThang12,sum);
            converts.add(dto);
        }


        return converts;
    }

    @GetMapping("/thu/tong_tien_thu")
    public TongTien tongTien(Integer year) {

        return new TongTien(dongQuyCongDoanService.tongThu(year));
    }


    @GetMapping("/detail_a_person")
    public UserDongQuyConvertDto detailPerson(@RequestParam Integer userId, @RequestParam Integer year) {
        UserEntity user = service.findByUserId(userId);
        List<DongQuyCongDoanEntity> list = dongQuyCongDoanService.findByUserIdAndYear(userId, year);


        List<TongHopThuQuyCongDoanTungNguoiDto> converts = new ArrayList<>();

        int tongTien = 0;
        log.info("Show log to debug");
        for (int i = 1; i <= 12; i++) {
            int tongTienThang = 0;
            for (DongQuyCongDoanEntity convert : list) {

                Date date = convert.getNgayDong();
                LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                int month = localDate.getMonthValue();

                log.info(month);
                if (month == i) {
                    tongTienThang += convert.getSoTien();
                    tongTien += convert.getSoTien();
                }
            }
            TongHopThuQuyCongDoanTungNguoiDto dto = new TongHopThuQuyCongDoanTungNguoiDto("Tháng " + i, tongTienThang);
            converts.add(dto);
        }


        UserDongQuyConvertDto dtos = new UserDongQuyConvertDto(user.getId(),
                user.getHoTen(), tongTien, converts);


        return dtos;
    }
    @GetMapping("/chi_tiet_thu_tung_nguoi")
    public TongHopThuQuyCongDoanDto chiTietThuTungNguoi(@RequestParam Integer userId, @RequestParam Integer year) {
        UserEntity user = service.findByUserId(userId);



            List<DongQuyCongDoanEntity> list = dongQuyCongDoanService.findByUserIdAndYear(userId, year);
            int tongTienThang1 = 0;
            int tongTienThang2 = 0;
            int tongTienThang3 = 0;
            int tongTienThang4 = 0;
            int tongTienThang5 = 0;
            int tongTienThang6 = 0;
            int tongTienThang7 = 0;
            int tongTienThang8 = 0;
            int tongTienThang9 = 0;
            int tongTienThang10 = 0;
            int tongTienThang11 = 0;
            int tongTienThang12 = 0;
            int sum=0;
            for (DongQuyCongDoanEntity convert : list) {

                Date date = convert.getNgayDong();
                LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                int month = localDate.getMonthValue();
                if (month == 1) {
                    tongTienThang1 += convert.getSoTien();
                }
                if (month == 2) {
                    tongTienThang2 += convert.getSoTien();
                }
                if (month == 3) {
                    tongTienThang3 += convert.getSoTien();
                }
                if (month == 4) {
                    tongTienThang4 += convert.getSoTien();
                }
                if (month == 5) {
                    tongTienThang5 += convert.getSoTien();
                }
                if (month == 6) {
                    tongTienThang6 += convert.getSoTien();
                }
                if (month == 7) {
                    tongTienThang7 += convert.getSoTien();
                }
                if (month == 8) {
                    tongTienThang8 += convert.getSoTien();
                }
                if (month == 9) {
                    tongTienThang9 += convert.getSoTien();
                }
                if (month == 10) {
                    tongTienThang10 += convert.getSoTien();
                }
                if (month == 11) {
                    tongTienThang11 += convert.getSoTien();
                }
                if (month == 12) {
                    tongTienThang12 += convert.getSoTien();
                }
                sum=tongTienThang1+tongTienThang2+tongTienThang3+tongTienThang4+tongTienThang5+tongTienThang6+tongTienThang7+tongTienThang8+tongTienThang9+
                        tongTienThang10+tongTienThang11+tongTienThang12;

            }
            TongHopThuQuyCongDoanDto dto = new TongHopThuQuyCongDoanDto(user.getHoTen(), tongTienThang1, tongTienThang2, tongTienThang3,
                    tongTienThang4, tongTienThang5, tongTienThang6, tongTienThang7, tongTienThang8, tongTienThang9,
                    tongTienThang10, tongTienThang11, tongTienThang12,sum);
        return dto;
    }

    @GetMapping("/chi")
    public List<CacKhoanChiPhiDto> tongHopChiTieu(@RequestParam Integer year) {

        return chiPhiService.chiQuy(year);
    }

    @GetMapping("/chi/tong_chi")
    public TongTien tongChi(@RequestParam Integer year) {

        return new TongTien(chiPhiService.tongChi(year));
    }

    @PostMapping("/hom_thu_gop_y")
    public Integer save(@Valid @RequestBody HomThuGopYReq request) throws MissingServletRequestParameterException, InvocationTargetException, NoSuchMethodException, DuplicatedColumnsException, IllegalAccessException, MessagingException {
        int anDanh=request.getAnDanh();
        String hoTen=request.getNguoiGui();
        String noiDung=request.getNoiDung();
        String tieuDe=request.getTieuDe();
        SendEmailFeedbackThread T1 = new SendEmailFeedbackThread("SendEmail",tieuDe,anDanh, hoTen,noiDung, FRIEND_EMAIL, MY_EMAIL,PASSWORD);
        T1.start();
        if (request == null) {
            throw new MissingServletRequestParameterException(null, null);
        }
         homThuGopYService.saveHomThuGopY(request);


//        emailService.sendMailHTML(tieuDe,anDanh, hoTen, noiDung);
        return 1;
    }

    @GetMapping("/tong_hop_thu_chi")
    public List<KeHoachThuChiDto> tongHopThuChi(@RequestParam Integer year) {
        List<KeHoachThuChiDto> list = new ArrayList<>();


        Integer tongThuNamTruoc = dongQuyCongDoanService.tongThu(year - 1);
        Integer tongChiNamTruoc = chiPhiService.tongChi(year - 1);
        int tongTonCuoiKy = 0;

        Integer soDuDauNam = tongThuNamTruoc - tongChiNamTruoc;

        KeHoachThuChiDto duDauNam = new KeHoachThuChiDto("Số dư đầu năm", soDuDauNam, 0, soDuDauNam, 0);
        list.add(duDauNam);

        Integer tongThuTrongNam = dongQuyCongDoanService.tongThu(year);

        KeHoachThuChiDto thuTrongNam = new KeHoachThuChiDto("Thu trong năm", tongThuTrongNam, 0, tongThuTrongNam + soDuDauNam, 0);
        list.add(thuTrongNam);

        Integer tongChiTrongNam = chiPhiService.tongChi(year);
        tongTonCuoiKy += tongThuTrongNam + soDuDauNam - tongChiTrongNam;
        KeHoachThuChiDto chiTrongNam = new KeHoachThuChiDto("Chi trong năm", 0, tongChiTrongNam, tongTonCuoiKy, 0);
        list.add(chiTrongNam);

        int tongGhiCo = soDuDauNam + tongThuTrongNam;
        int tongGhiNo = tongChiTrongNam;

        List<CacKhoanChiPhiDto> dsChi = chiPhiService.chiQuy(year);
        for (CacKhoanChiPhiDto item : dsChi) {
            KeHoachThuChiDto dto = new KeHoachThuChiDto(item.getNoiDung(), 0, item.getSoTien(), 0, 1);
            tongGhiNo += item.getSoTien();
            list.add(dto);
        }

        KeHoachThuChiDto congPhatSinh = new KeHoachThuChiDto("Cộng phát sinh trong kỳ", tongGhiCo, tongGhiNo, tongTonCuoiKy - tongGhiNo, 0);
        list.add(congPhatSinh);

        return list;
    }




    @GetMapping("/ke_hoach_thu_chi")
    public List<KeHoachThuChiDto> keHoachThuChi(@RequestParam Integer year) {
        List<KeHoachThuChiDto> list = new ArrayList<>();


        Integer tongThuNamTruoc = dongQuyCongDoanService.tongThu(year - 1);
        Integer tongChiNamTruoc = chiPhiService.tongChi(year - 1);
        int tongTonCuoiKy = 0;

        Integer soDuDauNam = tongThuNamTruoc - tongChiNamTruoc;

        KeHoachThuChiDto duDauNam = new KeHoachThuChiDto("Số dư đầu năm", soDuDauNam, 0, soDuDauNam, 0);
        list.add(duDauNam);

        Integer tongThuTrongNam = keHoachDongQuyCongDoanService.tongThu(year);

        KeHoachThuChiDto thuTrongNam = new KeHoachThuChiDto("Thu trong năm", tongThuTrongNam, 0, tongThuTrongNam + soDuDauNam, 0);
        list.add(thuTrongNam);

        Integer tongChiTrongNam = keHoachChiPhiService.tongChi(year);
        tongTonCuoiKy += tongThuTrongNam + soDuDauNam - tongChiTrongNam;
        KeHoachThuChiDto chiTrongNam = new KeHoachThuChiDto("Chi trong năm", 0, tongChiTrongNam, tongTonCuoiKy, 0);
        list.add(chiTrongNam);

        int tongGhiCo = soDuDauNam + tongThuTrongNam;
        int tongGhiNo = tongChiTrongNam;

        List<CacKhoanChiPhiDto> dsChi = keHoachChiPhiService.chiQuy(year);
        for (CacKhoanChiPhiDto item : dsChi) {
            KeHoachThuChiDto dto = new KeHoachThuChiDto(item.getNoiDung(), 0, item.getSoTien(), 0, 1);
            tongGhiNo += item.getSoTien();
            list.add(dto);
        }

        KeHoachThuChiDto congPhatSinh = new KeHoachThuChiDto("Cộng phát sinh trong kỳ", tongGhiCo, tongGhiNo, tongTonCuoiKy - tongGhiNo, 0);
        list.add(congPhatSinh);

        return list;
    }

}
