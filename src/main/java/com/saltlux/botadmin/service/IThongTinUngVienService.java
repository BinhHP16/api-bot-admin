package com.saltlux.botadmin.service;

import com.saltlux.botadmin.dto.tuyendung.CVDto;
import com.saltlux.botadmin.entity.ThongTinUngVienEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;

public interface IThongTinUngVienService {
    ThongTinUngVienEntity save(CVDto request) throws MissingServletRequestParameterException;
}
