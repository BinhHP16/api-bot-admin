package com.saltlux.botadmin.dto.congdoan;

import lombok.*;
import lombok.experimental.Accessors;

@Data
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class KeHoachThuChiDto {

    private String item;
    private Integer ghiCo;
    private Integer ghiNo;
    private Integer tonCuoiKy;
    private Integer level;
}
