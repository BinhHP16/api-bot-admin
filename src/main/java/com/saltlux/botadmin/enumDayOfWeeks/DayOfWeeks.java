package com.saltlux.botadmin.enumDayOfWeeks;

import lombok.*;
import lombok.experimental.Accessors;

@Data
@Getter
@Setter
@Accessors(chain = true)
public class DayOfWeeks {
    public static final String MONDAY="Thứ 2";
    public static final String TUESDAY="Thứ 3";
    public static final String WEDNESDAY="Thứ 4";
    public static final String THURSDAY="Thứ 5";
    public static final String FRIDAY="Thứ 6";
    public static final String SATURDAY="Thứ 7";
    public static final String SUNDAY="Chủ Nhật";
}
