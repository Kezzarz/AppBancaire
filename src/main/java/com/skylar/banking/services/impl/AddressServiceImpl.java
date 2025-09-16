package com.skylar.banking.services.impl;

import com.skylar.banking.dto.AddressDto;
import com.skylar.banking.models.Address;
import com.skylar.banking.services.AddressService;
import com.skylar.banking.validators.ObjectsValidator;
import com.skylar.banking.repositories.AddressRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository repository;
    private ObjectsValidator<AddressDto> validator;

    @Override
    public Integer save(AddressDto dto) {
        validator.validate(dto);
        Address address = AddressDto.toEntity(dto);
        return repository.save(address).getId();
    }

    @Override
    public List<AddressDto> findAll() {
        return repository.findAll()
                .stream()
                .map(AddressDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public AddressDto findById(Integer id) {
        return repository.findById(id)
                .map(AddressDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("No address found with the ID : " + id));
    }

    @Override
    public void delete(Integer id) {
        // toto check delete
        repository.deleteById(id);
    }
}
