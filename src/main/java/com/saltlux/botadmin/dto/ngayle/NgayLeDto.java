package com.saltlux.botadmin.dto.ngayle;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class NgayLeDto {
    private Integer id;
    private String tenNgayLe;
    private List listNgayle;
    private String listThoiGian;

    public NgayLeDto(Integer id, String tenNgayLe, List listNgayle) {
        this.id = id;
        this.tenNgayLe = tenNgayLe;
        this.listNgayle = listNgayle;
    }

    public NgayLeDto(Integer id, String tenNgayLe, String listThoiGian) {
        this.id = id;
        this.tenNgayLe = tenNgayLe;
        this.listThoiGian = listThoiGian;
    }
}
