package com.alphacoder.service;

import com.alphacoder.domain.response.TradeRequest;
import com.alphacoder.domain.response.TradeResponseDto;

public interface TradeService {
    public void addTrade(TradeRequest tradeRequest);
    public void updateTrade(TradeRequest tradeRequest);
    public void deleteTradeById(Long id);
    public TradeResponseDto getTrades(int page, int pageSize);
}
