package com.saltlux.botadmin.controller;

import com.saltlux.botadmin.dto.chamcong.ChamCongDto;
import com.saltlux.botadmin.dto.chamcong.ChiTietDiMuonDto;
import com.saltlux.botadmin.dto.ngaynghiphep.NgayNghiPhepConvertDto;
import com.saltlux.botadmin.dto.ngaynghiphep.UserNgayNghiPhepDto;
import com.saltlux.botadmin.entity.CheckinEntity;
import com.saltlux.botadmin.entity.NgayNghiPhepEntity;
import com.saltlux.botadmin.entity.UserEntity;
import com.saltlux.botadmin.enumDayOfWeeks.DayOfWeeks;
import com.saltlux.botadmin.service.ICheckinService;
import com.saltlux.botadmin.service.INgayLeService;
import com.saltlux.botadmin.service.INgayNghiPhepService;
import com.saltlux.botadmin.service.IUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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


    @GetMapping("/thong_tin_ngay_nghi_phep")
    public UserNgayNghiPhepDto thongTinNgayNghiPhep(@RequestParam Integer userId, @RequestParam Integer year) {
        UserEntity user = service.findByUserId(userId);
        List<NgayNghiPhepEntity> nghiPhep = ngayNghiPhepService.ngayNghiPhepTrongNam(userId, year);

        List<NgayNghiPhepConvertDto> converts = new ArrayList<>();
        Double count = 0.0;
        for (NgayNghiPhepEntity convert : nghiPhep) {
//            DateFormat format = new SimpleDateFormat("EEEE");
//            String finalDay = format.format(convert.getNgayNghiPhep());
            String finalDay = "";
            LocalDate localDate = convert.getNgayNghiPhep().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            DayOfWeek day = localDate.getDayOfWeek();
            if (day.getValue() == DayOfWeek.MONDAY.getValue()) {
                finalDay = DayOfWeeks.MONDAY;
            }
            if (day.getValue() == DayOfWeek.TUESDAY.getValue()) {
                finalDay = DayOfWeeks.TUESDAY;
            }
            if (day.getValue() == DayOfWeek.WEDNESDAY.getValue()) {
                finalDay = DayOfWeeks.WEDNESDAY;
            }
            if (day.getValue() == DayOfWeek.THURSDAY.getValue()) {
                finalDay = DayOfWeeks.THURSDAY;
            }
            if (day.getValue() == DayOfWeek.FRIDAY.getValue()) {
                finalDay = DayOfWeeks.FRIDAY;
            }
            System.out.println(day.getValue() == DayOfWeek.MONDAY.getValue());


            SimpleDateFormat formatDay = new SimpleDateFormat("dd");
            String strDate = formatDay.format(convert.getNgayNghiPhep());

            SimpleDateFormat formatMonth = new SimpleDateFormat("MM");
            String strMonth = formatMonth.format(convert.getNgayNghiPhep());

            SimpleDateFormat formatYear = new SimpleDateFormat("yyyy");
            String strYear = formatYear.format(convert.getNgayNghiPhep());

            NgayNghiPhepConvertDto ngayNghi = new NgayNghiPhepConvertDto(convert.getNgayNghiPhep(), convert.getManDay(), finalDay, strDate, strMonth, strYear);
            converts.add(ngayNghi);
            count += convert.getManDay();
        }

        UserNgayNghiPhepDto dto = new UserNgayNghiPhepDto(user.getId(), user.getHoTen(),
                user.getSoNgayNghiPhepTieuChuan(), user.getBoPhan(), converts, count, (user.getSoNgayNghiPhepTieuChuan() - count));
        return dto;
    }


    @GetMapping("/chi_tiet_ngay_nghi_phep")
    public List<NgayNghiPhepConvertDto> chiTietNgayNghiPhep(@RequestParam Integer userId,
                                                            @RequestParam Integer month, @RequestParam Integer year) {
        List<NgayNghiPhepEntity> nghiPhep = ngayNghiPhepService.chiTietNgayNghiPhep(userId, month, year);
//        UserEntity user = service.findByUserId(userId);
        List<NgayNghiPhepConvertDto> converts = new ArrayList<>();
        Double count = 0.0;
        for (NgayNghiPhepEntity convert : nghiPhep) {
            String finalDay = "";
            LocalDate localDate = convert.getNgayNghiPhep().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            DayOfWeek day = localDate.getDayOfWeek();
            if (day.getValue() == DayOfWeek.MONDAY.getValue()) {
                finalDay = DayOfWeeks.MONDAY;
            }
            if (day.getValue() == DayOfWeek.TUESDAY.getValue()) {
                finalDay = DayOfWeeks.TUESDAY;
            }
            if (day.getValue() == DayOfWeek.WEDNESDAY.getValue()) {
                finalDay = DayOfWeeks.WEDNESDAY;
            }
            if (day.getValue() == DayOfWeek.THURSDAY.getValue()) {
                finalDay = DayOfWeeks.THURSDAY;
            }
            if (day.getValue() == DayOfWeek.FRIDAY.getValue()) {
                finalDay = DayOfWeeks.FRIDAY;
            }
            System.out.println(day.getValue() == DayOfWeek.MONDAY.getValue());
            SimpleDateFormat formatDay = new SimpleDateFormat("dd");
            String strDate = formatDay.format(convert.getNgayNghiPhep());

            SimpleDateFormat formatMonth = new SimpleDateFormat("MM");
            String strMonth = formatMonth.format(convert.getNgayNghiPhep());

            SimpleDateFormat formatYear = new SimpleDateFormat("yyyy");
            String strYear = formatYear.format(convert.getNgayNghiPhep());

            NgayNghiPhepConvertDto ngayNghi = new NgayNghiPhepConvertDto(convert.getNgayNghiPhep(), convert.getManDay(), finalDay, strDate, strMonth, strYear);
            converts.add(ngayNghi);
            count += convert.getManDay();
        }
        if (converts.size() == 0) {
            NgayNghiPhepConvertDto ngayNghi = new NgayNghiPhepConvertDto(null, 0.0, "", "", "", "");
            converts.add(ngayNghi);
        }
        return converts;
    }

    @GetMapping("/")
    public ChamCongDto chamCong(@RequestParam Integer userId, @RequestParam Integer month, @RequestParam Integer year) {
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
        int soNgayT7CN = 0;
        int tongSoNgayCong = getDay(month, year);
        for (int i = 1; i <= tongSoNgayCong; i++) {
//            Calendar cal = Calendar.getInstance();
//            cal.set(year, month, i);
//            DateFormat formatter = new SimpleDateFormat("EEEE", Locale.getDefault());
//            System.out.println(formatter.format(cal.getTime()));
//            if(formatter.format(cal.getTime()).equals("Saturday")||formatter.format(cal.getTime()).equals("Sunday")){
//                soNgayT7CN+=1;
//            }

            LocalDate date = LocalDate.of(year, month, i);
            DayOfWeek day = date.getDayOfWeek();
            if (day.getValue() == 6 || day.getValue() == 7) {
                soNgayT7CN += 1;

            }

        }

        int ngayCongDuKien = tongSoNgayCong - soNgayT7CN;


        double ngayCongThucTe = ngayCongDuKien - soNgayNghiPhep - ngayDiMuon;
        String ngayCong = ngayCongThucTe + "/" + ngayCongDuKien;


        ChamCongDto chamCong = new ChamCongDto(userName, thoiGian, soNgayNghiPhep, tongThoiGian, ngayCong);
        return chamCong;
    }

    @GetMapping("/chi_tiet_di_muon")
    public List<ChiTietDiMuonDto> chiTietDiMuon(@RequestParam Integer userId, @RequestParam Integer month, @RequestParam Integer year) {


        List<CheckinEntity> listCheckin = serviceCheckin.findByUserIdAndMonthAndYearCheck(userId, month, year);
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");


        List<ChiTietDiMuonDto> list = new ArrayList<>();
        for (CheckinEntity entity : listCheckin) {


            Date d1 = null;
            Date d2 = null;
//            LocalDateTime current = entity.getNgayCheckIn().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
//            String formatted = current.format(formatter);

            try {
                d1 = format.parse(thoiGianLamViecQuyDinh);
                d2 = format.parse(entity.getThoiGianCheckIn().toString());
//                d2 = format.parse(formatted);



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
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd");
                String formatDate = simpleDateFormat.format(entity.getNgayCheckIn());

                ChiTietDiMuonDto chiTietDiMuonDto = new ChiTietDiMuonDto(entity.getNgayCheckIn(), minutes, formatDate,entity.getThoiGianCheckIn().toString());
                list.add(chiTietDiMuonDto);
            }

        }
        if (list.size() == 0) {
            ChiTietDiMuonDto chiTietDiMuonDto = new ChiTietDiMuonDto(null, 0, null,null);
            list.add(chiTietDiMuonDto);
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
