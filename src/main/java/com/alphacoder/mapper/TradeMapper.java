package com.alphacoder.mapper;

import com.alphacoder.domain.response.TradeRequest;
import com.alphacoder.domain.response.TradeResponse;
import com.alphacoder.entity.Trade;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface TradeMapper {
    Trade mapTradeRequestToTradeEntity(TradeRequest tradeRequest);
    TradeResponse mapTradeEntityToTradeResponse(Trade tradeEntity);
}