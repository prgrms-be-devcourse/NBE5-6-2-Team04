package com.grepp.nbe562team04.model.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InterestDto {
    private String type;          // "ROLE" 또는 "SKILL"
    private String interestName;  // "백엔드", "Java" 등
}