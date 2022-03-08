package com.saltlux.botadmin.dto.ngayle;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class ListNgayLeDto {
    private Date thoiGian;
}
