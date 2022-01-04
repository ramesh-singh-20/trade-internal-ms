package com.alphacoder.domain.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.sql.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TradeResponse {
    private Long id;
    private String stock;
    private Double entryPrice;
    private Long noOfShares;
    private Date entryDate;
    private Double stopLoss;
    private Double amountRisked;
    private Double riskPercentPosition;
    private Double riskPercentPortfolio;
    private Double exitPrice;
    private Date exitDate;
    private String isProfitable;
    private String tradeType;
    private String shortLong;
    private String chartPattern;
    private String commentsAfterBuy;
    private String commentsAfterSell;
}
