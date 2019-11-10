package com.geeks.blockchaincourse.lotteryapi.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class LotteryInfo {

    private final String addressOwner;
    private final String addressContract;
    private final String description;
    private final LocalDateTime deadLine;
    private final boolean status;
}
