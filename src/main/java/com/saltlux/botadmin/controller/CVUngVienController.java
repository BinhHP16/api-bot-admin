package com.saltlux.botadmin.controller;

import com.saltlux.botadmin.dto.tuyendung.CVDto;
import com.saltlux.botadmin.service.IEmailService;
import com.saltlux.botadmin.service.IThongTinUngVienService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Api(tags = "Gửi CV")
@RestController
@RequestMapping("/api/ung_vien")
public class CVUngVienController {

    @Value("${document.dir}")
    private String tuyenDungDir;


    @Autowired
    private IThongTinUngVienService ungVienService;

    @Autowired
    IEmailService emailService;



    @PostMapping("/uploadCV")
    public String uploadFile(@RequestParam("file") MultipartFile multipartFile, @RequestParam("hoTen") String hoTen,
                             @RequestParam("viTri") String viTri) {
//        model.addAttribute("message", "upload success");
        UUID uuid = UUID.randomUUID();
        try {
            String fileName = multipartFile.getOriginalFilename();

            File file = new File(tuyenDungDir+"/tuyen_dung/", fileName);

            File file2 = new File(tuyenDungDir+"/tuyen_dung/", uuid + fileName);

            file.renameTo(file2);
            multipartFile.transferTo(file2);

            CVDto request = new CVDto(hoTen, viTri, uuid + fileName);
            ungVienService.save(request);
            emailService.sendSimpleEmail();


        } catch (Exception e) {
            e.printStackTrace();
//            model.addAttribute("message", "upload failed");
        }

        return "result";
    }

}
