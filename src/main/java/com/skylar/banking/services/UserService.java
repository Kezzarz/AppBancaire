package com.skylar.banking.services;

import com.skylar.banking.dto.UserDto;

public interface UserService extends AbstractService<UserDto> {

    Integer validateAccount(Integer id);

    Integer invalidateAccount(Integer id);
}
