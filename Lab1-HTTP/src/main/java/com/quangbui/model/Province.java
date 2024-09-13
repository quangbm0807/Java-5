package com.quangbui.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Province {
    private Long id;
    private String name;
    private String code;
    private String divisionType;
}