package com.alphacoder.util;

import com.alphacoder.domain.error.ErrorDto;
import com.alphacoder.domain.request.TradeRequest;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class TradeUtil {
    private TradeUtil(){
    }

    public static List<ErrorDto> getErrorDtos(String code, String message) {
        List<ErrorDto> errors= new ArrayList<>();
        ErrorDto error= new ErrorDto();
        error.setCode(code);
        error.setMessage(message);
        errors.add(error);
        return errors;
    }

    public static TradeRequest calculateRiskValues(TradeRequest tradeRequest, Double portfolioValue) {
        if(null!= tradeRequest){
            tradeRequest.setRiskPercentPosition(formatDoubleValueToTwoDecimalPlaces(
                    ((tradeRequest.getEntryPrice()-tradeRequest.getStopLoss())/tradeRequest.getEntryPrice()
                    )*100.00));
            tradeRequest.setAmountRisked(formatDoubleValueToTwoDecimalPlaces(
                    (tradeRequest.getEntryPrice()-tradeRequest.getStopLoss())
                            *tradeRequest.getNoOfShares()
            ));
            tradeRequest.setRiskPercentPortfolio(formatDoubleValueToTwoDecimalPlaces(
                    (tradeRequest.getAmountRisked()/portfolioValue)*100.00));
        }
        return tradeRequest;
    }

    private static Double formatDoubleValueToTwoDecimalPlaces(Double inputValue){
        DecimalFormat df= new DecimalFormat("###.##");
        Double returnValue= Double.valueOf(df.format(inputValue));
        return returnValue;
    }
}
