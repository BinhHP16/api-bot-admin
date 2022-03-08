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
public class TongHopThuQuyCongDoanTungNguoiDto {
    private String thoiGian;
    private Integer tongTien;
}
