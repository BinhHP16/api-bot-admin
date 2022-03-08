package com.saltlux.botadmin.service;

import com.saltlux.botadmin.dto.user.ListUserDto;
import com.saltlux.botadmin.dto.user.UserDto;
import com.saltlux.botadmin.entity.UserEntity;
import com.saltlux.botadmin.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Log4j2
@Service
public class UserService implements IUserService {
    @Autowired
    UserRepository repository;

    @Override
    public List<UserEntity> getAll() {
        return repository.findAll();
    }

    @Override
    public List<ListUserDto> finAll() {
        List<ListUserDto> dtos=new ArrayList<>();
        List<UserEntity> listUsers=repository.findAll();
        for (UserEntity user : listUsers) {
            ListUserDto dto =new ListUserDto(user.getId(),user.getHoTen());
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public UserEntity findByUserId(Integer userId) {
        return repository.findByUserId(userId);
    }

    @Override
    public UserDto finById(Integer userId) {
        UserEntity user =repository.findByUserId(userId);
        UserDto dto =new UserDto(user.getId(),user.getHoTen(),user.getBoPhan(),user.getGioiTinh(),
                user.getNgaySinh(),user.getSdt(),user.getEmail(),user.getSoNgayNghiPhepTieuChuan(),1);

        return dto;
    }

    @Override
    public UserEntity findByEmailAndPassword(String email, String password) {

        UserEntity user =repository.findByEmailAndPassword(email);
        System.out.println(new Date());
        try {
            if(verify(password, user.getPassword())){
                return user;
            }
            System.out.println("Verify: " + verify("123456", "E10ADC3949BA59ABBE56E057F20F883E"));
        } catch (NoSuchAlgorithmException e) {
            log.info(e.getMessage(),e);
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public List<UserDto> finByname(String userName) {
        List<UserEntity> users =repository.finByname(userName);
        List<UserDto> dtos=new ArrayList<>();
        for (UserEntity user : users) {
            UserDto dto =new UserDto(user.getId(),user.getHoTen(),user.getBoPhan(),user.getGioiTinh(),
                    user.getNgaySinh(),user.getSdt(),user.getEmail(),user.getSoNgayNghiPhepTieuChuan(),1);
            dtos.add(dto);
        }
        return dtos;
    }

    public static boolean verify(String inputPassword, String hashPassWord)
            throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(inputPassword.getBytes());
        byte[] digest = md.digest();
        String myChecksum = DatatypeConverter
                .printHexBinary(digest).toUpperCase();
        return hashPassWord.equals(myChecksum);
    }



//    @Override
//    public PagedResponse<UserEntity> getAll(int page, int size, String orderBy, String sortDirection) {
//        Pageable pageable = buildPageable(page, size,orderBy,  sortDirection);
//        Page<UserEntity> systemPage = repository.search(pageable);
//        List<UserEntity> listChucNang = systemPage.getContent();
//        return new PagedResponse<>(listChucNang, systemPage.getNumber(), systemPage.getSize(), systemPage.getTotalElements(),
//                systemPage.getTotalPages(), systemPage.isLast());
//    }
}
