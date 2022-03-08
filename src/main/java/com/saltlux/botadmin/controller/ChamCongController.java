package com.saltlux.botadmin.controller;

import com.saltlux.botadmin.dto.chamcong.ChamCongDto;
import com.saltlux.botadmin.dto.chamcong.ChiTietDiMuonDto;
import com.saltlux.botadmin.dto.chamcong.UserCheckinConvertDto;
import com.saltlux.botadmin.dto.ngayle.NgayLeDto;
import com.saltlux.botadmin.dto.ngaynghiphep.NgayNghiPhepConvertDto;
import com.saltlux.botadmin.dto.ngaynghiphep.UserNgayNghiPhepDto;
import com.saltlux.botadmin.entity.CheckinEntity;
import com.saltlux.botadmin.entity.NgayNghiPhepEntity;
import com.saltlux.botadmin.entity.UserEntity;
import com.saltlux.botadmin.service.ICheckinService;
import com.saltlux.botadmin.service.INgayLeService;
import com.saltlux.botadmin.service.INgayNghiPhepService;
import com.saltlux.botadmin.service.IUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Api(tags = "Chấm công")
@RestController
@RequestMapping("/api/cham_cong")
public class ChamCongController {

    @Autowired
    INgayLeService ngayLeService;

    @Autowired
    IUserService service;

    @Autowired
    ICheckinService serviceCheckin;

    @Autowired
    INgayNghiPhepService ngayNghiPhepService;


    @Value("${thoiGianLamViecQuyDinh}")
    private String thoiGianLamViecQuyDinh;

//    @GetMapping("/ngay_nghi_phep")
//    public List<UserNgayNghiPhepDto> getAll() {
//        List<UserEntity> listUser = service.getAll();
//        List<UserNgayNghiPhepDto> listNgayNghiPhep = new ArrayList<>();
//        for (UserEntity user : listUser) {
//            List<NgayNghiPhepConvertDto> converts = new ArrayList<>();
//            Double count = 0.0;
//            for (NgayNghiPhepEntity convert : user.getListNgayNghiPhep()) {
//                NgayNghiPhepConvertDto ngayNghi = new NgayNghiPhepConvertDto(convert.getId(), convert.getNgayNghiPhep(), convert.getManDay());
//                converts.add(ngayNghi);
//                count += convert.getManDay();
//            }
//
//            UserNgayNghiPhepDto dto = new UserNgayNghiPhepDto(user.getId(), user.getHoTen(), user.getSoNgayNghiPhepTieuChuan(), user.getBoPhan(), converts, count, (user.getSoNgayNghiPhepTieuChuan() - count));
//            listNgayNghiPhep.add(dto);
//        }
//
//        return listNgayNghiPhep;
//    }

    @GetMapping("/ngay_nghi_phep_tung_nguoi/{userId}")
    public UserNgayNghiPhepDto thongTinNgayNghiPhep(@PathVariable(name = "userId") Integer userId, @RequestParam Integer year) {
        UserEntity user = service.findByUserId(userId);
        List<NgayNghiPhepEntity> nghiPhep = ngayNghiPhepService.ngayNghiPhepTrongNam(userId, year);

        List<NgayNghiPhepConvertDto> converts = new ArrayList<>();
        Double count = 0.0;
        for (NgayNghiPhepEntity convert : nghiPhep) {
            NgayNghiPhepConvertDto ngayNghi = new NgayNghiPhepConvertDto(convert.getId(), convert.getNgayNghiPhep(), convert.getManDay());
            converts.add(ngayNghi);
            count += convert.getManDay();
        }

        UserNgayNghiPhepDto dto = new UserNgayNghiPhepDto(user.getId(), user.getHoTen(), user.getSoNgayNghiPhepTieuChuan(), user.getBoPhan(), converts, count, (user.getSoNgayNghiPhepTieuChuan() - count));
        return dto;
    }


    @GetMapping("/chi_tiet_ngay_nghi_phep_tung_nguoi/{userId}")
    public UserNgayNghiPhepDto chiTietNgayNghiPhep(@PathVariable(name = "userId") Integer userId,
                                                @RequestParam Integer month, @RequestParam Integer year) {
        List<NgayNghiPhepEntity> nghiPhep = ngayNghiPhepService.chiTietNgayNghiPhep(userId, month, year);
        UserEntity user = service.findByUserId(userId);
        List<NgayNghiPhepConvertDto> converts = new ArrayList<>();
        Double count = 0.0;
        for (NgayNghiPhepEntity convert : nghiPhep) {
            NgayNghiPhepConvertDto ngayNghi = new NgayNghiPhepConvertDto(convert.getId(), convert.getNgayNghiPhep(), convert.getManDay());
            converts.add(ngayNghi);
            count += convert.getManDay();
        }

        UserNgayNghiPhepDto dto = new UserNgayNghiPhepDto(user.getId(), user.getHoTen(), user.getSoNgayNghiPhepTieuChuan(), user.getBoPhan(), converts, count);
        return dto;
    }
//
//    @GetMapping("/checkin")
//    public List<UserCheckinConvertDto> checkin() {
//        List<UserEntity> listUser = service.getAll();
//        List<UserCheckinConvertDto> listCheckin = new ArrayList<>();
//        for (UserEntity user : listUser) {
//            List<Date> converts = new ArrayList<>();
//
//            for (CheckinEntity convert : user.getListCheckin()) {
//
//                converts.add(convert.getThoiGianCheckIn());
//            }
//            UserCheckinConvertDto dto = new UserCheckinConvertDto(user.getId(), user.getHoTen(), user.getBoPhan(), user.getSdt(), user.getEmail(), converts);
//            listCheckin.add(dto);
//        }
//
//        return listCheckin;
//    }

//
//    @GetMapping("/checkin/{userId}")
//    public UserCheckinConvertDto chiTietCheckin(@PathVariable(name = "userId") Integer userId, @RequestParam Integer month) {
//        UserEntity user = service.findByUserId(userId);
//
//        List<CheckinEntity> checkins = serviceCheckin.findByUserIdAndMonth(userId, month);
//
//
//        List<Date> converts = new ArrayList<>();
//
//        for (CheckinEntity convert : checkins) {
////            if(convert.getThoiGianCheckIn()>thoiGianLamViecQuyDinh && convert.getThoiGianCheckIn().getDate()==month){
////            converts.add(convert.getThoiGianCheckIn());
////        }
//            converts.add(convert.getThoiGianCheckIn());
//
//        }
//
//        UserCheckinConvertDto dto = new UserCheckinConvertDto(user.getId(), user.getHoTen(), user.getBoPhan(), user.getSdt(), user.getEmail(), converts);
//        return dto;
//    }

    @GetMapping("/{userId}")
    public ChamCongDto chamCong(@PathVariable(name = "userId") Integer userId, @RequestParam Integer month, @RequestParam Integer year) {
        UserEntity user = service.findByUserId(userId);
        String userName = user.getHoTen();
        String thoiGian = "" + month + "/" + year;
        Double soNgayNghiPhep = ngayNghiPhepService.soNgayNghiPhep(userId, month, year);
        if (soNgayNghiPhep == null) {
            soNgayNghiPhep = 0.0;
        }
        List<CheckinEntity> listCheckin = serviceCheckin.findByUserIdAndMonthAndYearCheck(userId, month, year);
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        String thoiGianLamViecQuyDinh = " 08:00:00";
        long hours = 0;
        long minutes = 0;
        for (CheckinEntity entity : listCheckin) {
            Date d1 = null;
            Date d2 = null;
            try {
                d1 = format.parse(thoiGianLamViecQuyDinh);
                d2 = format.parse(entity.getThoiGianCheckIn().toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (d2.getTime() > d1.getTime()) {
                // Get msec from each, and subtract.
                long diff = d2.getTime() - d1.getTime();
                long diffSeconds = diff / 1000 % 60;
                long diffMinutes = diff / (60 * 1000) % 60;
                long diffHours = diff / (60 * 60 * 1000);
                hours += diffHours;
                minutes += diffMinutes;
            }

        }
        long tongThoiGian = hours * 60 + minutes;
        long tongThoiGianDiMuon = tongThoiGian;
        long ngayDiMuon = 0;

        while (tongThoiGianDiMuon > 240) {
            tongThoiGianDiMuon = tongThoiGianDiMuon % 240;
            ngayDiMuon += 0.5;

        }
//        List<NgayLeDto> listNgayle
        int soNgayT7CN=0;
        int holiday=0;
        int tongSoNgayCong=getDay(month,year);
        for (int i = 0; i <=tongSoNgayCong;i++) {
            Calendar cal = Calendar.getInstance();
            cal.set(year, month, i);
            DateFormat formatter = new SimpleDateFormat("EEEE", Locale.getDefault());
            System.out.println(formatter.format(cal.getTime()));
            if(formatter.format(cal.getTime()).equals("Saturday")||formatter.format(cal.getTime()).equals("Sunday")){
                soNgayT7CN+=1;
            }
        }

        int ngayCongDuKien=tongSoNgayCong-soNgayT7CN;


        double ngayCongThucTe = ngayCongDuKien - soNgayNghiPhep - ngayDiMuon;


        ChamCongDto chamCong = new ChamCongDto(userName, thoiGian, soNgayNghiPhep, tongThoiGian, ngayCongThucTe);
        return chamCong;
    }

    @GetMapping("/chi_tiet_di_muon/{userId}")
    public List<ChiTietDiMuonDto> chiTietDiMuon(@PathVariable(name = "userId") Integer userId, @RequestParam Integer month, @RequestParam Integer year) {

        String thoiGian = "" + month + "/" + year;

        List<CheckinEntity> listCheckin = serviceCheckin.findByUserIdAndMonthAndYearCheck(userId, month, year);
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");


        List<ChiTietDiMuonDto> list = new ArrayList<>();
        for (CheckinEntity entity : listCheckin) {
            Date d1 = null;
            Date d2 = null;
            try {
                d1 = format.parse(thoiGianLamViecQuyDinh);
                d2 = format.parse(entity.getThoiGianCheckIn().toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (d2.getTime() > d1.getTime()) {
                // Get msec from each, and subtract.
                long diff = d2.getTime() - d1.getTime();
                long diffSeconds = diff / 1000 % 60;
                long diffMinutes = diff / (60 * 1000) % 60;
                long diffHours = diff / (60 * 60 * 1000);
                long minutes = diffHours * 60 + diffMinutes;

                ChiTietDiMuonDto chiTietDiMuonDto = new ChiTietDiMuonDto(thoiGian, entity.getNgayCheckIn(), minutes);
                list.add(chiTietDiMuonDto);
            }

        }
        return list;
    }




    boolean isCheck(int nam) {
        return ((nam % 4 == 0 && nam % 100 != 0) || nam % 400 == 0);
    }

    public int getDay(int thang, int nam) {
        switch (thang) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                if (isCheck(nam))
                    return 29;
                else
                    return 28;
            default:
                System.out.println("không hợp lệ");
                return 0;
        }
    }

}
