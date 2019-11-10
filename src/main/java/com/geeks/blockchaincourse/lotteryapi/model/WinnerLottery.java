package com.geeks.blockchaincourse.lotteryapi.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class WinnerLottery {

    private final String address;
    private final BigDecimal amount;
    private final LocalDateTime txTimeClose;
}
