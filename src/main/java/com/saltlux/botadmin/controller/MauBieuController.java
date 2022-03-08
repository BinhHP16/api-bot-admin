package com.saltlux.botadmin.controller;

import com.saltlux.botadmin.entity.MauBieuEntity;
import com.saltlux.botadmin.service.IMauBieuService;
import io.swagger.annotations.Api;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@Api(tags = "Mẫu biểu")
@PropertySource("classpath:application.properties")
@RestController
@RequestMapping("/api/mau_bieu")
public class MauBieuController {

    @Autowired
    IMauBieuService service;

    @Value("${document.dir}")
    private String docDir;

    private String dir="/mau_bieu/";


    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public String downloadMauBieu(HttpServletResponse response, @RequestParam("code") String code) throws IOException {
        MauBieuEntity mauBieu=service.findByCode(code);

        try {
            File file = ResourceUtils.getFile(docDir+dir+mauBieu.getPath());

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


    @GetMapping("/list_mau_bieu/")
    public List<MauBieuEntity> getAll( ) {
       return service.findAll();
    }
//
//    @GetMapping("/list_download/")
//    public List<FormDownLoadDto> listDownload( ) {
//        List<FormDownLoadDto> formDtos=new ArrayList<>();
//
//        List<MauBieuEntity> entities=service.findAll();
//        for (MauBieuEntity entity : entities) {
//
//        }
//
//        return formDtos;
//    }

}
