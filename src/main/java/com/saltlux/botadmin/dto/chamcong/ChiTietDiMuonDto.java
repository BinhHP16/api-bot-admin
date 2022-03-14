package com.saltlux.botadmin.dto.chamcong;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class ChiTietDiMuonDto {
    private Date ngay;
    private long thoiGianDiMuon;
    private String day;
    private String thoiGianCheckin;
}
