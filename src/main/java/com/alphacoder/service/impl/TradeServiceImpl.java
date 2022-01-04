package com.alphacoder.service.impl;

import com.alphacoder.domain.response.TradeRequest;
import com.alphacoder.domain.response.TradeResponseDto;
import com.alphacoder.service.TradeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TradeServiceImpl implements TradeService {
    @Override
    public void addTrade(TradeRequest tradeRequest) {

    }

    @Override
    public void updateTrade(TradeRequest tradeRequest) {

    }

    @Override
    public void deleteTradeById(Long id) {

    }

    @Override
    public TradeResponseDto getTrades(int page, int pageSize) {
        return null;
    }
}
