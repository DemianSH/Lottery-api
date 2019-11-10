package com.geeks.blockchaincourse.lotteryapi.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class MemberLottery {

    private final String address;
    private final BigDecimal txValue;
    private final LocalDateTime txTime;
    private final boolean status;
}
