package com.saltlux.botadmin.dto.lamthemgio;

import lombok.*;
import lombok.experimental.Accessors;

@Data
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class TinhThuNhapLamThemGioDto {
    private Double lamThemGioMienThue;
    private Double lamThemGioChiuThue;
}
