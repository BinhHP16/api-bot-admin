package com.saltlux.botadmin.dto;

import lombok.*;
import lombok.experimental.Accessors;

@Data
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Login {
    private String email;
    private String password;
}
