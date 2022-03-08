package com.saltlux.botadmin.controller;

import com.saltlux.botadmin.dto.tuyendung.ThongTinTuyenDungDto;
import com.saltlux.botadmin.entity.CategoryTuyenDungEntity;
import com.saltlux.botadmin.entity.ThongTinTuyenDungEntity;
import com.saltlux.botadmin.service.IThongTinTuyenDungService;
import io.swagger.annotations.Api;
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
    public List<CategoryTuyenDungEntity> getCategory() {

        return service.getAll();
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
//    @PostMapping("/ung-tuyen")
//    public void ungTuyen(@RequestParam("fileCv") MultipartFile fileCv,
//                                         @RequestBody String hoTen,
//                                         @RequestBody String sdt, @RequestBody String email) throws IOException {
//
//
//
//        // 1. upload file Cv len thu muc F://
//        String root = "F:/saltlux-resources/";
//        String ungVienDir = root + "ung-vien/";
//
//        // tao thu muc neu chua co
//
//        // sinh ten file duy nhat theo extension cua fileCv -> quang_anh.docx -> 75847856384756566.docx
//        String newFileName = "";
//        String targetLocation = ungVienDir + newFileName;
//
//        Files.copy(fileCv.getInputStream(), , StandardCopyOption.REPLACE_EXISTING);
//
//        String cv_path = "";
//        UngVien uv = new UngVien()
//                .setCv_path(cv_path)
//                .setHoTen(hoTen)
//                .setEmail(email)
//                .setSdt(sdt);
//
//        // 2. save to database
//
//        // 3. send mail
//    }
//
//
//    @Data
//    @Accessors(chain = true)
//    static class UngVien {
//        private String hoTen;
//        private String sdt;
//        private String email;
//        private String cv_path;
//    }
//@RequestMapping(value = "/uploadCV", method = RequestMethod.POST)

//    @PostMapping("/uploadCV")
//    public String uploadFile(@RequestParam("file")  MultipartFile multipartFile, Model model, @RequestBody String hoTen,
//                             @RequestBody String viTri) {
//        model.addAttribute("message", "upload success");
//        UUID uuid = UUID.randomUUID();
//        try {
////            MultipartFile multipartFile = myFile.getMultipartFile();
//            String fileName = multipartFile.getOriginalFilename();
//
//            File file = new File(tuyenDungDir, fileName);
//
//            File file2 = new File(tuyenDungDir, uuid + fileName);
//
//            file.renameTo(file2);
//            multipartFile.transferTo(file2);
//
//            CVDto request = new CVDto(hoTen, viTri, uuid + fileName);
//            ungVienService.save(request);
//            emailService.sendSimpleEmail();
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            model.addAttribute("message", "upload failed");
//        }
//
//        return "result";
//    }

}
