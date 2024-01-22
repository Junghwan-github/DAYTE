package com.example.projectt.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContentsDTO {
    private String id;
    private String businessName;
    private String category;
    private String gu;
    private String ro;
    private Double positionX;
    private Double positionY;
}
