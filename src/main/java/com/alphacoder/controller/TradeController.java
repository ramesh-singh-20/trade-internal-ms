package com.alphacoder.controller;

import com.alphacoder.domain.request.TradeRequest;
import com.alphacoder.domain.response.ResponseDto;
import com.alphacoder.service.TradeService;
import com.alphacoder.domain.response.ResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/trade")
@RequiredArgsConstructor(onConstructor = @__ ({@Autowired}))
@CrossOrigin
public class TradeController {
    private final TradeService service;

    @GetMapping()
    public ResponseEntity<ResponseDto> getAllTrades(@RequestParam(value="page", required = true)int page,
                                                    @RequestParam(value="pageSize", required = true)int pageSize){
        return ResponseEntity.ok(ResponseDto.forSuccess(service.getTrades(page, pageSize)));
    }

    @PostMapping
    public ResponseEntity<ResponseDto> addTrade(@Valid @RequestBody TradeRequest tradeRequest){
        this.service.addTrade(tradeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(ResponseStatus.SUCCESS, null));
    }

    @PutMapping
    public ResponseEntity<ResponseDto> updateTrade(@Valid @RequestBody TradeRequest tradeRequest){
        this.service.updateTrade(tradeRequest);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseDto(ResponseStatus.SUCCESS, null));
    }

    @DeleteMapping(value= "/{id}")
    public ResponseEntity<ResponseDto> deleteTradeById(@PathVariable Long id){
        this.service.deleteTradeById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(ResponseStatus.SUCCESS, null));
    }

}
