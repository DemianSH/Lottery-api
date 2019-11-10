package com.geeks.blockchaincourse.lotteryapi.controller.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class RequestModel {
    private String address;
    private BigDecimal valueEther;
    private String privateKey;
}
