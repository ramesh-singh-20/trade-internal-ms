package com.alphacoder.domain.response;

import lombok.Data;

import java.util.List;

@Data
public class TradeResponseDto {
    private List<TradeResponse> trades;
    private int totalPages;
    private Long totalRecords;
    private int pageSize;
}
