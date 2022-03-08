package com.saltlux.botadmin.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class HomThuGopYReq {
    private Integer id;
    private String noiDung;
    private String tieuDe;
    private String nguoiGui;
    private Integer anDanh;
    private Date thoiGian;

}
