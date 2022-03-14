package com.saltlux.botadmin.dto.tuyendung;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class TuyenDungDto {
    private Integer id;
    private String viTri;
    private String code;
    private Date thoiGian;
    private String diaDiem;
    private String mucLuong;
    private String motaNganGon;
}
