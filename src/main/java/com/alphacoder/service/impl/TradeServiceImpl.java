package com.alphacoder.service.impl;

import com.alphacoder.domain.error.ErrorDto;
import com.alphacoder.domain.request.TradeRequest;
import com.alphacoder.domain.response.ResponseDto;
import com.alphacoder.domain.response.TradeResponse;
import com.alphacoder.domain.response.TradeResponseDto;
import com.alphacoder.entity.Trade;
import com.alphacoder.exception.ApplicationDomainException;
import com.alphacoder.mapper.TradeMapper;
import com.alphacoder.repository.TradeRepository;
import com.alphacoder.service.TradeService;
import com.alphacoder.util.TradeUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__ ({@Autowired}))
//@RefreshScope
public class TradeServiceImpl implements TradeService {
    private final TradeRepository repository;
    private final TradeMapper mapper;
    //@Lazy
    private final RestTemplate template;

    @Value("${app.microservice.portfolio.endpoints.asset}")
    private String PORTFOLIO_ASSET_URL;

    @Override
    public void addTrade(TradeRequest tradeRequest) {
        log.info("Trade Request: "+tradeRequest);
        tradeRequest= TradeUtil.calculateRiskValues(tradeRequest, getPortfolioValue());

        log.info("Trade Request with risk columns: "+tradeRequest);
        Trade tradeEntity= mapper.mapTradeRequestToTradeEntity(tradeRequest);

        log.info("Trade Entity After Mapping: "+ tradeEntity);

        repository.save(tradeEntity);
    }

    @Override
    public void updateTrade(TradeRequest tradeRequest) {
        log.info("Trade request: "+tradeRequest);
        tradeRequest= TradeUtil.calculateRiskValues(tradeRequest, getPortfolioValue());

        log.info("Trade Request with risk columns: "+tradeRequest);

        Trade entity= mapper.mapTradeRequestToTradeEntity(tradeRequest);
        log.info("Trade Entity After Mapping: "+ entity);
        repository.save(entity);
    }

    @Override
    public void deleteTradeById(Long id) {
        log.info("Trade id: "+id);
        repository.deleteById(id);
    }

    @Override
    public TradeResponseDto getTrades(int page, int pageSize) {
        return getTradeResponse(page, pageSize);
    }

    private TradeResponseDto getTradeResponse(int page, int pageSize){
        Page<Trade> paginatedTrades= getPaginatedTrades(page, pageSize);
        TradeResponseDto responseDto= new TradeResponseDto();
        List<TradeResponse> tradeList;

        if(paginatedTrades!= null && paginatedTrades.hasContent()){
            tradeList= new ArrayList<>();

            for(Trade entity: paginatedTrades){
                tradeList.add(mapper.mapTradeEntityToTradeResponse(entity));
            }

            if(tradeList!= null && !tradeList.isEmpty()){
                responseDto.setTrades(tradeList);
                responseDto.setTotalRecords(paginatedTrades.getTotalElements());
                responseDto.setTotalPages(paginatedTrades.getTotalPages());
                responseDto.setPageSize(pageSize);
            }

        }else{
            List<ErrorDto> errors = TradeUtil.getErrorDtos("TRADE_1001", "No Data Found.");
            throw new ApplicationDomainException(errors, HttpStatus.NOT_FOUND);
        }

        return responseDto;
    }

    private Page<Trade>  getPaginatedTrades(int page, int pageSize){
        Pageable pageable= PageRequest.of(page, pageSize);
        return repository.findAllByOrderByEntryDateDesc(pageable);
    }

    private Double getPortfolioValue(){
        log.info("Portfolio url: "+PORTFOLIO_ASSET_URL);
        ResponseDto<Double> responseDto= this.template.getForObject(PORTFOLIO_ASSET_URL, ResponseDto.class);
        return responseDto.getData();
    }


}
