package com.skylar.banking.services.impl;

import com.skylar.banking.dto.AccountDto;
import com.skylar.banking.models.Account;
import com.skylar.banking.repositories.AccountRepository;
import com.skylar.banking.services.AccountService;
import com.skylar.banking.validators.ObjectsValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;
    private final ObjectsValidator<AccountDto> validator;

    @Override
    public Integer save(AccountDto dto) {
        validator.validate(dto);
        Account account = AccountDto.toEntity(dto);
        //todo generate random IBAN
        return  repository.save(account).getId();
    }

    @Override
    public List<AccountDto> findAll() {
        return List.of();
    }

    @Override
    public AccountDto findById(Integer id) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    private String generateRandomIban() {
        //todo generate an iban

        //check if the iban already exists

        // if exists -> generate new random iban

        // if not exist -> return generated iban

        return "";
    }
}
