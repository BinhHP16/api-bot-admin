package com.saltlux.botadmin.dto.congdoan;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class CacKhoanChiPhiDto {
    private Integer id;
    private String noiDung;
    private Integer soTien;
    private String date;
    private String ghiChu;

}
