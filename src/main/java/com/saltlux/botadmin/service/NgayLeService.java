package com.saltlux.botadmin.service;

import com.saltlux.botadmin.dto.ngayle.ListNgayLeDto;
import com.saltlux.botadmin.dto.ngayle.NgayLeDto;
import com.saltlux.botadmin.entity.ChiTietNgayLeEntity;
import com.saltlux.botadmin.entity.NgayLeEntity;
import com.saltlux.botadmin.repository.NgayLeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NgayLeService implements INgayLeService{
    @Autowired
    NgayLeRepository repository;
    @Override
    public List<NgayLeDto> chiTietNgayLe(int year) {
        List<NgayLeEntity> list=repository.finByYear(year);
        List<NgayLeDto> dtos=new ArrayList();
        for (NgayLeEntity list1 : list) {

            List<ListNgayLeDto> list2=new ArrayList();
            List<String> list3=new ArrayList();
            for (ChiTietNgayLeEntity ct : list1.getListThoiGianNgayLe()) {
                ListNgayLeDto dto=new ListNgayLeDto(ct.getDate());
                list2.add(dto);
                list3.add(dto.getThoiGian().toString());
            }
            String listThoiGian="";
            for (int i=0;i<list3.size();i++) {
                listThoiGian+= list3.get(i)+"; ";
            }
            NgayLeDto dto=new NgayLeDto(list1.getId(),list1.getTenNgayLe(),listThoiGian);
            dtos.add(dto);
        }
        return dtos;

    }
}
