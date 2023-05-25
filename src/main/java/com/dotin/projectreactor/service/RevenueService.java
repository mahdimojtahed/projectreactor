package com.dotin.projectreactor.service;

import com.dotin.projectreactor.domain.Revenue;

import static com.dotin.projectreactor.util.CommonUtil.delay;

public class RevenueService {
    public Revenue getRevenue(Long movieID) {
        delay(1000); // simulating a network ( rest or db ) call
        return Revenue.builder()
                .movieInfoId(movieID)
                .budget(1000000)
                .boxOffice(5000000)
                .build();
    }
}
