package com.dotin.projectreactor.dotin_class.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Revenue {
    private Long movieInfoId;
    private double budget;
    private double boxOffice;
}
