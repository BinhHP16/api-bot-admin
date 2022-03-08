package com.saltlux.botadmin.dto.tuyendung;

import lombok.*;
import lombok.experimental.Accessors;

@Data
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class CVDto {
    private Integer id;
    private String hoTen;
    private String viTriUngTuyen;
    private String path;

    public CVDto(String hoTen, String viTriUngTuyen, String path) {
        this.hoTen = hoTen;
        this.viTriUngTuyen = viTriUngTuyen;
        this.path = path;
    }
}
