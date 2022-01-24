package com.alphacoder.domain.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TradeRequest {
    private Long id;
    @NotNull(message = "Stock cannot be empty.")
    private String stock;

    @NotNull(message = "entryDate cannot be empty.")
    private Date entryDate;

    @NotNull(message = "entryPrice cannot be empty.")
    @Positive(message = "entryPrice must be greater than 0.")
    private Double entryPrice;

    @NotNull(message = "number of shares cannot be empty.")
    @Positive(message = "number of shares must be greater than 0.")
    private Long noOfShares;

    @NotNull(message = "stopLoss cannot be empty.")
    @Positive(message = "stopLoss must be greater than 0.")
    private Double stopLoss;

    private Double amountRisked;

    private Double riskPercentPosition;

    private Double riskPercentPortfolio;

    @NotNull(message = "tradeType cannot be empty.")
    private String tradeType;

    @NotNull(message = "shortLong cannot be empty.")
    private String shortLong;

    @NotNull(message = "chartPattern cannot be empty.")
    private String chartPattern;

    private Date exitDate;

    @Positive(message = "exitPrice must be greater than 0.")
    private Double exitPrice;
    private String commentsAfterBuy;
    private String commentsAfterSell;
}
