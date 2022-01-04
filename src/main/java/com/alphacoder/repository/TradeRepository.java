package com.alphacoder.repository;

import com.alphacoder.entity.Trade;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeRepository extends PagingAndSortingRepository<Trade, Long> {
}
