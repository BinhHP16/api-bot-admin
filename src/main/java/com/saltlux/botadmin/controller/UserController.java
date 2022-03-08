package com.saltlux.botadmin.controller;

import com.saltlux.botadmin.dto.user.ListUserDto;
import com.saltlux.botadmin.dto.user.Login;
import com.saltlux.botadmin.dto.user.ResultLogin;
import com.saltlux.botadmin.dto.user.UserDto;
import com.saltlux.botadmin.entity.UserEntity;
import com.saltlux.botadmin.service.IUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "User")
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    IUserService service;

    @GetMapping("/all")
    public List<ListUserDto> getAll() {

        return service.finAll();
    }

    @GetMapping("/detail")
    public UserDto finById(@RequestParam Integer userId) {

        return service.finById(userId);
    }

    @PostMapping("/log_in")
    public ResultLogin Login(@Valid @RequestBody Login login) {
        UserEntity user = service.findByEmailAndPassword(login.getEmail(), login.getPassword());
        if (user != null) {
            ResultLogin resultLogin = new ResultLogin(user.getId(), 1);
            return resultLogin;
        } else {
            ResultLogin resultLogin = new ResultLogin(0, 0);
            return resultLogin;
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

