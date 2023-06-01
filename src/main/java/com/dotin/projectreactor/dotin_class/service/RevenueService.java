package com.dotin.projectreactor.dotin_class.service;

import com.dotin.projectreactor.dotin_class.domain.Revenue;

import static com.dotin.projectreactor.dotin_class.util.CommonUtil.delay;

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
