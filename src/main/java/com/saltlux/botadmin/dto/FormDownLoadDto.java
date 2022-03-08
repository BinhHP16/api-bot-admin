package com.saltlux.botadmin.dto;

import lombok.*;
import lombok.experimental.Accessors;

@Data
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class FormDownLoadDto {
    private String tenMauBieu;
    private String link;
}
