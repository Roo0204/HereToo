package com.heretoo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SearchResultDTO {
    private Integer id;
    private String name;
    private Double lat;
    private Double lng;
    private String theme;
}
