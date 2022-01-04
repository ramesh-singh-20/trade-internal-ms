package com.alphacoder.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name= "Trade")
@Data
public class Trade {
    @Id
    @Column(name= "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "stock", nullable = false)
    private String stock;

    @Column(name="entry_price", nullable = false)
    private Double entryPrice;

    @Column(name= "entry_date", nullable = false)
    private Date entryDate;

    @Column(name="stop_loss", nullable = false)
    private Double stopLoss;

    @Column(name="amount_risked", nullable = false)
    private Double amountRisked;

    @Column(name="risk_percent_position", nullable = false)
    private Double riskPercentPosition;

    @Column(name="risk_percent_portfolio", nullable = false)
    private Double riskPercentPortfolio;

    @Column(name="exit_price")
    private Double exitPrice;

    @Column(name= "exit_date")
    private Date exitDate;

    @Column(name= "no_of_shares", nullable = false)
    private Long noOfShares;

    @Column(name="is_profitable", length = 1)
    private String isProfitable;

    @Column(name= "month")
    private Integer month;

    @Column(name= "year")
    private Integer year;

    @Column(name= "trade_type", nullable = false)
    private String tradeType;

    @Column(name= "short_long", nullable = false)
    private String shortLong;

    @Column(name= "chart_pattern", nullable = false)
    private String chartPattern;

    @Column(name= "comments_after_buy")
    private String commentsAfterBuy;

    @Column(name= "comments_after_sell")
    private String commentsAfterSell;
}
