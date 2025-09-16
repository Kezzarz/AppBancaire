package com.skylar.banking.services;

import com.skylar.banking.dto.TransactionDto;

import java.util.List;

public interface TransactionService extends AbstractService<TransactionDto>{

    List<TransactionDto> findAllByUserId(Integer userId);
}
