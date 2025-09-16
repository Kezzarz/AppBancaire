package com.skylar.banking.services;

import com.skylar.banking.dto.ContactDto;

import java.util.List;

public interface ContactService extends AbstractService<ContactDto> {

    List<ContactDto> findAllByUserId (Integer userId);
}
