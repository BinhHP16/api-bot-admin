package com.saltlux.botadmin.controller;

import com.saltlux.botadmin.dto.tuyendung.CVDto;
import com.saltlux.botadmin.multiple_thread.SendEmailCVThread;
import com.saltlux.botadmin.service.IEmailService;
import com.saltlux.botadmin.service.IThongTinUngVienService;
import io.swagger.annotations.Api;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

@Api(tags = "Gửi CV")
@RestController
@RequestMapping("/api/ung_vien")
public class CVUngVienController {

    @Value("${document.dir}")
    private String tuyenDungDir;

    @Value("${email.to}")
    public String FRIEND_EMAIL;

    @Value("${email.from}")
    public String MY_EMAIL;


    @Value("${email.password}")
    public String PASSWORD;


    @Autowired
    private IThongTinUngVienService ungVienService;

    @Autowired
    IEmailService emailService;

    @PostMapping("/upFile")
    public String uploadFile(HttpServletResponse response, @RequestParam("file") MultipartFile multipartFile) {
//        model.addAttribute("message", "upload success");
        UUID uuid = UUID.randomUUID();
        try {
            String fileName = multipartFile.getOriginalFilename();

            File file = new File(tuyenDungDir + "/test/", fileName);

            File file2 = new File(tuyenDungDir + "/test/", uuid + fileName);

            String pathFile = tuyenDungDir + "/test/" + uuid + fileName;

            file.renameTo(file2);
            multipartFile.transferTo(file2);
            return pathFile;

        } catch (Exception e) {
            e.printStackTrace();
//            model.addAttribute("message", "upload failed");

        }

        return "result";
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public String downloadCV(HttpServletResponse response, String filePath) {

        try {
            File file = ResourceUtils.getFile(filePath);

            byte[] data = FileUtils.readFileToByteArray(file);
            // Thiết lập thông tin trả về
            response.setContentType("application/octet-stream");
            response.setHeader("Content-disposition", "attachment; filename=" + file.getName());
            response.setContentLength(data.length);
            InputStream inputStream = new BufferedInputStream(new ByteArrayInputStream(data));
            FileCopyUtils.copy(inputStream, response.getOutputStream());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


    @GetMapping("/uploadCVAndSendMail")
    public String uploadCVAndSendEmail(HttpServletResponse response, @RequestParam("filePath") String filePath, @RequestParam("hoTen") String hoTen,
                                       @RequestParam("viTri") String viTri) throws IOException {

        File fileToUpload = ResourceUtils.getFile(filePath);
        UUID uuid = UUID.randomUUID();
        File file = new File(filePath);
        FileInputStream input = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile("file",
                file.getName(), "text/plain", IOUtils.toByteArray(input));
        try {
            String fileName = fileToUpload.getName();
            File file1 = new File(tuyenDungDir + "/tuyen_dung/", fileName);
            File file2 = new File(tuyenDungDir + "/tuyen_dung/", uuid + fileName);
            String newFilePath = tuyenDungDir + "/tuyen_dung/" + uuid + fileName;
            file1.renameTo(file2);
            multipartFile.transferTo(file2);
            SendEmailCVThread T3 = new SendEmailCVThread("SendEmailRecruit", newFilePath, hoTen, viTri, FRIEND_EMAIL, MY_EMAIL, PASSWORD);
            T3.start();
            CVDto request = new CVDto(hoTen, viTri, newFilePath);
            ungVienService.save(request);
            return newFilePath;
//            emailService.sendEmailAttach(link, hoTen, viTri);
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }


}
