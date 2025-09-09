package com.skylar.banking.dto;

import com.skylar.banking.models.Address;
import com.skylar.banking.models.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Builder

public class AddressDto {

    private Integer id;

    private String street;
    private Integer houseNumber;
    private Integer zipCode;
    private String city;
    private String county;

    private Integer userId;

    public static AddressDto fromEntity(Address address) {
        return AddressDto.builder()
                .id(address.getId())
                .street(address.getStreet())
                .houseNumber(address.getHouseNumber())
                .zipCode(address.getZipCode())
                .city(address.getCity())
                .county(address.getCounty())
                .userId(address.getUser().getId())
                .build();
    }

    public static AddressDto toEntity(AddressDto address) {
        return AddressDto.builder()
                .id(address.getId())
                .street(address.getStreet())
                .houseNumber(address.getHouseNumber())
                .zipCode(address.getZipCode())
                .city(address.getCity())
                .county(address.getCounty())
                .user(
                        User.builder()
                            .id(address.getUserId())
                            .build()
                )
                .build();
    }
}
