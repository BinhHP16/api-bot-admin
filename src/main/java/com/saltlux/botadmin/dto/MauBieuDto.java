package com.saltlux.botadmin.dto;

import lombok.*;
import lombok.experimental.Accessors;

@Data
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class MauBieuDto {
    private String tenMauBieu;
    private String linkDownload;
}
