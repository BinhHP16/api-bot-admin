package com.saltlux.botadmin.service;

import com.saltlux.botadmin.dto.tuyendung.CVDto;
import com.saltlux.botadmin.entity.ThongTinUngVienEntity;
import com.saltlux.botadmin.repository.ThongTinUngVienRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MissingServletRequestParameterException;

import static com.saltlux.botadmin.utils.ValidationUtil.isValidId;


@Service
public class ThongTinUngVienService implements IThongTinUngVienService{
   @Autowired
    ThongTinUngVienRepository repository;

    @Override
    public ThongTinUngVienEntity save(CVDto request) throws MissingServletRequestParameterException {

        if (request == null) {
            throw new MissingServletRequestParameterException(null, null);
        }
        Integer id = request.getId();
        ThongTinUngVienEntity system = isValidId(id) ? repository.findById(id).get() : new ThongTinUngVienEntity(request.getHoTen(),request.getViTriUngTuyen(),request.getPath());
        BeanUtils.copyProperties(request, system, "");

        return repository.save(system);
    }
}
