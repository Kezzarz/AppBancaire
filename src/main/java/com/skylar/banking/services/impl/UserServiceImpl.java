package com.skylar.banking.services.impl;

import com.skylar.banking.dto.AccountDto;
import com.skylar.banking.dto.UserDto;
import com.skylar.banking.models.Account;
import com.skylar.banking.models.User;
import com.skylar.banking.repositories.UserRepository;
import com.skylar.banking.services.AccountService;
import com.skylar.banking.services.UserService;
import com.skylar.banking.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final AccountService accountService;
    private final ObjectsValidator<UserDto> validator;

    @Override
    public Integer save(UserDto dto) {
        validator.validate(dto); // Valider l'objet
        User user = UserDto.toEntity(dto); // Transformer l'objet vers une entité
        return repository.save(user).getId(); // Renvoyé l'id ou l'objet
    }

    @Override
    public List<UserDto> findAll() {
        return repository.findAll()
                .stream() // Renvolyé un string utilisateur
                .map(UserDto::fromEntity) // on fait appelle à une méthode de transformation
                .collect(Collectors.toList()); // Collecter ou rassemblé les informations
    }

    @Override
    public UserDto findById(Integer id) {
        return repository.findById(id)
                .map(UserDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("No user was found with the provided ID : " + id));
    }

    @Override
    public void delete(Integer id) {
        // todo check before delete
        repository.deleteById(id);
    }

    @Override
    @Transactional // Permet un retour en arriere en cas d'erreur de cette méthode de validation.
    public Integer validateAccount(Integer id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No user was found for user account validation"));

        // create a ban account
        AccountDto account = AccountDto.builder()
                .user(UserDto.fromEntity(user))
                .build();
        accountService.save(account);

        user.setActive(true);
        repository.save(user);
        return user.getId();
    }

    @Override
    public Integer invalidateAccount(Integer id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No user was found for user account validation"));

        user.setActive(false);
        repository.save(user);
        return user.getId();
    }
}
