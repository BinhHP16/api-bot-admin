package com.saltlux.botadmin.dto.congdoan;

import lombok.*;
import lombok.experimental.Accessors;

@Data
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class TongHopThuQuyCongDoanDto {
    private String hoTen;
    private Integer thang1;
    private Integer thang2;
    private Integer thang3;
    private Integer thang4;
    private Integer thang5;
    private Integer thang6;
    private Integer thang7;
    private Integer thang8;
    private Integer thang9;
    private Integer thang10;
    private Integer thang11;
    private Integer thang12;
    private Integer sum;

}
