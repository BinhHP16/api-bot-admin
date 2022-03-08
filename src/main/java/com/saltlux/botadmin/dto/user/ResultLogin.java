package com.saltlux.botadmin.dto.user;

import lombok.*;
import lombok.experimental.Accessors;

@Data
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class ResultLogin {
    private Integer userId;
    private Integer result;
}
