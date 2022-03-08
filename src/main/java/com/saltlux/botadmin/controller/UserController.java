package com.saltlux.botadmin.controller;

import com.saltlux.botadmin.dto.Login;
import com.saltlux.botadmin.dto.UserDto;
import com.saltlux.botadmin.entity.UserEntity;
import com.saltlux.botadmin.service.IUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.util.List;
import java.util.UUID;

@Api(tags = "User")
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    IUserService service;

    @GetMapping("/all")
    public List<UserDto> getAll() {

        return service.finAll();
    }

    @GetMapping("/{userId}")
    public UserDto finById(@PathVariable Integer userId) {

        return service.finById(userId);
    }

    @PostMapping("/log_in")
    public Integer Login(@Valid @RequestBody Login login) {

        UserEntity user =service.findByEmailAndPassword(login.getEmail(), login.getPassword());
        if (user!=null) {
            return user.getId();
        } else {
            return 0;
        }
    }




//    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
//    public String uploadFile(@RequestParam("file") MultipartFile multipartFile, Model model) {
//        model.addAttribute("message", "Upload success");
////		model.addAttribute("description", myFile.getDescription());
//        UUID uuid = UUID.randomUUID();
//        try {
////			MultipartFile multipartFile = myFile.getMultipartFile();
//            String fileName = multipartFile.getOriginalFilename();
//
//            File file = new File("D:/TestUpload", fileName);
//
//            File file2 = new File("D:/TestUpload", uuid + fileName);
//
//            file.renameTo(file2);
//            multipartFile.transferTo(file2);
//        } catch (Exception e) {
//            e.printStackTrace();
//            model.addAttribute("message", "upload failed");
//        }
//        return "result";
//    }

}

