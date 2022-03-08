package com.saltlux.botadmin.dto.tuyendung;

import lombok.*;
import lombok.experimental.Accessors;

@Data
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class ThongTinTuyenDungDto {
  private  Integer id;
  private String tieuDe;
  private String noiDung;

}
