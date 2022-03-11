package com.saltlux.botadmin.dto.chamcong;

import lombok.*;
import lombok.experimental.Accessors;

@Data
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class ChamCongDto {
    private String hoTen;
    private String thoiGian;
    private Double nghiPhep;
    private long diMuon;
   private String ngayCong;

}
