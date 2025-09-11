package com.skylar.banking.services.impl;

import com.skylar.banking.dto.UserDto;
import com.skylar.banking.models.User;
import com.skylar.banking.repositories.UserRepository;
import com.skylar.banking.services.UserService;
import com.skylar.banking.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
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
}
