package com.skylar.banking.services.impl;

import com.skylar.banking.dto.AccountDto;
import com.skylar.banking.exceptions.OperationNonPermittedException;
import com.skylar.banking.models.Account;
import com.skylar.banking.repositories.AccountRepository;
import com.skylar.banking.services.AccountService;
import com.skylar.banking.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.iban4j.CountryCode;
import org.iban4j.Iban;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;
    private final ObjectsValidator<AccountDto> validator;

    @Override
    public Integer save(AccountDto dto) {
        // block account update -> iban cannot be changed
        if (dto.getId() != null) {
            throw new OperationNonPermittedException(
                    "Account cannot be updated",
                    "save account",
                    "Account",
                    "update not permitted"
            );
        }
        validator.validate(dto);
        Account account = AccountDto.toEntity(dto);
        //generate random IBAN
        account.setIban(generateRandomIban());
        return  repository.save(account).getId();
    }

    @Override
    public List<AccountDto> findAll() {
        return repository.findAll()
                .stream()
                .map(AccountDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public AccountDto findById(Integer id) {
        return repository.findById(id)
                .map(AccountDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("No account was found with the ID : " + id));
    }

    @Override
    public void delete(Integer id) {
        // Todo check delete account
        repository.deleteById(id);
    }

    private String generateRandomIban() {
        // generate an iban
        String iban = Iban.random(CountryCode.DE).toFormattedString();

        //check if the iban already exists
        boolean ibanExists = repository.findByIban(iban).isPresent();
        // if exists -> generate new random iban
        if (ibanExists) {
            generateRandomIban();
        }
        // if not exist -> return generated iban

        return iban;
    }
}
